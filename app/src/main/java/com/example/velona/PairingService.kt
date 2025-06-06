package com.example.velona

class PairingService {
    fun generateConnectionURI(): Flow<String> = callbackFlow {
        val pairingParams = Sign.Params.Pair()

        SignClient.connect(
            pairingParams = pairingParams,
            onSuccess = { pairing -> trySend(pairing.uri) },
            onError = { error -> close(error.throwable) }
        )

        awaitClose()
    }
}