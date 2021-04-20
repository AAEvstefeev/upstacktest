package com.example.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception




class MainViewModel(private val networkClient: NetworkClient) : ViewModel() {


    val liveData: LiveData<List<Repo>> get() = _liveData
    private val _liveData = MutableLiveData<List<Repo>>()

    fun getList(name : String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    networkClient.getRepo(name).collect{
                        withContext(Dispatchers.Main) {
                           _liveData.postValue(it)
                        }
                    }
                }


            } catch (e: Exception) {
                Log.e("err","errro ");
                e.printStackTrace()
            }
        }
    }

}