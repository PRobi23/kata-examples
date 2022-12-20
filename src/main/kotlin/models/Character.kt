package models

const val maxHealth = 1000

interface Character {
    var health: Int
    val level: Int
    var state: State
    val maxRange: Int
}
