package ru.skillbranch.gameofthrones.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.skillbranch.gameofthrones.NetworkState
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.repositories.RootRepository

class RootViewModel(val app: Application) : AndroidViewModel(app), KoinComponent {

    private val repository: RootRepository by inject()

    fun syncDataIfNeed(): LiveData<LoadResult> {
        val result: MutableLiveData<LoadResult> = MutableLiveData(LoadResult.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.isNeedUpdate()) {
                if (!NetworkState.isNetworkConnected) {
                    result.postValue(LoadResult.Error(app.applicationContext.getString(R.string.network_error)))
                    return@launch
                }
                repository.sync()
                result.postValue(LoadResult.Success)
            } else {
                delay(5000)
                result.postValue(LoadResult.Success)
            }
        }
        return result
    }
}

sealed class LoadResult {
    object Loading : LoadResult()
    object Success : LoadResult()
    class Error(val message: String) : LoadResult()
}