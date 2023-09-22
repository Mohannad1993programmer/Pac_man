package Pacman

import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JPanel
import javax.swing.Timer
import kotlin.concurrent.thread


class Board : JPanel(), ActionListener {

    var states: Array<BooleanArray>? = null
    var balls: Array<BooleanArray>? = null


    var lives = 2
    var timer: Timer? = null
    var direction: Char = 'L'
    var  flag=true
    var Score=0


    var title: Boolean? = null
    private var titlescreen: Image = Toolkit.getDefaultToolkit().getImage("./src/img/titleScreen.jpg")
    private var red_ghost1: Image = Toolkit.getDefaultToolkit().getImage("./src/img/ghost10.jpg")
    private var red_ghost2: Image = Toolkit.getDefaultToolkit().getImage("./src/img/ghost11.jpg")
    private var blue_ghost1: Image = Toolkit.getDefaultToolkit().getImage("./src/img/ghost30.jpg")
    private var blue_ghost2: Image = Toolkit.getDefaultToolkit().getImage("./src/img/ghost31.jpg")
    private var yellow_ghost1: Image = Toolkit.getDefaultToolkit().getImage("./src/img/ghost20.jpg")
    private var yellow_ghost2: Image = Toolkit.getDefaultToolkit().getImage("./src/img/ghost21.jpg")
    private var pink_ghost1: Image = Toolkit.getDefaultToolkit().getImage("./src/img/ghost40.jpg")
    private var pink_ghost2: Image = Toolkit.getDefaultToolkit().getImage("./src/img/ghost41.jpg")
    private var pacmanImg: Image = Toolkit.getDefaultToolkit().getImage("./src/img/pacman.jpg")
    private var pacmandown: Image = Toolkit.getDefaultToolkit().getImage("./src/img/pacmandown.jpg")
    private var pacmanup: Image = Toolkit.getDefaultToolkit().getImage("./src/img/pacmanup.jpg")
    private var pacmanleft: Image = Toolkit.getDefaultToolkit().getImage("./src/img/pacmanleft.jpg")
    private var pacmanright: Image = Toolkit.getDefaultToolkit().getImage("./src/img/pacmanright.jpg")
    private var G_red = arrayOf(red_ghost1, red_ghost2)
    private var G_yellow = arrayOf(yellow_ghost1, yellow_ghost2)
    private var G_blue = arrayOf(blue_ghost1, blue_ghost2)
    private var G_pink = arrayOf(pink_ghost1, pink_ghost2)
    private var pacman_Images = arrayOf(pacmanleft, pacmanright, pacmanup, pacmandown)

    var G1 = Ghost(10 * component().cellsize, 8 * component().cellsize)
    var G2 = Ghost(10 * component().cellsize, 9 * component().cellsize)
    var G3 = Ghost(11 * component().cellsize, 9 * component().cellsize)
    var G4 = Ghost(9 * component().cellsize, 9 * component().cellsize)
    var pacman = Pacman(10 * component().cellsize, 15 * component().cellsize)

    init {
        //title
        addKeyListener(TAdapter())
        isFocusable=true
        title = true
        this.balls = Array(component().cellsize) { BooleanArray(component().cellsize) }
        this.states = Array(component().cellsize) { BooleanArray(component().cellsize) }
        timer = Timer(50, this)
        timer!!.start()
        for (i in 0 until component().cellsize) {
            for (j in 0 until component().cellsize) {
                balls!![i][j] = true
                states!![i][j] = true
            }
        }

    }

    override fun actionPerformed(e: ActionEvent?) {
        if(title==false && lives>0){
            if(flag) {
                Thread.sleep(2000)
                flag = false

            }
            G1.move()
            if(G1.getShape().intersects(pacman.getShape()))reset()
        G2.move()
            if(G2.getShape().intersects(pacman.getShape()))reset()
        G3.move()
            if(G3.getShape().intersects(pacman.getShape()))reset()
        G4.move()
            if(G4.getShape().intersects(pacman.getShape()))reset()
        G1.updateState(states!!)
        G2.updateState(states!!)
        G3.updateState(states!!)
        G4.updateState(states!!)
        pacman.move(direction)
            if(balls!![pacman.x!! / 20][pacman.y!! / 20]){
                Score++
        balls!![pacman.x!! / 20][pacman.y!! / 20] = false}
        pacman.updateState(states!!)
        repaint()}
    }


    fun drawlives(g: Graphics) {
        g.color = Color.yellow
        for (i in 0 until lives)
        // g.fillOval((20+5)*i+15,400+10,20,20)
            g.drawImage(pacmanleft, (component().cellsize + 5) * i + 15, component().max + 10, null)
    }

    fun update(g: Graphics, x: Int, y: Int, width: Int, height: Int) {

        g.fillRect(x, y, width, height)
        for (i in x / 20 until (x / 20 + width / 20)) {
            for (j in y / 20 until (y / 20 + height / 20)) {
                balls!![i][j] = false
                states!![i - 1][j - 1] = false
            }
        }
    }

    fun drawBalls(g: Graphics) {
        g.color = Color.yellow
        for (i in 1 until component().cellsize) {
            for (j in 1 until component().cellsize) {
                if (balls!![i][j])
                    g.fillOval(i * 20 + 8, j * 20 + 8, 4, 4)
            }
        }

    }

