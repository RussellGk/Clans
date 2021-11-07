package ru.skillbranch.gameofthrones.data.local.db

import androidx.room.*
import ru.skillbranch.gameofthrones.data.local.entities.House
import ru.skillbranch.gameofthrones.data.local.entities.Relative

@Dao
abstract class HouseDao :
    BaseDao<House> {

    @Query("SELECT * FROM House")
    abstract fun getAll(): List<House>

    fun removeAll() {
        removeAllHouses()
        removeAllRelatives()
    }

    @Query("DELETE FROM House")
    abstract fun removeAllHouses()

    @Query("DELETE FROM Relative")
    abstract fun removeAllRelatives()

    @Query("SELECT count(*) FROM House")
    abstract fun getCount(): Int

    @Insert
    abstract fun insertRelative(vararg relative: Relative)
}