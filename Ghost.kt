package Pacman


import kotlin.collections.HashSet


class Ghost:component {


    constructor(x: Int, y: Int) {

        this.x = x
        this.y = y
        direction = 'L'


    }

    fun choise(): Boolean {
        if (x!! % cellsize == 0 && y!! % cellsize == 0)
            return true
        else
            return false
    }

    fun selectdirection(): Char {

        var newX = x
        var newY = y
        val myset = HashSet<Char>()
        var backward = 'R'
        when (direction) {
            'L' -> backward = 'R'
            'R' -> backward = 'L'
            'U' -> backward = 'D'
            'D' -> backward = 'U'
        }
        var newdirection = backward
        while (newdirection == backward || !isVailed(newX!!, newY!!)) {
            if (myset.size == 3) {
                myset.clear()
                newdirection = backward
                break
            }
            var random = ((Math.random() * 4) + 1).toInt()

            if (random == 1) {
                newdirection = 'L'
                newX = newX!! - speed
            } else if (random == 2) {
                newdirection = 'R'
                newX = newX!! + cellsize
            } else if (random == 3) {
                newdirection = 'U'
                newY = newY!! - speed
            } else if (random == 4) {
                newdirection = 'D'
                newY = newY!! + cellsize
            }

            if (newdirection != backward)
                myset.add(newdirection)

            index=random%2

        }

        return newdirection!!
    }

    fun move() {

        if (choise())
          direction = selectdirection()


        when (direction) {

                'L' -> if (isVailed(x!! - speed, y!!)) x=x!! - speed
                'R' -> if (isVailed(x!! + cellsize, y!!))x=x !! + speed
                'U' -> if (isVailed(x!!, y!! - speed)) y=y !! - speed
                'D' -> if (isVailed(x!!, y!! + cellsize)) y=y !! + speed
            }



    }
}