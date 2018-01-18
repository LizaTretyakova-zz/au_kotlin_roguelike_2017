package com.example.liza.myexamapp.Screens

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import com.example.liza.myexamapp.R
import com.prokkypew.asciipanelview.AsciiPanelView


class WinScreen(panelView: AsciiPanelView) : Screen(panelView) {

    override fun displayOutput() {
        panel.writeString("You won.", 1, 1, null, null)
        panel.writeCenter("-- follow [the white character] to restart --", 22, Color.WHITE)
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen {
        return if (char.charColor == Color.WHITE) PlayScreen(panel) else this
    }
}
