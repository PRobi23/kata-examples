package action

import models.Character
import models.State
import models.maxHealth

class HealthAction(private val healer: Character) : Action {
    fun health(characterToHeal: Character, healthLevel: Int) {
        if (healer == characterToHeal) {
            val afterHealth = characterToHeal.health + healthLevel
            if (afterHealth > maxHealth && characterToHeal.state != State.DEAD) {
                characterToHeal.health = maxHealth
            }
        }
    }
}