package com.example.liza.myexamapp.World

import com.example.liza.myexamapp.Screens.PlayScreen


class WorldBuilder(private val width: Int = PlayScreen.SCREEN_WIDTH, private val height: Int = PlayScreen.SCREEN_HEIGHT) {
    private var tiles: Array<Array<Tile>>

    init {
        tiles = Array(width) { Array(height) { Tile.FLOOR } }
    }

    fun build(): World = World(tiles)
    fun makeCaves(): WorldBuilder = randomizeTiles().smooth(8)

    private fun randomizeTiles(): WorldBuilder {
        for (x in 0 until width) {
            for (y in 0 until height) {
                tiles[x][y] = if (Math.random() < 0.5) Tile.FLOOR else Tile.WALL
            }
        }
        return this
    }

    private fun smooth(times: Int): WorldBuilder {
        val tiles2 = Array<Array<Tile>>(width) { Array(height) { Tile.FLOOR } }
        for (time in 0 until times) {

            for (x in 0 until width) {
                for (y in 0 until height) {
                    var floors = 0
                    var rocks = 0

                    for (ox in -1..1) {
                        for (oy in -1..1) {
                            if (x + ox < 0 || x + ox >= width || y + oy < 0
                                    || y + oy >= height)
                                continue

                            if (tiles[x + ox][y + oy] === Tile.FLOOR)
                                floors++
                            else
                                rocks++
                        }
                    }
                    tiles2[x][y] = if (floors >= rocks) Tile.FLOOR else Tile.WALL
                }
            }
            tiles = tiles2
        }
        return this
    }
}