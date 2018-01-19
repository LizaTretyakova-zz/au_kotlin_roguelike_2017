package com.example.liza.myexamapp.Utils

import android.graphics.Color
import com.prokkypew.asciipanelview.AsciiPanelView

//import com.example.liza.myexamapp.ApplicationMain
//
//fun getString(stringId: Int): String {
//    return ApplicationMain.context.getString(stringId)
//}

val WALL = AsciiPanelView.ColoredChar(250.toChar(), Color.CYAN, Color.CYAN)
val FLOOR = AsciiPanelView.ColoredChar('.', Color.WHITE, AsciiPanelView.DEFAULT_BG_COLOR)
val BOUNDS = AsciiPanelView.ColoredChar('x', AsciiPanelView.DEFAULT_CHAR_COLOR, AsciiPanelView.DEFAULT_BG_COLOR)
val MIDI_CHLORIAN = AsciiPanelView.ColoredChar(7.toChar(), Color.WHITE, AsciiPanelView.DEFAULT_BG_COLOR)
val JEDI = AsciiPanelView.ColoredChar('@', Color.WHITE, AsciiPanelView.DEFAULT_BG_COLOR)
val TROOPER = AsciiPanelView.ColoredChar('T', Color.WHITE, AsciiPanelView.DEFAULT_BG_COLOR)

fun isDiggable(tile: AsciiPanelView.ColoredChar): Boolean {
    return tile == WALL
}

fun isGround(tile: AsciiPanelView.ColoredChar): Boolean {
    return tile == FLOOR || tile == MIDI_CHLORIAN
}