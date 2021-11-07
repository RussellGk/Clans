package ru.skillbranch.gameofthrones.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["id","house"])
data class Relative(
    val id: String,
    val house: String
)