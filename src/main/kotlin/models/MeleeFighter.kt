package models

data class MeleeFighter(
    override val maxRange: Int = 2,
    override var health: Int = maxHealth,
    override val level: Int = 1,
    override var state: State = State.ALIVE
) : Character