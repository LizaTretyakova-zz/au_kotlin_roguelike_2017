package com.example.liza.myexamapp.Screens

import android.graphics.Color
import com.prokkypew.asciipanelview.AsciiPanelView


class LoseScreen(panelView: AsciiPanelView) : Screen(panelView) {

    override fun displayOutput() {
        panel.clear()
        panel.writeString("You've lost, young padawan.", 1, 2, Color.YELLOW)
        panel.writeString("But remember, that patience", 1, 3, Color.YELLOW)
        panel.writeString("is the truly Jedi way,", 1, 4, Color.YELLOW)
        panel.writeString("so let the Force guide you,", 1, 5, Color.YELLOW)
        panel.writeString("and try again.", 1, 6, Color.YELLOW)
        panel.writeCenter("-- [restart] --", 22)
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen =
            if (char.charColor == Color.WHITE) PlayScreen(panel) else this
}
