package com.example.liza.myexamapp.Screens

import com.prokkypew.asciipanelview.AsciiPanelView
import com.example.liza.myexamapp.World.World
import com.example.liza.myexamapp.World.WorldBuilder
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

    fun getScrollX(): Int {
        return Math.max(0, Math.min(player.x!! - SCREEN_WIDTH / 2, world.width - SCREEN_WIDTH))
    }

    fun getScrollY(): Int {
        return Math.max(0, Math.min(player.y!! - SCREEN_HEIGHT / 2, world.height - SCREEN_HEIGHT))
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
        world.creatures
                .filter {it.x!! >= left && it.x!! < left + SCREEN_WIDTH && it.y!! >= top && it.y!! < top + SCREEN_HEIGHT}
                .map { panel.writeChar(it.char.char.char, it.x!! - left, it.y!! - top, it.char.char.charColor) }
    }

    private fun createLifeForms(creatureFactory: CreatureFactory) {
        for (i in 0..7) {
            creatureFactory.newTrooper()
        }
    }

    init {
        world = WorldBuilder(WORLD_WIDTH, WORLD_HEIGHT)
                .makeCaves()
                .build()
        val creatureFactory = CreatureFactory(world)
        player = creatureFactory.newPlayer()
        createLifeForms(creatureFactory)
    }

    override fun displayOutput() {
        val left = getScrollX()
        val top = getScrollY()

        displayTiles(left, top)
        if(player.x!! >= left && player.x!! < left + SCREEN_WIDTH && player.y!! >= top && player.y!! < top + SCREEN_HEIGHT) {
            panel.writeChar(player.char.char.char, player.x!! - left, player.y!! - top, player.char.char.charColor);
        }
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen {
        val right = SCREEN_WIDTH - x!!
        val left = x!!
        val up = SCREEN_HEIGHT - y!!
        val down = y!!
        val dest = Math.min(Math.min(right, left), Math.min(up, down))

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
