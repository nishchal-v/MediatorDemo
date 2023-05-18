package com.example.mediatordemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val isOneOn = MutableLiveData(false)
    val isTwoOn = MutableLiveData(false)

    private val _isOn = MediatorLiveData<Boolean>()
    val isOn: LiveData<Boolean> = _isOn

    init {
        _isOn.apply {
            addSource(isOneOn) {
                value = it && isTwoOn.value ?: false
            }
            addSource(isTwoOn) {
                value = it && isOneOn.value ?: false
            }
        }
    }
}