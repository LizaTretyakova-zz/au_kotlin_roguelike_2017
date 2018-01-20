package com.example.liza.myexamapp.Screens

import android.graphics.Color
import com.prokkypew.asciipanelview.AsciiPanelView


class WinScreen(panelView: AsciiPanelView) : Screen(panelView) {

    override fun displayOutput() {
        panel.clear()
        panel.writeCenter("Today you won, my young apprentice.", 1, Color.YELLOW, null)
        panel.writeCenter("But be mindful, and beware of", 2, Color.YELLOW, null)
        panel.writeCenter("pride, arrogance, and", 3, Color.YELLOW, null)
        panel.writeCenter("Disney Studios making a new SW movie", 4, Color.YELLOW, null)
        panel.writeCenter("every coming year.", 5, Color.YELLOW, null)
        panel.writeCenter("-- [restart] --", 10, Color.WHITE)
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen =
        if (char.charColor == Color.WHITE) PlayScreen(panel) else this
}
