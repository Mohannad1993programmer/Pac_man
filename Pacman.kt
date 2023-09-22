package Pacman

class Pacman:component {

    constructor(x:Int,y:Int)
    {
        this.x=x
        this.y=y
    }

    fun move(direction:Char) {


        when (direction) {

            'L' -> {
                if (isVailed(x!! - speed, y!!) && isVailed(x!! - speed, y!! + cellsize - 5))
                    x = x!! - speed
                else if (y!! > 178 && y!! < 182 && x == 20) x = 380
                index = 0
            }
            'R' ->{ if (isVailed(x!! + cellsize, y!!) && isVailed(x!! + cellsize, y!!+cellsize-5))
                x=x !! + speed
            else if (y!! > 178 && y!! < 182 && x == 380) x = 20
                index=1
            }
            'U' ->{ if (isVailed(x!!, y!! - speed) && isVailed(x!! + cellsize-5, y!!-speed))
                y=y !! - speed
                index=2
            }
            'D' ->{ if (isVailed(x!!, y!! + cellsize) && isVailed(x!!+cellsize-5, y!! + cellsize))
                y=y !! + speed
                index=3
            }
        }



    }
}