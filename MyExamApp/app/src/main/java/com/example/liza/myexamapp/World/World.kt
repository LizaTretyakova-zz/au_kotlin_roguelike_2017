package com.example.liza.myexamapp.World

import com.example.liza.myexamapp.LifeForms.Creature
import com.example.liza.myexamapp.Screens.PlayScreen.Companion.SCREEN_HEIGHT
import com.example.liza.myexamapp.Screens.PlayScreen.Companion.SCREEN_WIDTH





class World(private val tiles: Array<Array<Tile>>) {
    val width: Int = tiles.size
    val height: Int = tiles[0].size
    val creatures: MutableList<Creature> = listOf<Creature>().toMutableList()

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

    fun creature(x: Int, y: Int): Creature? {
        return creatures.find { it.x == x && it.y == y }
    }

    fun addAtEmptyLocation(creature: Creature) {
        var x: Int
        var y: Int

        do {
            x = (Math.random() * SCREEN_WIDTH).toInt()
            y = (Math.random() * SCREEN_HEIGHT).toInt()
        } while (!tile(x, y).isGround())

        creature.x = x
        creature.y = y
        creatures.add(creature);
    }

    fun remove(creature: Creature) {
        creatures.remove(creature)
    }
}