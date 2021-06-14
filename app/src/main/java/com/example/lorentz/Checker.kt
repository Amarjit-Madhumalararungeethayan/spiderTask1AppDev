package com.example.lorentz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.lorentz.databinding.ActivityCheckerBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.sqrt

class Checker : AppCompatActivity() {
    lateinit var binding: ActivityCheckerBinding

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.hide()
        }

        binding.textView6.text = "                      "

        binding.button.setOnClickListener() {
            checkFun()
        }
        binding.button2.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

}

    private fun checkFun() {
        val velo =
            try {                                                //takes in velocity input
                binding.textView7.text.toString().toDouble()
            } catch (ex: NumberFormatException) {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_LONG)
                    .show()         //if invalid gives a toast
                return
            }

        if (velo > 300000000) {
            Toast.makeText(this, "Speed cannot be greater than light", Toast.LENGTH_LONG).show()
            val intent = Intent(
                this,
                Checker::class.java
            )                          // Toast and restarts activity if the velocity is greater than that of light
            startActivity(intent)
        } else if (velo == 300000000.toDouble()) {
            Toast.makeText(this, "Lorentz Factor Tends to Infinity ♾", Toast.LENGTH_LONG).show()
            val intent = Intent(
                this,
                Checker::class.java
            )                          // Toast and restarts activity if the velocity is equal to that of light
            startActivity(intent)
        } else {
            var temp = velo.div(300000000)
            var res = 1 / sqrt(1 - (temp.pow(2)))           //Lorentz Formula

            val df = DecimalFormat("#.######")     //rounds it to 5 decimal places
            df.roundingMode = RoundingMode.CEILING

            binding.textView6.text = "Calculated Lorentz factor "
            binding.textView8.text = df.format(res).toDouble().toString() //💪🏼
        }


            }
        }


