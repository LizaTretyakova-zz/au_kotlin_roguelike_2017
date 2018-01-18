package com.example.liza.myexamapp.Screens

import android.view.KeyEvent
import com.prokkypew.asciipanelview.AsciiPanelView

class PlayScreen(panelView: AsciiPanelView) : Screen(panelView) {

    override fun displayOutput() {
        panel.writeString("You are having fun.", 1, 1, null, null)
        panel.writeCenter("-- follow the [X] character to lose or [V] to win --", 22)
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen {
        when (char.char) {
            'X' -> return LoseScreen(panel)
            'V' -> return WinScreen(panel)
        }

        return this
    }
}
