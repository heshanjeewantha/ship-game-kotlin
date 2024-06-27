package com.example.game

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// This class represents the main activity of the game.
class MainActivity : AppCompatActivity(), GameTask {

    // Declare variables for UI elements
    lateinit var rootLayout: LinearLayout
    lateinit var startBtn: Button
    lateinit var mGameView: GameView
    lateinit var score: TextView

    // Override the onCreate method to initialize the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set the layout for the activity
        startBtn = findViewById(R.id.startBtn) // Initialize start button
        rootLayout = findViewById(R.id.rootLayout) // Initialize root layout
        score = findViewById(R.id.score) // Initialize score TextView

        // Set a click listener for the start button
        startBtn.setOnClickListener {
            // Create a new instance of the GameView
            mGameView = GameView(this, this)
            mGameView.setBackgroundResource(R.drawable.ocean) // Set background resource for GameView

            rootLayout.addView(mGameView) // Add GameView to the root layout
            startBtn.visibility = View.GONE // Hide start button
            score.visibility = View.GONE // Hide score TextView
        }
    }

    // Implementation of the closeGame method from the GameTask interface
    override fun closeGame(mScore: Int) {
        score.text = "Score: $mScore" // Update score TextView with the final score
        rootLayout.removeView(mGameView) // Remove GameView from the root layout
        startBtn.visibility = View.VISIBLE // Make start button visible again
        score.visibility = View.VISIBLE // Make score TextView visible again
    }
}
