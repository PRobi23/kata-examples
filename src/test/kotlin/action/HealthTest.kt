package action

import models.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class HealthTest {

    private val healer = MeleeFighter()
    private val sut = HealthAction(healer)

    @Test
    fun `when a character is dead it cannot be health`() {
        // given
        val deadCharacter = RangedFighter(health = 15, state = State.DEAD)

        // when
        sut.health(deadCharacter, 25)

        // then
        assertEquals(State.DEAD, deadCharacter.state)
        assertEquals(15, deadCharacter.health)
    }

    @Test
    fun `character cannot be health to more than 1000`() {
        // given

        // when
        sut.health(healer, 124)

        // then
        assertEquals(maxHealth, healer.health)
    }

    @Test
    fun `character can only heal itself`() {
        // given

        // when
        sut.health(healer, 1531)

        // then
        assertEquals(maxHealth, healer.health)
    }
}