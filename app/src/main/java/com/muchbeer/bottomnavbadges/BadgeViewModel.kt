package com.muchbeer.bottomnavbadges

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BadgeViewModel() : ViewModel() {

    private val _homeCount = MutableLiveData<Int>()
    val homeCount: LiveData<Int>
    get() = _homeCount

    private val _listCount = MutableLiveData<Int>()
    val listCount: LiveData<Int>
        get() = _listCount

    private val _profileCount = MutableLiveData<Int>()
    val profileCount: LiveData<Int>
        get() = _profileCount

    private val _spinnerSelected = MutableLiveData<String>()
    val spinnerSelected : LiveData<String>
        get() = _spinnerSelected

    private val _buttoClicked = MutableLiveData<String>()
    val buttonClicked : LiveData<String>
        get() = _buttoClicked
    init {
        _homeCount.value = 0
        _listCount.value = 0
        _profileCount.value = 0
    }

    fun countHome(count: Int) {
        _homeCount.value = count
    }

    fun countList(count: Int) {
        _listCount.value = count
    }

    fun countProfile(count: Int) {
        _profileCount.value = count
    }

    fun spinnerSelected(itemSelected : String) {
        _spinnerSelected.value = itemSelected
        Log.d("BadgeVM", "ViewModel selected : ${itemSelected}")
    }

    fun buttonSelected(itemSelected: String) {
        _buttoClicked.value = itemSelected
    }
}