    fun reset()
    {
        if(lives>0) lives--

        G1.x=10*component().cellsize
        G1.y=8 * component().cellsize

        G2.x=10 * component().cellsize
        G2.y=9*component().cellsize

        G3.x=11 * component().cellsize
        G3.y=9*component().cellsize

        G4.x=9 * component().cellsize
        G4.y=9*component().cellsize

        pacman.x=10*component().cellsize
        pacman.y=15*component().cellsize





    }


    fun drawBoard(g: Graphics) {
        g.color = Color.white
        g.drawRect(19, 19, 382, 382)
        g.color = Color.blue
        update(g, 40, 40, 60, 20)
        update(g, 120, 40, 60, 20)
        update(g, 200, 20, 20, 40)
        update(g, 240, 40, 60, 20);
        update(g, 320, 40, 60, 20)
        update(g, 40, 80, 60, 20)
        update(g, 160, 80, 100, 20);
        update(g, 200, 80, 20, 60)
        update(g, 320, 80, 60, 20);
        update(g, 20, 120, 80, 60)
        update(g, 320, 120, 80, 60);
        update(g, 20, 200, 80, 60)
        update(g, 320, 200, 80, 60);
        update(g, 160, 160, 40, 20);
        update(g, 220, 160, 40, 20)
        update(g, 160, 180, 20, 20);
        update(g, 160, 200, 100, 20)
        update(g, 240, 180, 20, 20);
        update(g, 120, 120, 60, 20)
        update(g, 120, 80, 20, 100)
        update(g, 280, 80, 20, 100);
        update(g, 240, 120, 60, 20);
        update(g, 280, 200, 20, 60)
        update(g, 120, 200, 20, 60);
        update(g, 160, 240, 100, 20);
        update(g, 200, 260, 20, 40);
        update(g, 120, 280, 60, 20);
        update(g, 240, 280, 60, 20);
        update(g, 40, 280, 60, 20)
        update(g, 80, 280, 20, 60);
        update(g, 320, 280, 60, 20);
        update(g, 320, 280, 20, 60)
        update(g, 20, 320, 40, 20)
        update(g, 360, 320, 40, 20);
        update(g, 160, 320, 100, 20);
        update(g, 200, 320, 20, 60);
        update(g, 40, 360, 140, 20);
        update(g, 240, 360, 140, 20);
        update(g, 280, 320, 20, 60);
        update(g, 120, 320, 20, 60);


        }

    override fun paint(g: Graphics) {
        g.color = Color.BLACK
        g.fillRect(0, 0, 420, 500)
        drawBoard(g)
        drawBalls(g)
        drawlives(g)

        var f:Font= Font("Arial",Font.BOLD,24)
        g.font=f
        g.drawString("Score: "+Score,component().max/2+50,component().max+30)
        g.drawImage(G_red!![G1.index], G1.x!!, G1.y!!, null)
        g.drawImage(G_yellow!![G2.index], G2.x!!, G2.y!!, null)
        g.drawImage(G_pink!![G3.index], G3.x!!, G3.y!!, null)
        g.drawImage(G_blue!![G4.index], G4.x!!, G4.y!!, null)
        g.drawImage(pacman_Images[pacman.index], pacman.x!!, pacman.y!!, null)
        if (title == true) {
            g.drawImage(titlescreen, 0, 0, null)
            repaint()
        }
        if(lives==0)
            gameOver(g)
        if(check())
            winGame(g)
    }
    private fun winGame(g: Graphics) {

        val msg = "Game Over"
        val small = Font("Helvetica", Font.BOLD, 14)
        val fontMetrics = getFontMetrics(small)

        val rh = RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON)

        rh[RenderingHints.KEY_RENDERING] = RenderingHints.VALUE_RENDER_QUALITY

        (g as Graphics2D).setRenderingHints(rh)

        g.color = Color.white
        g.font = small
        g.drawString(msg, (420 - fontMetrics.stringWidth(msg)) / 2,
            500 / 2)
        timer!!.stop()
    }


    private fun gameOver(g: Graphics) {

        val msg = "Game Over Bro!! your Score is "+Score
        val small = Font("Helvetica", Font.BOLD, 20)
        val fontMetrics = getFontMetrics(small)

        val rh = RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON)

        rh[RenderingHints.KEY_RENDERING] = RenderingHints.VALUE_RENDER_QUALITY

        (g as Graphics2D).setRenderingHints(rh)

        g.color = Color.white
        g.font = small
        g.drawString(msg, (420 - fontMetrics.stringWidth(msg)) / 2,
           500 / 2)
        timer!!.stop()
    }

    fun check():Boolean
    {
        for (i in 1 until component().cellsize) {
            for (j in 1 until component().cellsize) {
                if (balls!![i][j])
                    return false
            }
        }
        return true

    }

    private inner class TAdapter : KeyAdapter() {

            override fun keyPressed(e: KeyEvent?) {


                if (e!!.keyCode== KeyEvent.VK_LEFT ) direction='L'
                if (e!!.keyCode == KeyEvent.VK_RIGHT ) direction='R'
                if (e!!.keyCode == KeyEvent.VK_UP )direction='U'
                if (e!!.keyCode == KeyEvent.VK_DOWN) direction='D'
                if(e!!.keyCode==KeyEvent.VK_SPACE) title=false

            }


        }
    }

