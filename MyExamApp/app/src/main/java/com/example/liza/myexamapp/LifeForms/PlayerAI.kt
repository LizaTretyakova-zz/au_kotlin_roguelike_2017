package com.example.liza.myexamapp.LifeForms

import android.util.Log
import com.example.liza.myexamapp.World.Tile

class PlayerAI(creature: Creature) : CreatureAI(creature) {

    override fun onAttack(enemy: Creature) {
        this.creature.modifyForce(-enemy.power)
        enemy.die()
        this.creature.x = enemy.x
        this.creature.y = enemy.y
    }

    override fun canDig(tile: Tile): Boolean = creature.force - tile.sharpness > 0
    override fun onDig(tile: Tile) = this.creature.modifyForce(-tile.sharpness)
}
