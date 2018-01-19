package com.example.liza.myexamapp.Screens

import android.graphics.Color
import com.prokkypew.asciipanelview.AsciiPanelView
import com.example.liza.myexamapp.World.World
import com.example.liza.myexamapp.World.WorldBuilder
import android.R.attr.centerY
import android.R.attr.centerX
import android.util.Log


class PlayScreen(panelView: AsciiPanelView) : Screen(panelView) {
    companion object {
        internal val SCREEN_WIDTH = 48
        internal val SCREEN_HEIGHT = 26
        internal val WORLD_WIDTH = 90
        internal val WORLD_HEIGHT = 32
    }

    private var world: World? = null
    private var centerX: Int = 0
    private var centerY: Int = 0

    private fun createWorld() {
        world = WorldBuilder(WORLD_WIDTH, WORLD_HEIGHT)
                .makeCaves()
                .build()
    }

    fun getScrollX(): Int {
        return Math.max(0, Math.min(centerX - SCREEN_WIDTH / 2, world!!.width() - SCREEN_WIDTH))
    }

    fun getScrollY(): Int {
        return Math.max(0, Math.min(centerY - SCREEN_HEIGHT / 2, world!!.height() - SCREEN_HEIGHT))
    }

    private fun displayTiles(left: Int, top: Int) {
        panel.clear()
        for (x in 0 until SCREEN_WIDTH) {
            for (y in 0 until SCREEN_HEIGHT) {
                val wx = x + left
                val wy = y + top

                panel.writeChar(world!!.glyph(wx, wy), x, y, world!!.color(wx, wy))
            }
        }
    }

    private fun scrollBy(mx: Int, my: Int) {
        Log.w("ScrollBy", "centerX  + mx = " + (centerX + mx).toString())
        Log.w("ScrollBy", "world!!.width() - 1 = " + (world!!.width() - 1).toString())
        Log.w("ScrollBy", "centerY  + my = " + (centerY + my).toString())
        Log.w("ScrollBy", "world!!.height() - 1 = " + (world!!.height() - 1).toString())

        centerX = Math.max(0, Math.min(centerX + mx, world!!.width() - 1))
        centerY = Math.max(0, Math.min(centerY + my, world!!.height() - 1))

        Log.w("ScrollBy", "updated centerX = " + centerX.toString())
        Log.w("ScrollBy", "updated centerY = " + centerY.toString())
    }

    init {
        createWorld()
    }

    override fun displayOutput() {
        val left = getScrollX()
        val top = getScrollY()

        displayTiles(left, top)
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen {
        val right = SCREEN_WIDTH - x!!
        val left = x!!
        val up = SCREEN_HEIGHT - y!!
        val down = y!!
        val dest = Math.min(Math.min(right, left), Math.min(up, down))

        Log.w("PlayScreen", "x = " + x!!.toString())
        Log.w("PlayScreen", "y = " + y!!.toString())
        Log.w("PlayScreen", "right = " + right.toString())
        Log.w("PlayScreen", "left = " + left.toString())
        Log.w("PlayScreen", "up = " + up.toString())
        Log.w("PlayScreen", "down = " + down.toString())

        when (dest) {
            right -> scrollBy(1, 0)
            left -> scrollBy(-1, 0)
            up -> scrollBy(0, 1)
            down -> scrollBy(0, -1)
        }

        Log.w("PlayScreen", "updated centerX = " + centerX.toString())
        Log.w("PlayScreen", "updated centerY = " + centerY.toString())

        displayOutput()
        return this
    }
}
