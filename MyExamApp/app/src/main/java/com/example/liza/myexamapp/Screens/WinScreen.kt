package com.example.liza.myexamapp.Screens

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import com.example.liza.myexamapp.R
import com.prokkypew.asciipanelview.AsciiPanelView


class WinScreen(panelView: AsciiPanelView) : Screen(panelView) {

    override fun displayOutput() {
        panel.clear()
        panel.writeString("Today you won, my young apprentice. But be mindful,", 1, 1, Color.YELLOW, null)
        panel.writeString("and beware of pride, arrogance, and", 1, 2, Color.YELLOW, null)
        panel.writeString("Disney Studios making a new SW movie every year.", 1, 3, Color.YELLOW, null)
        panel.writeCenter("-- [restart] --", 10, Color.WHITE)
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen {
        return if (char.charColor == Color.WHITE) PlayScreen(panel) else this
    }
}
