package com.example.liza.myexamapp.Screens

import android.app.Activity;
import android.content.ContextWrapper
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView
import com.example.liza.myexamapp.Items.ItemFactory
import com.prokkypew.asciipanelview.AsciiPanelView
import com.example.liza.myexamapp.World.World
import com.example.liza.myexamapp.World.WorldBuilder
import com.example.liza.myexamapp.LifeForms.Creature
import com.example.liza.myexamapp.LifeForms.CreatureFactory
import com.example.liza.myexamapp.R

class PlayScreen(panelView: AsciiPanelView, contextWrapper: ContextWrapper) : Screen(panelView, contextWrapper) {
    companion object {
        internal const val SCREEN_WIDTH = 48
        internal const val SCREEN_HEIGHT = 26
        internal const val WORLD_WIDTH = 90
        internal const val WORLD_HEIGHT = 32
    }

    private var world: World
    private var player: Creature

    private fun getScrollX(): Int {
        return Math.max(0, Math.min(player.x!! - SCREEN_WIDTH / 2, world.width - SCREEN_WIDTH))
    }

    private fun getScrollY(): Int {
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
        world.items
                .filter {it.x!! >= left && it.x!! < left + SCREEN_WIDTH && it.y!! >= top && it.y!! < top + SCREEN_HEIGHT}
                .map { panel.writeChar(it.char.char, it.x!! - left, it.y!! - top, it.char.charColor) }
    }

    private fun displayStats() {
        val statsString = " Force: " + player.force.toString() + " power: " + player.power.toString()
        panel.clearRect(0.toChar(), 0, SCREEN_HEIGHT, SCREEN_WIDTH, 1)
        panel.writeString(statsString, 0, SCREEN_HEIGHT, Color.YELLOW)
    }

    private fun createLifeForms(creatureFactory: CreatureFactory) {
        for (i in 0..7) {
            // one healing midi-chlorian per trooper
            // but you'd better avoid these guys anyway ;)
            creatureFactory.newTrooper()
            world.addMidiChlorian()
        }
    }

    private fun bringCrystals(itemFactory: ItemFactory) {
        itemFactory.newKyberCrystalRed()
        itemFactory.newKyberCrystalYellow()
        itemFactory.newKyberCrystalGreen()
        itemFactory.newKyberCrystalBlue()
    }

    init {
        world = WorldBuilder(WORLD_WIDTH, WORLD_HEIGHT)
                .makeCaves()
                .build()
        val creatureFactory = CreatureFactory(world)
        val itemFactory = ItemFactory(world)

        player = creatureFactory.newPlayer()
        createLifeForms(creatureFactory)
        bringCrystals(itemFactory)
    }

    override fun displayOutput() {
        val left = getScrollX()
        val top = getScrollY()

        displayTiles(left, top)
        displayStats()
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

        world.update()

//        when {
//            player.isDead() -> return LoseScreen(panel)
//            player.isWinner() -> return WinScreen(panel)
//        }

        if(player.isWinner() || player.isDead()) {
            val layoutInflater = contextWrapper.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = layoutInflater.inflate(R.layout.popup, null)
            val popupWindow = PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

            val btnDismiss = popupView.findViewById(R.id.dismiss) as Button
            btnDismiss.setOnClickListener { popupWindow.dismiss() }

            val popupText = popupView.findViewById(R.id.text) as TextView
            popupText.text  =
                    if(player.isDead())
                        contextWrapper.getResources().getString(R.string.lose_text)
                    else
                        contextWrapper.getResources().getString(R.string.win_text)

            popupWindow.showAtLocation(panel, Gravity.CENTER, 0, 0)
            popupWindow.update(0, 0, popupWindow.getWidth(), popupWindow.getHeight());
            return PlayScreen(panel, contextWrapper)
        }

        displayOutput()
        return this
    }
}
