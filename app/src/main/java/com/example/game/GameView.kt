package com.example.game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.KeyEvent

import android.view.View

// This class represents the main game view where the game graphics and logic are handled.
class GameView(var c: Context, var gameTask: GameTask) : View(c) {
    private var myPaint: Paint? = null // Paint object for drawing
    private var speed = 1 // Initial speed of the game
    private var time = 0 // Current time in the game
    private var score = 0 // Player's score
    private var myShipPosition = 0 // Position of the player's ship
    private val otherShips = ArrayList<HashMap<String, Any>>() // List to store information about other cars

    var viewWidth = 0 // Width of the game view
    var viewHeight = 0 // Height of the game view

    // Initialization block
    init {
        myPaint = Paint() // Initialize Paint object
        isFocusable = true // Set the view to be focusable to receive key events
    }

    // Override the onDraw method to handle drawing of the game elements
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        viewWidth = this.measuredWidth // Get the width of the game view
        viewHeight = this.measuredHeight // Get the height of the game view

        // Spawn other cars randomly
        if (time % 700 < 10 + speed) {
            val map = HashMap<String, Any>()
            map["lane"] = (0..2).random() // Randomly select a lane for the new ship
            map["startTime"] = time
            otherShips.add(map)
        }
        time = time + 10 + speed // Update the time
        val shipWidth = viewWidth / 5 // Calculate width of ships
        val shipHeight = shipWidth + 10 // Calculate height of ships
        myPaint!!.style = Paint.Style.FILL

        // Draw the player's car
        val d = resources.getDrawable(R.drawable.ship1, null)
        d.setBounds(
            myShipPosition * viewWidth / 3 + viewWidth / 15 + 25,
            viewHeight - 2 - shipHeight,
            myShipPosition * viewWidth / 3 + viewWidth / 15 + shipWidth - 25,
            viewHeight - 2
        )
        d.draw(canvas)

        // Draw other Ships, check for collisions and update score
        for (i in otherShips.indices.reversed()) {
            try {
                val shipX = otherShips[i]["lane"] as Int * viewWidth / 3 + viewWidth / 15
                var shipY = time - otherShips[i]["startTime"] as Int
                val d2 = resources.getDrawable(R.drawable.ship2, null)

                d2.setBounds(
                    shipX + 25, shipY - shipHeight, shipX + shipWidth - 25, shipY
                )
                d2.draw(canvas)

                // Check for collision with the player's Ship
                if (otherShips[i]["lane"] as Int == myShipPosition) {
                    if (shipY > viewHeight - 2 - shipHeight
                        && shipY < viewHeight - 2
                    ) {
                        gameTask.closeGame(score) // End the game if collision occurs
                    }
                }

                // Remove Ships that have passed the bottom of the screen, update score and speed
                if (shipY > viewHeight + shipHeight) {
                    otherShips.removeAt(i)
                    score++
                    speed = 1 + Math.abs(score / 8)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Draw score and speed on the screen
        myPaint!!.color = Color.YELLOW
        myPaint!!.textSize = 50f
        canvas.drawText("Score : $score", 80f, 80f, myPaint!!)
        canvas.drawText("Speed : $speed", 380f, 80f, myPaint!!)

        invalidate() // Invalidate the view to trigger redraw
    }

    // Override the onKeyDown method to handle key events (left and right arrow keys)
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                // Move the player's Ship to the left
                myShipPosition = if (myShipPosition > 0) myShipPosition - 1 else myShipPosition
                invalidate() // Trigger redraw with updated position
                return true
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                // Move the player's Ship to the right
                myShipPosition = if (myShipPosition < 2) myShipPosition + 1 else myShipPosition
                invalidate() // Trigger redraw with updated position
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}
