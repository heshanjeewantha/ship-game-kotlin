package com.example.game

// This interface defines a contract for communication between the MainActivity and the GameView.
interface GameTask {
    // This method is called when the game needs to be closed, typically when the player loses.
    // It takes the final score of the game as a parameter.
    fun closeGame(mScore: Int)
}