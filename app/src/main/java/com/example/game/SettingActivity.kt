package com.example.game

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
    }

    fun onBeginnerButtonClick(view: View) {
        navigateToMainActivity()
    }

    fun onIntermediateButtonClick(view: View) {
        navigateToMainActivity()
    }

    fun onHardButtonClick(view: View) {
        navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
