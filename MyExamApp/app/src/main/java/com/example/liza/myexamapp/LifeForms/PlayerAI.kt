package com.example.liza.myexamapp.LifeForms

import com.example.liza.myexamapp.World.Tile

class PlayerAI(creature: Creature) : CreatureAI(creature) {
    override fun onEnter(x: Int, y: Int, tile: Tile) {
        if (tile.isGround()) {
            creature.x = x
            creature.y = y
        } else if (tile.isDiggable()) {
            creature.dig(x, y)
        }
    }

    override fun onAttack(enemy: Creature) {
        this.creature.modifyForce(-enemy.power)
        enemy.die()
        this.creature.x = enemy.x
        this.creature.y = enemy.y
    }

}
