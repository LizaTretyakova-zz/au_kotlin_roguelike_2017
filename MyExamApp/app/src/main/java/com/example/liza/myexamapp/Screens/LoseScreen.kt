package com.example.liza.myexamapp.Screens

import android.content.ContextWrapper
import android.graphics.Color
import com.prokkypew.asciipanelview.AsciiPanelView


class LoseScreen(panelView: AsciiPanelView, contextWrapper: ContextWrapper) : Screen(panelView, contextWrapper) {

    override fun displayOutput() {
        panel.clear()
        panel.writeCenter("You've lost, young padawan.", 1, 2, Color.YELLOW)
        panel.writeCenter("But remember, that patience", 1, 3, Color.YELLOW)
        panel.writeCenter("is the truly Jedi way,", 1, 4, Color.YELLOW)
        panel.writeCenter("so let the Force guide you,", 1, 5, Color.YELLOW)
        panel.writeCenter("and try again.", 1, 6, Color.YELLOW)
        panel.writeCenter("-- [restart] --", 22)
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen =
            if (char.charColor == Color.WHITE) PlayScreen(panel, contextWrapper) else this
}
