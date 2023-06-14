package com.example.kotlinbasics

import android.content.Context
import android.icu.number.NumberFormatter
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.Key
import com.example.kotlinbasics.databinding.CalculateActivityBinding
import java.text.NumberFormat
import kotlin.math.ceil

class CalculateActivity : AppCompatActivity(){

    lateinit var binding: CalculateActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculateActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{ calculateTip() }

        binding.costOfService.setOnKeyListener{ view, keyCode, _ -> handleKeyEvent(view, keyCode) }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    fun calculateTip() {
        val stringFromEditText = binding.costOfServiceEditText.text.toString()
        val cost = stringFromEditText.toDoubleOrNull()
        if (cost == null){
            binding.tipResult.text = ""
            return
        }

        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when(selectedId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tipamount = tipPercentage * cost

        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp){
            tipamount = ceil(tipamount)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tipamount)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}