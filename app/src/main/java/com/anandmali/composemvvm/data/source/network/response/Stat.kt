package com.anandmali.composemvvm.data.source.network.response


import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: StatX
) {

    val maxValue: Int
        get() = when (stat.name) {
            "hp" -> maxHp
            "attack" -> maxAttack
            "defense" -> maxDefense
            "special-attack" -> maxSpAttack
            "special-defense" -> maxSpDefense
            "speed" -> maxSpeed
            else -> baseStat
        }

    val name: String
        get() = when (stat.name) {
            "hp" -> "HP"
            "attack" -> "ATTACK"
            "defense" -> "DEFENCE"
            "special-attack" -> "SPECIAL ATTACK"
            "special-defense" -> "SPECIAL DEFENSE"
            "speed" -> "SPEED"
            else -> stat.name
        }

    companion object {
        const val maxHp = 100
        const val maxAttack = 100
        const val maxDefense = 100
        const val maxSpAttack = 100
        const val maxSpDefense = 100
        const val maxSpeed = 100
    }
}