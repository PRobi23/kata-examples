package models

data class RangedFighter(
    override val maxRange: Int = 20,
    override var health: Int = maxHealth,
    override val level: Int = 1,
    override var state: State = State.ALIVE
) : Character