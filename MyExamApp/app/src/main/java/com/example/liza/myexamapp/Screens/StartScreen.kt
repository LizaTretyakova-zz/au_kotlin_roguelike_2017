package com.example.liza.myexamapp.Screens

import android.graphics.Color
import com.prokkypew.asciipanelview.AsciiPanelView

class StartScreen(panelView: AsciiPanelView) : Screen(panelView) {

    override fun displayOutput() {
        panel.clear()
        panel.writeString("Somewhat a", 1, 1, Color.MAGENTA, null)
        panel.writeString("roguelike", 1, 2, Color.MAGENTA, null)
        panel.writeCenter("-- [start] --", 10, Color.WHITE)
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen {
        return if (char.charColor == Color.WHITE) PlayScreen(panel) else this
    }
}
