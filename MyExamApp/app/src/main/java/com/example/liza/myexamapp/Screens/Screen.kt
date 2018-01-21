package com.example.liza.myexamapp.Screens

import android.content.ContextWrapper
import com.prokkypew.asciipanelview.AsciiPanelView

abstract class Screen(var panel: AsciiPanelView, val contextWrapper: ContextWrapper) {
    abstract fun displayOutput()

    abstract fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen
}
