package com.raywenderlich.android.creaturemon.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.CreatureAttributes
import com.raywenderlich.android.creaturemon.model.CreatureGenerator
import com.raywenderlich.android.creaturemon.model.CreatureRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CreatureViewModelTest {

  private lateinit var creatureViewModel: CreatureViewModel

  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  @Mock
  lateinit var mockGenerator: CreatureGenerator

  @Mock
  lateinit var creatureRepository: CreatureRepository

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    creatureViewModel = CreatureViewModel(mockGenerator, creatureRepository)
  }

  @Test
  fun testSetupCreature() {
    val attributes = CreatureAttributes(10, 3, 7)
    val stubCreature = Creature(attributes, 87, "Test Creature")

    `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

    creatureViewModel.intelligence = 10
    creatureViewModel.strength = 3
    creatureViewModel.endurance = 7

    creatureViewModel.updateCreature()

    assertEquals(stubCreature, creatureViewModel.creature)
  }

  @Test
  fun testCantSaveCreatureWithBlankName() {
    setViewModelCreatureParameters(stubCreature.copy(name = ""))

    val canSaveCreature = creatureViewModel.canSaveCreature()

    assertEquals(false, canSaveCreature)
  }

  @Test
  fun testCantSaveCreatureWithoutIntelligence() {
    setViewModelCreatureParameters(stubCreature.copy(attributes = stubAttributes.copy(intelligence = 0)))

    val canSaveCreature = creatureViewModel.canSaveCreature()

    assertEquals(false, canSaveCreature)
  }

  @Test
  fun testCantSaveCreatureWithoutStrength() {
    setViewModelCreatureParameters(stubCreature.copy(attributes = stubAttributes.copy(strength = 0)))

    val canSaveCreature = creatureViewModel.canSaveCreature()

    assertEquals(false, canSaveCreature)
  }

  @Test
  fun testCantSaveCreatureWithoutEndurance() {
    setViewModelCreatureParameters(stubCreature.copy(attributes = stubAttributes.copy(endurance = 0)))

    val canSaveCreature = creatureViewModel.canSaveCreature()

    assertEquals(false, canSaveCreature)
  }

  @Test
  fun testCantSaveCreatureWithoutDrawable() {
    setViewModelCreatureParameters(stubCreature.copy(drawable = 0))

    val canSaveCreature = creatureViewModel.canSaveCreature()

    assertEquals(false, canSaveCreature)
  }

  private fun setViewModelCreatureParameters(creature: Creature) {
    creatureViewModel.intelligence = creature.attributes.intelligence
    creatureViewModel.strength = creature.attributes.strength
    creatureViewModel.endurance = creature.attributes.endurance
    creatureViewModel.drawable = creature.drawable
    creatureViewModel.name.set(creature.name)
  }

  companion object {
    val stubAttributes = CreatureAttributes(
        intelligence = 10,
        strength = 3,
        endurance = 7
    )

    val stubCreature = Creature(
        stubAttributes,
        87,
        "Stub Creature",
        1
    )
  }
}