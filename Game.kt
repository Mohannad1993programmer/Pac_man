package Pacman


import java.awt.BorderLayout
import javax.swing.JFrame



class Game:JFrame() {
    init {
        CreateGame()
    }

    private fun CreateGame() {


        add(Board(), BorderLayout.CENTER)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setSize(420, 500)
        setLocationRelativeTo(null)
        isResizable = false
        isVisible = true
        title = "Pac Man"



    }

}

fun main() {
    Game()

}
