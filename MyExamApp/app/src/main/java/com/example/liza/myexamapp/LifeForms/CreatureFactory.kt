package com.example.liza.myexamapp.LifeForms

import com.example.liza.myexamapp.World.Tile
import com.example.liza.myexamapp.World.World

class CreatureFactory(private val world: World) {
    fun newPlayer(): Creature {
        val player = Creature(world, Tile.JEDI, Creature.JEDI_FORCE, Creature.JEDI_POWER)
        world.addAtEmptyLocation(player)
        PlayerAI(player)
        return player
    }

    fun newTrooper(): Creature {
        val trooper = Creature(world, Tile.TROOPER, Creature.TROOPER_FORCE, Creature.TROOPER_POWER)
        world.addAtEmptyLocation(trooper)
        TrooperAI(trooper)
        return trooper
    }
}
