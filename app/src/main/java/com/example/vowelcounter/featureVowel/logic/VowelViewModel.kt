package com.example.vowelcounter.featureVowel.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vowelcounter.common.MutableLiveEvent

class VowelViewModel : ViewModel() {

    private val searchEnteredTextLiveData = MutableLiveData<String>()

    private val searchEnteredTexForVowelLiveData = MutableLiveData<String>()

    private val validationEmptySearchLiveEvent = MutableLiveEvent<Unit>()

    private val clearTextLiveDataEvent = MutableLiveEvent<String>()

    private val clearContentTextLiveData = MutableLiveData<String>()

    fun getClearContentText(): LiveData<String> = clearContentTextLiveData

    fun getClearSearchText(): LiveData<String> = clearTextLiveDataEvent

    fun getValidationEmptySearch(): LiveData<Unit> = validationEmptySearchLiveEvent

    fun getSearchEnteredText(): LiveData<String> = searchEnteredTextLiveData

    fun getSearchEnteredTextForVowel(): LiveData<String> = searchEnteredTexForVowelLiveData

    fun onSearchButton(text: CharSequence) {
        if (text.isEmpty()) {
            showValidationEmptySearch()
        }
        showTextFromSearch(text)
    }

    fun onClearButton() {
        clearSearchText()
        clearContentText()
    }

    fun onVowelCounterButton(text: CharSequence) {
        showTextFromSearchToVowel(text)
    }

    private fun clearContentText() {
        clearContentTextLiveData.value = ""
    }

    private fun clearSearchText() {
        clearTextLiveDataEvent.value = ""
    }

    private fun showValidationEmptySearch() {
        validationEmptySearchLiveEvent.value = Unit
    }

    private fun showTextFromSearch(text: CharSequence) {
        searchEnteredTextLiveData.value = text.toString()
    }

    private fun showTextFromSearchToVowel(text: CharSequence) {
        searchEnteredTexForVowelLiveData.value = text.toString()
    }
}
