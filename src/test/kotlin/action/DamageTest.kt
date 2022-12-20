package action

import models.Character
import models.MeleeFighter
import models.RangedFighter
import models.State
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DamageTest {

    private val defaultAttacker = RangedFighter()
    private fun sut(attacker: Character = defaultAttacker) = DamageAction(attacker = attacker)

    @Test
    fun `when damage is called it is subtracted from health`() {
        // given
        val charToDamage = RangedFighter(health = 555)

        // when
        sut(defaultAttacker).dealDamage(charToDamage, 525)

        // then
        assertEquals(30, charToDamage.health)
    }

    @Test
    fun `when damage is above current health the health becomes zero and char dies`() {
        // given
        val charToDamage = MeleeFighter(health = 555)
        // when
        sut(defaultAttacker).dealDamage(charToDamage, 585)

        // then
        assertEquals(0, charToDamage.health)
        assertEquals(State.DEAD, charToDamage.state)
    }

    @Test
    fun `a character cannot damage itself`() {
        // when
        sut(defaultAttacker).dealDamage(defaultAttacker, 585)

        // then
        assertEquals(1000, defaultAttacker.health)
        assertEquals(State.ALIVE, defaultAttacker.state)
    }

    @Test
    fun `if target is 5 or more level above attacker attack is reduced by 50 percent`() {
        // given
        val charToDamage = MeleeFighter(health = 555, level = 6)

        // when
        sut(
            defaultAttacker.copy(level = 1)
        ).dealDamage(charToDamage, 40)

        // then
        assertEquals(535, charToDamage.health)
        assertEquals(State.ALIVE, charToDamage.state)
    }

    @Test
    fun `if target is 5 or more level below attacker attack is increased by 50 percent`() {
        // given
        val charToDamage = MeleeFighter(health = 555, level = 1)

        // when
        sut(
            defaultAttacker.copy(level = (charToDamage.level + 6..15).random())
        ).dealDamage(charToDamage, 15)

        // then
        assertEquals(525, charToDamage.health)
        assertEquals(State.ALIVE, charToDamage.state)
    }

    @Test
    fun `if melee fighter attacks the char must be inside 2 meters otherwise it has no affect`() {
        // given
        val charToDamage = RangedFighter(health = 555, level = 1)

        // when
        sut(MeleeFighter()).dealDamage(charToDamage, 15)

        // then
        assertEquals(555, charToDamage.health)
        assertEquals(State.ALIVE, charToDamage.state)
    }

    @Test
    fun `if melee fighter attacks the char must be inside 2 meters`() {
        // given
        val charToDamage = RangedFighter(health = 555, level = 1)

        // when
        sut(MeleeFighter()).dealDamage(charToDamage, 15)

        // then
        assertEquals(555, charToDamage.health)
        assertEquals(State.ALIVE, charToDamage.state)
    }

    @Test
    fun `if ranged fighter attacks the char must be inside 20 meters otherwise it has no affect`() {
        // given
        val charToDamage = RangedFighter(health = 555, level = 1, maxRange = 55)

        // when
        sut(RangedFighter()).dealDamage(charToDamage, 15)

        // then
        assertEquals(555, charToDamage.health)
        assertEquals(State.ALIVE, charToDamage.state)
    }

    @Test
    fun `if ranged fighter attacks the char must be inside 20 meters`() {
        // given
        val charToDamage = MeleeFighter(health = 555, level = 1)

        // when
        sut(RangedFighter()).dealDamage(charToDamage, 15)

        // then
        assertEquals(540, charToDamage.health)
        assertEquals(State.ALIVE, charToDamage.state)
    }
}