package com.example.liza.myexamapp.Screens

import android.graphics.Color
import android.view.KeyEvent
import com.prokkypew.asciipanelview.AsciiPanelView

class PlayScreen(panelView: AsciiPanelView) : Screen(panelView) {

    override fun displayOutput() {
        panel.writeString("So fun.", 1, 1, Color.MAGENTA, null)
        panel.writeCenter("-- [lose] --", 10, Color.RED, null)
        panel.writeCenter("-- [win] --", 11, Color.GREEN, null)
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen {
        when (char.charColor) {
            Color.RED -> return LoseScreen(panel)
            Color.GREEN -> return WinScreen(panel)
        }

        return this
    }
}
