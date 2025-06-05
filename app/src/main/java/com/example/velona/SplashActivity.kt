package com.example.velona

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import android.view.animation.PathInterpolator
import android.widget.ImageView
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.logo)
        val title = findViewById<TextView>(R.id.title)

        // Начальное состояние (обе элементы - точки)
        logo.scaleX = 0.1f
        logo.scaleY = 0.1f
        logo.alpha = 0f
        logo.translationY = 100f

        title.scaleX = 0.1f
        title.scaleY = 0.1f
        title.alpha = 0f
        title.translationY = 100f

        // Последовательность анимаций
        logo.animate()
            .scaleX(1f)
            .scaleY(1f)
            .alpha(1f)
            .translationY(0f)
            .setDuration(800)
            .setInterpolator(OvershootInterpolator(2.0f))
            .withEndAction {
                // Анимация названия с небольшой задержкой
                title.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .alpha(1f)
                    .translationY(0f)
                    .setDuration(600)
                    .setInterpolator(BounceInterpolator())
                    .withEndAction {
                        // Плавный переход
                        Handler(Looper.getMainLooper()).postDelayed({
                            startActivity(Intent(this, MainActivity::class.java))
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                            finish()
                        }, 1000)
                    }
                    .start()
            }
            .start()
    }
}