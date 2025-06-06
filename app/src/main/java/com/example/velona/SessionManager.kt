package com.example.velona

class SessionManager private constructor() {
    private val activeSessions = mutableMapOf<String, Session>()

    companion object {
        private var instance: SessionManager? = null

        fun get(): SessionManager {
            return instance ?: synchronized(this) {
                instance ?: SessionManager().also { instance = it }
            }
        }
    }

    fun approveSession(proposal: Sign.Model.SessionProposal) {
        val namespaces = buildNamespaces(proposal)

        SignClient.approve(
            Sign.Params.Approve(
                proposerPublicKey = proposal.proposerPublicKey,
                namespaces = namespaces
            ),
            onSuccess = { session ->
                activeSessions[session.topic] = session.toAppSession()
            },
            onError = { error -> /* Обработка ошибок */ }
        )
    }

    fun disconnectSession(topic: String) {
        SignClient.disconnect(
            Sign.Params.Disconnect(topic = topic),
            onSuccess = { activeSessions.remove(topic) },
            onError = { error -> /* Обработка ошибок */ }
        )
    }

    private fun buildNamespaces(proposal: Sign.Model.SessionProposal): Map<String, Sign.Model.Namespace.Session> {
        return mapOf(
            "eip155" to Sign.Model.Namespace.Session(
                chains = listOf("eip155:1"),
                methods = listOf("eth_sendTransaction", "personal_sign"),
                events = listOf("chainChanged", "accountsChanged")
            )
        )
    }
}