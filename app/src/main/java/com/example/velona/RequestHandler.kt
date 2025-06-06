package com.example.velona

class RequestHandler private constructor() {
    companion object {
        fun initialize() {
            SignClient.setSessionRequestObserver { request ->
                when (request.method) {
                    "personal_sign" -> handleSignRequest(request)
                    "eth_sendTransaction" -> handleTransaction(request)
                    else -> rejectRequest(request)
                }
            }
        }

        private fun handleSignRequest(request: Sign.Model.SessionRequest) {
            val message = parseMessage(request.params)
            val signature = CryptoManager.sign(message)

            respondSuccess(request, signature)
        }

        private fun respondSuccess(request: Sign.Model.SessionRequest, result: Any) {
            SignClient.respond(
                Sign.Params.Response(
                    topic = request.topic,
                    response = Sign.Model.JsonRpcResponse.JsonRpcResult(
                        id = request.id,
                        result = result
                    )
                )
            )
        }
    }
}