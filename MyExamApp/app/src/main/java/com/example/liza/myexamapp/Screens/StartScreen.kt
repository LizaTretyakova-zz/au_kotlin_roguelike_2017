package com.example.liza.myexamapp.Screens

import android.graphics.Color
import com.prokkypew.asciipanelview.AsciiPanelView

class StartScreen(panelView: AsciiPanelView) : Screen(panelView) {

    override fun displayOutput() {
        panel.clear()
        panel.writeCenter("Welcome to the Crystal Caves of the ice planet", 1, Color.YELLOW)
        panel.writeCenter("of Ilum, young Padawan!", 2, Color.YELLOW)
        panel.writeCenter("You were sent to accomplish an essential", 3, Color.YELLOW)
        panel.writeCenter("part of your Jedi training -- ", 4, Color.YELLOW)
        panel.writeCenter("to construct your own lightsaber.", 5, Color.YELLOW)
        panel.writeCenter("In order to do this you need to harvest some", 6, Color.YELLOW)
        panel.writeCenter("kyber crystals which you'll find in the Caves.", 7, Color.YELLOW)
        panel.writeCenter("", 8, Color.YELLOW)
        panel.writeCenter("Beware of the imperial troopers, they are", 9, Color.YELLOW)
        panel.writeCenter("always around. And mind it that you can always", 10, Color.YELLOW)
        panel.writeCenter("dig into the Caves' walls,", 11, Color.YELLOW)
        panel.writeCenter("though it will cost you some Force.", 12, Color.YELLOW)
        panel.writeCenter("", 13, Color.YELLOW)
        panel.writeCenter("Good luck. And may the Force be with you!", 14, Color.YELLOW)
        panel.writeCenter("-- press [here] to start --", 20, Color.WHITE)
    }

    override fun respondToUserInput(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar): Screen =
        if (char.charColor == Color.WHITE) PlayScreen(panel) else this
}
