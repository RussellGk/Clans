package ru.skillbranch.gameofthrones.data.local.entities

import androidx.room.*

@Entity
data class Character(
        @PrimaryKey val id: String,
        val name: String,
        val born: String,
        val died: String,
        val titles: List<String>,
        val aliases: List<String>,
        val father: String,
        val mother: String
)

data class CharacterWithHouse(
        val id: String,
        val name: String,
        val words: String,
        val born: String,
        val died: String,
        val titles: List<String>,
        val aliases: List<String>,
        val father: String,
        val mother: String,
        val house: String
)