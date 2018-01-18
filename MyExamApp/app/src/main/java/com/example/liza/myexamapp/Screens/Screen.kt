package com.example.liza.myexamapp.Screens

import android.view.KeyEvent
import com.prokkypew.asciipanelview.AsciiPanelView

abstract class Screen(var panel: AsciiPanelView) {
    abstract fun displayOutput()

    abstract fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen
}
