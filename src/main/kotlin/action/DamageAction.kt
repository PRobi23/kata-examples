package action

import models.Character
import models.State

class DamageAction(private val attacker: Character) : Action {

    fun dealDamage(characterToDamage: Character, damage: Int) {
        if (attacker != characterToDamage) {
            if (damage > characterToDamage.health) {
                characterToDamage.state = State.DEAD
                characterToDamage.health = 0
            } else {
                characterToDamage.health -= computeDamage(
                    characterToDamage = characterToDamage,
                    attacker = attacker, damage = damage
                )
            }
        }
    }

    private fun computeDamage(characterToDamage: Character, attacker: Character, damage: Int): Int {
        return if (targetIsInsideTheRange(characterToDamage, attacker)) {
            when {
                targetIsFiveLevelAboveAttacker(characterToDamage, attacker) -> {
                    damage / 2
                }

                targetIsFiveLevelBelowAttacker(characterToDamage, attacker) -> {
                    damage * 2
                }

                else -> {
                    damage
                }
            }
        } else 0
    }

    private fun targetIsFiveLevelAboveAttacker(characterToDamage: Character, attacker: Character) =
        (characterToDamage.level - attacker.level) >= 5

    private fun targetIsFiveLevelBelowAttacker(characterToDamage: Character, attacker: Character) =
        (attacker.level - characterToDamage.level) >= 5

    private fun targetIsInsideTheRange(characterToDamage: Character, attacker: Character) =
        attacker.maxRange >= characterToDamage.maxRange
}