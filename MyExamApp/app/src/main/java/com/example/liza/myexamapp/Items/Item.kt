package com.example.liza.myexamapp.Items

import android.graphics.Color
import com.prokkypew.asciipanelview.AsciiPanelView

open class Item(val char: AsciiPanelView.ColoredChar, val key: Int) {
    companion object {
        val KYBER_CRYSTAL_RED = AsciiPanelView.ColoredChar('#', Color.RED, AsciiPanelView.DEFAULT_BG_COLOR)
        val KYBER_CRYSTAL_YELLOW = AsciiPanelView.ColoredChar('#', Color.YELLOW, AsciiPanelView.DEFAULT_BG_COLOR)
        val KYBER_CRYSTAL_GREEN = AsciiPanelView.ColoredChar('#', Color.GREEN, AsciiPanelView.DEFAULT_BG_COLOR)
        val KYBER_CRYSTAL_BLUE = AsciiPanelView.ColoredChar('#', Color.BLUE, AsciiPanelView.DEFAULT_BG_COLOR)

        const val KEY_RED = 0
        const val KEY_YELLOW = 1
        const val KEY_GREEN = 2
        const val KEY_BLUE = 3
    }

    var x: Int? = null
    var y: Int? = null

    fun isKyberCrystal(): Boolean =
            this.key in KEY_RED..KEY_BLUE
                    && (this.char == KYBER_CRYSTAL_RED
                    || this.char == KYBER_CRYSTAL_YELLOW
                    || this.char == KYBER_CRYSTAL_GREEN
                    || this.char == KYBER_CRYSTAL_BLUE)
}