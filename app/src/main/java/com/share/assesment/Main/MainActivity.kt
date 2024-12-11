package com.share.assesment.Main

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.trimmedLength
import com.share.assesment.R
import com.share.assesment.ui.theme.AssesmentTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var amountText: TextView
    private lateinit var confirmButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountText = findViewById(R.id.amount_display_main)
        confirmButton = findViewById(R.id.confirm_btn_main)

        viewModel.amount.observe(this) { amount ->
            amountText.setText(amount.toString())
            if (amount != "") {
                if(amount.toDouble() != 0.0){
                    confirmButton.visibility = View.VISIBLE
                }else{
                    confirmButton.visibility = View.GONE
                }
            } else {
                confirmButton.visibility = View.GONE
            }
        }

        findViewById<Button>(R.id.one).setOnClickListener { fillAmount("1") }
        findViewById<Button>(R.id.two).setOnClickListener { fillAmount("2") }
        findViewById<Button>(R.id.three).setOnClickListener { fillAmount("3") }
        findViewById<Button>(R.id.four).setOnClickListener { fillAmount("4") }
        findViewById<Button>(R.id.five).setOnClickListener { fillAmount("5") }
        findViewById<Button>(R.id.six).setOnClickListener { fillAmount("6") }
        findViewById<Button>(R.id.seven).setOnClickListener { fillAmount("7") }
        findViewById<Button>(R.id.eight).setOnClickListener { fillAmount("8") }
        findViewById<Button>(R.id.nine).setOnClickListener { fillAmount("9") }
        findViewById<Button>(R.id.zero).setOnClickListener { fillAmount("0") }
        findViewById<Button>(R.id.dot).setOnClickListener { fillAmount(".") }
        findViewById<Button>(R.id.back).setOnClickListener { fillAmount("Remove") }
    }

    private fun fillAmount(value:String){
        when(value){
            "." -> {
                if(amountText.text.toString().contains(".") && amountText.text.toString().trim() != ""){
                    Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.setAmount(amountText.text.toString(),".")
                }
            }
            "Remove" -> {
                if(amountText.text.isEmpty()){
                    viewModel.setAmount("0",value);
                }else if(amountText.text.trim() != ""){
                    viewModel.setAmount(amountText.text.toString(),value)
                }
            }
            else ->{
                viewModel.setAmount(amountText.text.toString(),value)
            }
        }
    }
}