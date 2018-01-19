package com.example.liza.myexamapp.Utils

import android.graphics.Color
import com.prokkypew.asciipanelview.AsciiPanelView

//import com.example.liza.myexamapp.ApplicationMain
//
//fun getString(stringId: Int): String {
//    return ApplicationMain.context.getString(stringId)
//}

val WALL = AsciiPanelView.ColoredChar(219.toChar(), Color.CYAN, Color.LTGRAY)
val FLOOR = AsciiPanelView.ColoredChar('.', Color.WHITE, AsciiPanelView.DEFAULT_BG_COLOR)
val BOUNDS = AsciiPanelView.ColoredChar('x', AsciiPanelView.DEFAULT_CHAR_COLOR, AsciiPanelView.DEFAULT_BG_COLOR)
val MIDI_CHLORIAN = AsciiPanelView.ColoredChar(7.toChar(), Color.WHITE, AsciiPanelView.DEFAULT_BG_COLOR)