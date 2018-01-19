package com.example.liza.myexamapp.World

import android.graphics.Color
import com.prokkypew.asciipanelview.AsciiPanelView

enum class Tile(var char: AsciiPanelView.ColoredChar, val force: Int, val sharpness: Int) {
    WALL(AsciiPanelView.ColoredChar(250.toChar(), Color.CYAN, Color.CYAN), 0, 1),
    FLOOR(AsciiPanelView.ColoredChar('.', Color.WHITE, AsciiPanelView.DEFAULT_BG_COLOR), 0, 0),
    BOUNDS(AsciiPanelView.ColoredChar('x', AsciiPanelView.DEFAULT_CHAR_COLOR, AsciiPanelView.DEFAULT_BG_COLOR), 0, 0),
    MIDI_CHLORIAN(AsciiPanelView.ColoredChar('*', Color.WHITE, AsciiPanelView.DEFAULT_BG_COLOR), 1, 0),
    JEDI(AsciiPanelView.ColoredChar('@', Color.WHITE, AsciiPanelView.DEFAULT_BG_COLOR), 0, 0),
    TROOPER(AsciiPanelView.ColoredChar('T', Color.WHITE, AsciiPanelView.DEFAULT_BG_COLOR), 0, 0);

    fun isDiggable(): Boolean = this == WALL
    fun isWalkable(): Boolean = this == FLOOR || this == MIDI_CHLORIAN
    fun isGround(): Boolean = this == FLOOR
    fun isInteractive(): Boolean = this == MIDI_CHLORIAN
}