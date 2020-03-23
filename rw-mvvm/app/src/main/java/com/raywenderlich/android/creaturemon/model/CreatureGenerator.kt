package com.raywenderlich.android.creaturemon.model

import android.support.annotation.DrawableRes

class CreatureGenerator {

  fun generateCreature(attributes: CreatureAttributes, name: String = "", @DrawableRes drawable: Int = 0): Creature {
    val hitPoints = 5 * attributes.intelligence +
        3 * attributes.strength +
        4 * attributes.endurance

    return Creature(attributes, hitPoints, name, drawable)
  }
}