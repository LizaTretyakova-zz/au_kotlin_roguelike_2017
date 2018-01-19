package com.example.liza.myexamapp.LifeForms

import android.util.Log
import com.example.liza.myexamapp.World.World
import com.example.liza.myexamapp.World.Tile

class Creature(
        val world: World,
        internal val char: Tile,
        var force: Int,
        val power: Int) {

    companion object {
        const val CRYSTALS_NUMBER = 4
        const val JEDI_FORCE = 10
        const val JEDI_POWER = 10
        const val TROOPER_FORCE = 0
        const val TROOPER_POWER = 1
    }

    var ai: CreatureAI? = null
    var x: Int? = null
    var y: Int? = null
    private var kyberCrystals: Int = 0

    fun dig(wx: Int, wy: Int) {
        if(world.dig(wx, wy)) {
            x = wx
            y = wy
        }
    }

    fun attack(creature: Creature) {
        ai?.onAttack(creature)
        creature.attacked()
    }

    fun die() {
        world.remove(this)
        ai?.onDeath()
    }

    fun update() = ai?.onUpdate()
    fun attacked() = ai?.onAttacked()
    fun isDead(): Boolean = force < 0
    fun isWinner(): Boolean = kyberCrystals >= CRYSTALS_NUMBER

    fun modifyForce(delta: Int) {
        force += delta
        Log.e("Creature", "Force modified to " + force.toString() + " d=" + delta.toString())
        if (isDead()) {
            die()
        }
    }

    fun moveBy(mx: Int, my: Int) {
        if(world.tile(x!! + mx, y!! + my) == Tile.BOUNDS) return

        val inhabitant = world.creature(x!! + mx, y!! + my)
        when(inhabitant) {
            null -> ai!!.onEnter(x!! + mx, y!! + my, world.tile(x!! + mx, y!! + my))
            else -> attack(inhabitant)
        }
    }

}