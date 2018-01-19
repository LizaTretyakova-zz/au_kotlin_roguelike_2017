package com.example.liza.myexamapp.Screens

import com.prokkypew.asciipanelview.AsciiPanelView
import com.example.liza.myexamapp.World.World
import com.example.liza.myexamapp.World.WorldBuilder
import android.util.Log
import com.example.liza.myexamapp.LifeForms.Creature
import com.example.liza.myexamapp.LifeForms.CreatureFactory




class PlayScreen(panelView: AsciiPanelView) : Screen(panelView) {
    companion object {
        internal val SCREEN_WIDTH = 48
        internal val SCREEN_HEIGHT = 26
        internal val WORLD_WIDTH = 90
        internal val WORLD_HEIGHT = 32
    }

    private var world: World
    private var player: Creature

    private fun createWorld() {
    }

    fun getScrollX(): Int {
        return Math.max(0, Math.min(player.x!! - SCREEN_WIDTH / 2, world.width() - SCREEN_WIDTH))
    }

    fun getScrollY(): Int {
        return Math.max(0, Math.min(player.y!! - SCREEN_HEIGHT / 2, world.height() - SCREEN_HEIGHT))
    }

    private fun displayTiles(left: Int, top: Int) {
        panel.clear()
        for (x in 0 until SCREEN_WIDTH) {
            for (y in 0 until SCREEN_HEIGHT) {
                val wx = x + left
                val wy = y + top

                panel.writeChar(world.glyph(wx, wy), x, y, world.color(wx, wy))
            }
        }
    }

    init {
        world = WorldBuilder(WORLD_WIDTH, WORLD_HEIGHT)
                .makeCaves()
                .build()
        val creatureFactory = CreatureFactory(world)
        player = creatureFactory.newPlayer()
    }

    override fun displayOutput() {
        val left = getScrollX()
        val top = getScrollY()

        displayTiles(left, top)
        if(player.x!! >= left && player.x!! < left + SCREEN_WIDTH && player.y!! >= top && player.y!! < top + SCREEN_HEIGHT) {
            panel.writeChar(player.char.char, player.x!! - left, player.y!! - top, player.char.charColor);
        }
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
            right -> player.moveBy(1, 0)
            left -> player.moveBy(-1, 0)
            up -> player.moveBy(0, 1)
            down -> player.moveBy(0, -1)
        }

        displayOutput()
        return this
    }
}
