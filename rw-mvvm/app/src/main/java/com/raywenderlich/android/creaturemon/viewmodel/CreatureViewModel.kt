package com.raywenderlich.android.creaturemon.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.DrawableRes
import com.raywenderlich.android.creaturemon.model.*

class CreatureViewModel(private val generator: CreatureGenerator = CreatureGenerator()) : ViewModel() {

  private val creatureLiveData = MutableLiveData<Creature>()

  fun getCreatureLiveData(): LiveData<Creature> = creatureLiveData

  var name = ""
  var intelligence = 0
  var strength = 0
  var endurance = 0
  @DrawableRes
  var drawable = 0

  lateinit var creature: Creature

  fun updateCreature() {
    val attributes = CreatureAttributes(intelligence, strength, endurance)
    creature = generator.generateCreature(attributes, name, drawable)
    creatureLiveData.postValue(creature)
  }

  fun attributeSelected(attributeType: AttributeType, position: Int) {
    when (attributeType) {
      AttributeType.INTELLIGENCE ->
        intelligence = AttributeStore.INTELLIGENCE[position].value
      AttributeType.STRENGTH ->
        strength = AttributeStore.STRENGTH[position].value
      AttributeType.ENDURANCE ->
        endurance = AttributeStore.ENDURANCE[position].value
    }
    updateCreature()
  }

  fun drawableSelected(@DrawableRes drawable: Int) {
    this.drawable = drawable
    updateCreature()
  }
}