package com.example.velona

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.walletconnect.android.Core
import com.walletconnect.android.CoreClient
import com.walletconnect.sign.client.Sign
import com.walletconnect.sign.client.SignClient

class WalletActivity : AppCompatActivity() {
    private val projectId = "af4b454520b3ff02cb7e099bf8eb18a4" // Замените на реальный

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWalletConnect()
    }

    private fun initWalletConnect() {
        try {
            // 1. Инициализация Core
            CoreClient.initialize(
                relayServerUrl = "wss://relay.walletconnect.com",
                connectionType = Core.ConnectionType.AUTOMATIC,
                application = application,
                metaData = Core.Model.AppMetaData(
                    name = "Velona Messenger",
                    description = "Decentralized Web3 messenger",
                    url = "https://velona.chat", // можно свой сайт, или официальный
                    icons = listOf("https://imgfoto.host/i/cpljTS"), // прямой URL к картинке с расширением
                    redirect = "velona://auth"
                ),
                projectId = projectId
            )


            // 2. Инициализация Sign Client
            SignClient.initialize(
                Sign.Params.Init(CoreClient),
                onError = { error ->
                    Log.e("WC", "SignClient init error: ${error.throwable}")
                }
            )

            Log.d("WC", "Initialization successful")
        } catch (e: Exception) {
            Log.e("WC", "Init failed", e)
        }
    }
}