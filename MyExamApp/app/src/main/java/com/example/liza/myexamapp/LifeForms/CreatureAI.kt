package com.example.liza.myexamapp.LifeForms

import android.util.Log
import com.example.liza.myexamapp.World.Tile

open class CreatureAI(protected var creature: Creature) {

    init {
        this.creature.ai = this
    }

    open fun onEnter(x: Int, y: Int, tile: Tile) {
        if (tile.isGround()) {
            creature.x = x
            creature.y = y
            if (tile.isInteractive()) {
                creature.modifyForce(tile.force)
                creature.world.ground(x, y)
            }
        } else if (tile.isDiggable() && canDig(tile)) {
            onDig(tile)
            creature.x = x
            creature.y = y
            creature.world.ground(x, y)
        }
    }

    open fun canDig(tile: Tile): Boolean = false
    open fun onDig(tile: Tile) {}
    fun onAttacked() {}
    fun onDeath() {}
    open fun onUpdate() {}
    open fun onAttack(enemy: Creature) {}
}
