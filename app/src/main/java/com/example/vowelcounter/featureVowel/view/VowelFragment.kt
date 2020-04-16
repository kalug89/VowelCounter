package com.example.vowelcounter.featureVowel.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.vowelcounter.R
import com.example.vowelcounter.featureVowel.logic.VowelViewModel
import kotlinx.android.synthetic.main.vowel_fragment.*

class VowelFragment : Fragment(R.layout.vowel_fragment) {

    private val viewModel: VowelViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        setupSendButton()
        setupClearButton()
        setupVowelCounterButton()
        handleSearchText()
        handleEmptySearch()
        handleClearSearchText()
        handleClearContentText()
        handleVowelCounterText()
    }

    private fun setupClearButton() {
        view_clear_button.setOnClickListener() {
            viewModel.onClearButton()
        }
    }

    private fun setupSendButton() {
        view_send_button.setOnClickListener() {
            viewModel.onSearchButton(view_search.text)
        }
    }

    private fun setupVowelCounterButton() {
        view_vowel_counter_button.setOnClickListener() {
            viewModel.onVowelCounterButton(view_content_text.text)

        }
    }

    private fun handleSearchText() {
        viewModel.getSearchEnteredText().observe(viewLifecycleOwner) { searchText ->
            view_content_text.text = searchText
        }
    }

    private fun handleEmptySearch() {
        viewModel.getValidationEmptySearch().observe(viewLifecycleOwner) {
            showEmptySearchTextDialog()
            vibrate()
        }
    }

    private fun handleClearSearchText() {
        viewModel.getClearSearchText().observe(viewLifecycleOwner) { clearSearchText ->
            view_search.setText(clearSearchText)
        }
    }

    private fun handleClearContentText() {
        viewModel.getClearContentText().observe(viewLifecycleOwner) { clearContentText ->
            view_content_text.text = clearContentText

        }
    }

    private fun handleVowelCounterText() {
        viewModel.getSearchEnteredTextForVowel().observe(viewLifecycleOwner) { searchtext ->
            calculateVowelInText(searchtext)
        }
    }

    private fun showEmptySearchTextDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.dialog_text)
        builder.setPositiveButton(R.string.dialog_ok) { _, _ ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun vibrate() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(200)
    }

    private fun calculateVowelInText(searchText: String) {
        view_vowel_counter_text.text = viewModel.calculateVowelInText(searchText).toString()
    }
}
