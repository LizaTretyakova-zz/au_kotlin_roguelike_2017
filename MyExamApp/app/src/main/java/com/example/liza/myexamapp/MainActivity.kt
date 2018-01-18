package com.example.liza.myexamapp

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager

import com.example.liza.myexamapp.Screens.Screen
import com.example.liza.myexamapp.Screens.StartScreen

import com.prokkypew.asciipanelview.AsciiPanelView

class MainActivity : AppCompatActivity(), AsciiPanelView.OnCharClickedListener {
    private lateinit var currentScreen: Screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        val panelView = findViewById(R.id.panelView) as AsciiPanelView
//        panelView.writeChar('g', 2, 2, Color.RED, Color.YELLOW)
//                .setCursorPosition(14, 15)
//                .writeCharWithColor('p', Color.RED, Color.YELLOW)
//                .writeString("We", 16, 17, Color.RED, Color.YELLOW)
//                .writeCenter("Center TEXT String", 5, Color.YELLOW, Color.CYAN)
//                .onCharClickedListener = this
        panelView.onCharClickedListener = this

        if (savedInstanceState == null) {
            currentScreen = StartScreen(panelView)
            currentScreen.displayOutput()
        }
    }

    override fun onCharClicked(x: Int?, y: Int?, char: AsciiPanelView.ColoredChar) {
        currentScreen = currentScreen.respondToUserInput(x, y, char)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }
}
