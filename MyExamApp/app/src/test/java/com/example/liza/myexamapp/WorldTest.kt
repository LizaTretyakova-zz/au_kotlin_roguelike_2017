package com.example.liza.myexamapp

import com.example.liza.myexamapp.Items.ItemFactory
import com.example.liza.myexamapp.LifeForms.Creature
import com.example.liza.myexamapp.LifeForms.CreatureFactory
import com.example.liza.myexamapp.World.Tile
import com.example.liza.myexamapp.World.World
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class WorldTest {
    var tiles = Array(4) { Array(4) { Tile.FLOOR } }
    lateinit var world: World
    lateinit var creatureFactory: CreatureFactory
    lateinit var itemFactory: ItemFactory
    lateinit var jedi: Creature
    lateinit var trooper: Creature

    @Before
    fun setUp() {
        tiles[3][0] = Tile.JEDI
        tiles[2][0] = Tile.MIDI_CHLORIAN
        tiles[1][0] = Tile.TROOPER

        world = World(tiles)

        creatureFactory = CreatureFactory(world)
        itemFactory = ItemFactory(world)

        jedi = creatureFactory.newPlayer()
        trooper = creatureFactory.newTrooper()
        itemFactory.newKyberCrystalBlue()
    }

    @Test
    fun setupTest() {
        assertEquals(jedi.force, Creature.JEDI_FORCE)
        assertEquals(jedi.power, Creature.JEDI_POWER)
        assertEquals(trooper.force, Creature.TROOPER_FORCE)
        assertEquals(trooper.power, Creature.TROOPER_POWER)

        assertNotNull(world.item(world.items[0].x!!, world.items[0].y!!))
        assertNull(world.item((world.items[0].x!! + 1) % 4, (world.items[0].y!! + 1) % 4))

        assertNotNull(world.creature(trooper.x!!, trooper.y!!))
        assertNull(world.creature(4  - trooper.x!!, 4 - trooper.y!!))

        assertEquals(world.tile(20, 20), Tile.BOUNDS)
        assertEquals(world.tile(jedi.x!!, jedi.y!!), Tile.FLOOR)
    }

}