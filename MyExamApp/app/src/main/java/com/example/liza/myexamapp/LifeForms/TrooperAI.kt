package com.example.liza.myexamapp.LifeForms

class TrooperAI(creature: Creature) : CreatureAI(creature) {

    override fun onUpdate() {
        val dx = (Math.random() * 10).toInt() % 3 - 1;
        val dy = (Math.random() * 10).toInt() % 3 - 1;
        creature.moveBy(dx, dy)
    }
}
