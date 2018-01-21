package com.example.liza.myexamapp.LifeForms

import android.util.Log
import com.example.liza.myexamapp.Items.Item
import com.example.liza.myexamapp.World.Tile

class PlayerAI(creature: Creature) : CreatureAI(creature) {
    val kyberCrystals: MutableList<Boolean> = listOf(false, false, false, false).toMutableList()

    override fun onAttack(enemy: Creature) {
        Log.e("Player", "Enemy power " + enemy.power.toString())
        this.creature.modifyForce(-enemy.power)
        enemy.die()
        this.creature.x = enemy.x
        this.creature.y = enemy.y
    }

    override fun canDig(tile: Tile): Boolean = creature.force - tile.sharpness > 0
    override fun onDig(tile: Tile) = this.creature.modifyForce(-tile.sharpness)

    override fun onPick(item: Item) {
        Log.e("PlayerAI::onPick ", item.key.toString())
        if(item.isKyberCrystal() && item.key in 0..kyberCrystals.size) {
            this.kyberCrystals[item.key] = true
            this.creature.x = item.x
            this.creature.y = item.y
            this.creature.world.removeItem(item)
        }
    }

    override fun isWinner() =
            kyberCrystals[Item.KEY_RED]
                    && kyberCrystals[Item.KEY_YELLOW]
                    && kyberCrystals[Item.KEY_GREEN]
                    && kyberCrystals[Item.KEY_BLUE]
}
