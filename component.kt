package Pacman

import java.awt.Rectangle

open class component {
    var index = 0
    var states: Array<BooleanArray> ?=null
    var cellsize = 20
    var max = 400
    var speed = 4

    var direction: Char? = null
    var x: Int? = null
    var y: Int? = null



    constructor() {
        this.states=Array(20) { BooleanArray(20) }
        for(i in 0..cellsize-1){
            for(j in 0..cellsize-1)
               this.states!![i][j]=false}
    }




    fun updateState(Updatestates: Array<BooleanArray> =Array(20){BooleanArray(20)} )
    {

        for(i in 0..cellsize-1) {
            for (j in 0..cellsize - 1)
                this.states!![i][j]=Updatestates[i][j]
        }
    }

    fun isVailed(x: Int, y: Int): Boolean {
        if (x >= cellsize && x < max && y >= cellsize && y < max && states!![x/20-1][y/20-1])
            return true
        else
            return false
    }

    fun getShape():Rectangle
    {
        return Rectangle(x!!,y!!,20,20)
    }
}
