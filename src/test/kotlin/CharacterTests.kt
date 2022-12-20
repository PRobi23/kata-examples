import models.MeleeFighter
import models.RangedFighter
import models.State
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CharacterTests {

    @Test
    fun `test default melee fighter`() {
        // given
        val char = MeleeFighter()

        // then
        assertEquals(1000, char.health)
        assertEquals(1, char.level)
        assertEquals(State.ALIVE, char.state)
        assertEquals (2, char.maxRange)
    }

    @Test
    fun `test default ranged fighter`() {
        // given
        val char = RangedFighter()

        // then
        assertEquals(1000, char.health)
        assertEquals(1, char.level)
        assertEquals(State.ALIVE, char.state)
        assertEquals(20, char.maxRange)
    }
}