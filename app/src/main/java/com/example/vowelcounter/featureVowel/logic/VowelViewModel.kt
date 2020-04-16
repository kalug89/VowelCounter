package com.example.vowelcounter.featureVowel.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vowelcounter.common.MutableLiveEvent

class VowelViewModel : ViewModel() {

    private val searchEnteredTextLiveData = MutableLiveData<String>()

    private val searchEnteredTexForVowelLiveData = MutableLiveData<String>()

    private val validateEmptySearchLiveEvent = MutableLiveEvent<Unit>()

    private val clearTextLiveDataEvent = MutableLiveEvent<String>()

    private val clearContentTextLiveEvent = MutableLiveEvent<String>()

    fun getClearContentText(): LiveData<String> = clearContentTextLiveEvent

    fun getClearSearchText(): LiveData<String> = clearTextLiveDataEvent

    fun getValidationEmptySearch(): LiveData<Unit> = validateEmptySearchLiveEvent

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
        clearContentTextLiveEvent.value = ""
    }

    private fun clearSearchText() {
        clearTextLiveDataEvent.value = ""
    }

    private fun showValidationEmptySearch() {
        validateEmptySearchLiveEvent.value = Unit
    }

    private fun showTextFromSearch(text: CharSequence) {
        searchEnteredTextLiveData.value = text.toString() + text.toString()
    }

    private fun showTextFromSearchToVowel(text: CharSequence) {
        searchEnteredTexForVowelLiveData.value = text.toString()
    }

    fun calculateVowelInText(searchText: String): Int {
        val numberOfVowel: Int
        fun Char.isVowel(): Boolean =  this.toLowerCase() == 'a'
                || this.toLowerCase() == 'e'
                || this.toLowerCase() == 'i'
                || this.toLowerCase() == 'o'
                || this.toLowerCase() == 'u'
                || this.toLowerCase() == 'y'
                || this.toLowerCase() == 'ą'
                || this.toLowerCase() == 'ę'
                || this.toLowerCase() == 'ó'

        val vowel = searchText.filter { e -> e.isVowel()}
        numberOfVowel = vowel.count()
        return numberOfVowel
    }
}
