package com.example.liza.myexamapp.Items

import com.example.liza.myexamapp.World.World

class ItemFactory(private val world: World) {
    fun newKyberCrystalRed() = world.addItemAtEmptyLocation(Item(Item.KYBER_CRYSTAL_RED, Item.KEY_RED))
    fun newKyberCrystalYellow() = world.addItemAtEmptyLocation(Item(Item.KYBER_CRYSTAL_YELLOW, Item.KEY_YELLOW))
    fun newKyberCrystalGreen() = world.addItemAtEmptyLocation(Item(Item.KYBER_CRYSTAL_GREEN, Item.KEY_GREEN))
    fun newKyberCrystalBlue() = world.addItemAtEmptyLocation(Item(Item.KYBER_CRYSTAL_BLUE, Item.KEY_BLUE))
}