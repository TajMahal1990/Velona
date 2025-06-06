package com.example.velona


import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    // UI элементы
    private lateinit var metaMaskButton: LinearLayout
    private lateinit var walletConnectButton: LinearLayout
    private lateinit var guestButton: LinearLayout
    private lateinit var titleText: TextView
    private lateinit var subtitleText: TextView
    private lateinit var logoImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        metaMaskButton = findViewById(R.id.metamaskButton)
        walletConnectButton = findViewById(R.id.walletconnectButton)
        guestButton = findViewById(R.id.guestButton)
        titleText = findViewById(R.id.title)
        subtitleText = findViewById(R.id.subtitle)
        logoImage = findViewById(R.id.logoImage)



        // Обновим текст (если нужно)
        titleText.text = "VELONA"
        subtitleText.text = "Welcome to Web 3 messenger"
    }

    private fun setupClickListeners() {
        metaMaskButton.setOnClickListener {
            Toast.makeText(this, "MetaMask selected", Toast.LENGTH_SHORT).show()
            connectWithMetaMask()
        }

        walletConnectButton.setOnClickListener {
            Toast.makeText(this, "WalletConnect selected", Toast.LENGTH_SHORT).show()
            connectWithWalletConnect()
        }

        guestButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        logoImage.setOnClickListener {
            Toast.makeText(this, "Velona Logo clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun connectWithMetaMask() {
        // TODO: Реализация подключения MetaMask
    }

    private fun connectWithWalletConnect() {
        // TODO: Реализация подключения WalletConnect
    }

    private fun exploreAsGuest() {
        // TODO: Реализация гостевого режима
    }
}
