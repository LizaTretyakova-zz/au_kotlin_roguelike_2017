package com.example.liza.myexamapp.Screens

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import com.example.liza.myexamapp.R
import com.prokkypew.asciipanelview.AsciiPanelView


class WinScreen(panelView: AsciiPanelView) : Screen(panelView) {

    override fun displayOutput() {
        panel.clear()
        panel.writeString("You won.", 1, 1, Color.MAGENTA, null)
        panel.writeCenter("-- [restart] --", 10, Color.WHITE)
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen {
        return if (char.charColor == Color.WHITE) PlayScreen(panel) else this
    }
}
