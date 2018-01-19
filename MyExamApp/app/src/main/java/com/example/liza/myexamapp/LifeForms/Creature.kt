package com.example.liza.myexamapp.LifeForms

import com.example.liza.myexamapp.World.World
import com.example.liza.myexamapp.World.Tile

class Creature(
        val world: World,
        internal val char: Tile,
        val power: Int,
        var force: Int) {

    companion object {
        val CRYSTALS_NUMBER = 4
        val JEDI_FORCE = 10
        val JEDI_POWER = 10
        val TROOPER_FORCE = 0
        val TROOPER_POWER = 1
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

    fun update() {
        ai?.onUpdate()
    }

    fun attacked() {
        ai?.onAttacked()
    }

    fun modifyForce(delta: Int) {
        force += delta
        if (isDead()) {
            die()
        }
    }

    fun isDead(): Boolean {
        return force < 0
    }

    fun isWinner(): Boolean {
        return kyberCrystals >= CRYSTALS_NUMBER
    }

    fun moveBy(mx: Int, my: Int) {
        val inhabitant = world.creature(mx, my)
        when(inhabitant) {
            null -> ai!!.onEnter(x!! + mx, y!! + my, world.tile(x!! + mx, y!! + my))
            else -> attack(inhabitant)
        }
    }

}