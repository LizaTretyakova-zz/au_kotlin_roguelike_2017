package com.example.liza.myexamapp.Screens

import android.graphics.Color
import com.prokkypew.asciipanelview.AsciiPanelView


class LoseScreen(panelView: AsciiPanelView) : Screen(panelView) {

    override fun displayOutput() {
        panel.writeString("You lost.", 1, 1, null, null)
        panel.writeCenter("-- follow [the white character] to restart --", 22, Color.WHITE)
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen {
        return if (char.charColor == Color.WHITE) PlayScreen(panel) else this
    }
}
