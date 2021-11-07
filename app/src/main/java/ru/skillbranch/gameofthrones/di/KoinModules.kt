package ru.skillbranch.gameofthrones.di


import androidx.room.Room
import org.koin.dsl.module
import ru.skillbranch.gameofthrones.data.local.db.Database
import ru.skillbranch.gameofthrones.data.local.db.RoomDb
import ru.skillbranch.gameofthrones.data.local.db.RootDatabase
import ru.skillbranch.gameofthrones.data.remote.network.NetworkService
import ru.skillbranch.gameofthrones.repositories.RootRepository

val dataModule = module {

    single { Room.databaseBuilder(get(), RootDatabase::class.java, "tree-db").build() }

    single {
        RoomDb(
            get<RootDatabase>(RootDatabase::class.java).houseDao(),
            get<RootDatabase>(RootDatabase::class.java).characterDao()
        ) as Database //TODO check
    }

    single {
        NetworkService.api
    }

    single { RootRepository }

}