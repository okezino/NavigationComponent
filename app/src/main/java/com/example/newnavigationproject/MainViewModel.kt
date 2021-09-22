package com.example.newnavigationproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(val repository: Repository) : ViewModel() {

    var _count : MutableLiveData<Int> = MutableLiveData(0)
    val count : LiveData<Int> = _count

    fun updateCount(){
        _count.value = _count.value?.plus(1)
    }
}