package com.llama.compose_do.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel : ViewModel() {
    val titleText = mutableStateOf("")

    fun setTitleText(title: String) {
        titleText.value = title
    }
}