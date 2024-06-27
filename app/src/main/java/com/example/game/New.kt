package com.example.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class New : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        // Initialize buttons
        val newGameBtn = findViewById<Button>(R.id.newGameBtn)
        val settingBtn = findViewById<Button>(R.id.settingBtn)
        val quiteBtn = findViewById<Button>(R.id.QuiteBtn) // Add this line to initialize the QuiteBtn

        // Set click listener for newGameBtn
        newGameBtn.setOnClickListener {
            // Create intent to navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for settingBtn
        settingBtn.setOnClickListener {
            // Create intent to navigate to SettingActivity
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for quiteBtn
        quiteBtn.setOnClickListener {
            // Show a confirmation dialog
            showQuitConfirmationDialog()
        }
    }

    private fun showQuitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit")
        builder.setMessage("Are you sure you want to exit the app?")
        builder.setPositiveButton("Yes") { _, _ ->
            // User clicked Yes, so exit the app
            finish()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            // User clicked No, so dismiss the dialog
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}