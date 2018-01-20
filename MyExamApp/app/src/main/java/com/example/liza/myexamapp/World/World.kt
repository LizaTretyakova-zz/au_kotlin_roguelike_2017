package com.example.liza.myexamapp.World

import android.util.Log
import com.example.liza.myexamapp.Items.Item
import com.example.liza.myexamapp.LifeForms.Creature
import com.example.liza.myexamapp.Screens.PlayScreen.Companion.SCREEN_HEIGHT
import com.example.liza.myexamapp.Screens.PlayScreen.Companion.SCREEN_WIDTH

class World(private val tiles: Array<Array<Tile>>) {
    val width: Int = tiles.size
    val height: Int = tiles[0].size
    val creatures: MutableList<Creature> = listOf<Creature>().toMutableList()
    val items: MutableList<Item> = listOf<Item>().toMutableList()

    fun tile(x: Int, y: Int): Tile {
        return if (x < 0 || x >= width || y < 0 || y >= height)
            Tile.BOUNDS
        else
            tiles[x][y]
    }

    fun glyph(x: Int, y: Int): Char {
        return tile(x, y).char.char
    }

    fun color(x: Int, y: Int): Int {
        return tile(x, y).char.charColor
    }

    fun dig(x: Int, y: Int): Boolean {
        if (tile(x, y).isDiggable()) {
            tiles[x][y] = Tile.FLOOR
            return true
        }
        return false
    }

    fun ground(x: Int, y: Int) {
        if(tile(x, y) != Tile.BOUNDS) {
            tiles[x][y] = Tile.FLOOR
        }
    }

    fun creature(x: Int, y: Int): Creature? =
            creatures.find { it.x == x && it.y == y }

    fun item(x: Int, y: Int): Item? {
//        items.find { it.x == x && it.y == y }
        Log.e("World::item -- x, y:", "(" + x.toString() + ", " + y.toString() + ")")
        for (item in items) {
            Log.e("World::item", "(" + item.x.toString() + ", " + item.y.toString() + ")")
            if(item.x == x && item.y == y) return item
        }
        return null
    }

    private fun findPlace(): Pair<Int, Int> {
        var x: Int
        var y: Int

        do {
            x = (Math.random() * SCREEN_WIDTH).toInt()
            y = (Math.random() * SCREEN_HEIGHT).toInt()
        } while (!tile(x, y).isGround())

        return Pair(x, y)
    }

    fun addCreatureAtEmptyLocation(creature: Creature) {
        val place = findPlace()

        creature.x = place.first
        creature.y = place.second
        creatures.add(creature);
    }

    fun addItemAtEmptyLocation(item: Item) {
        val place = findPlace()

        item.x = place.first
        item.y = place.second
        items.add(item);
    }

    fun addMidiChlorian() {
        val place = findPlace()
        tiles[place.first][place.second] = Tile.MIDI_CHLORIAN
    }

    fun update() = creatures.map { it.update() }

    fun removeCreature(creature: Creature) = creatures.remove(creature)
    fun removeItem(item: Item) = items.remove(item)
}