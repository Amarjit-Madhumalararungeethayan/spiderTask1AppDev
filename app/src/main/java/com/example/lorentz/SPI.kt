package com.example.lorentz

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.lorentz.databinding.ActivitySpiBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.math.pow

val temp = 1

class SPI : AppCompatActivity() {


    //takes user back to home page
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    lateinit var binding: ActivitySpiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpiBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //hides action bar
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.hide()
        }

        //starts countdown
        countDown(100000000,1000)

        //back to home screen
        binding.button3.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun countDown(x: Int, y: Int) {

        //countdown is an object of the CountDownTimer class
        val countDown: CountDownTimer
        val X = x.toLong()
        val Y = y.toLong()
        countDown = object : CountDownTimer(X, Y) {
            override fun onTick(millisUntil: Long) {
                val calendar = Calendar.getInstance()
                val hour12hrs = calendar.get(Calendar.HOUR)
                val minutes = calendar.get(Calendar.MINUTE)
                val seconds = calendar.get(Calendar.SECOND)

                //Displays the time
                binding.hr.text = hour12hrs.toString()
                binding.min.text = minutes.toString()
                binding.sec.text = seconds.toString()

                //Calc part
                val t1 = fact(hour12hrs).toDouble()
                val t2 = (seconds + (minutes*minutes*minutes)).toDouble()

                val t3 = t1/t2

                //Displaying the result
                val df = DecimalFormat("#.#########")
                df.roundingMode = RoundingMode.CEILING
                binding.textView19.text = df.format(t3).toString()

            }

            //Function that calculates the factorial
            fun fact(n: Int): Long
                {
                    if (n >= 1)
                        return n * fact(n - 1)
                    else
                        return 1
                }

            override fun onFinish() {
                countDown(100000000,1000)
            }
        }
        countDown.start()
    }
}

