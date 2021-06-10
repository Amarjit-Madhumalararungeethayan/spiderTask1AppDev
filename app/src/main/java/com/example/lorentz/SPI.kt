package com.example.lorentz

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.lorentz.databinding.ActivitySpiBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.math.pow

val temp = 1

class SPI : AppCompatActivity() {

    
    lateinit var binding: ActivitySpiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countDown(100000,1000)

        binding.button3.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun countDown(x: Int, y: Int) {
        val countDown: CountDownTimer
        val X = x.toLong()
        val Y = y.toLong()
        countDown = object : CountDownTimer(X, Y) {
            override fun onTick(millisUntil: Long) {
                val calendar = Calendar.getInstance()
                val hour12hrs = calendar.get(Calendar.HOUR)
                val minutes = calendar.get(Calendar.MINUTE)
                val seconds = calendar.get(Calendar.SECOND)

                binding.hr.text = hour12hrs.toString()
                binding.min.text = minutes.toString()
                binding.sec.text = seconds.toString()


                val t1 = fact(hour12hrs).toDouble()
                val t2 = (seconds + (minutes*minutes*minutes)).toDouble()

                val t3 = t1/t2

                val df = DecimalFormat("#.#########")
                df.roundingMode = RoundingMode.CEILING
                binding.textView19.text = df.format(t3).toString()

            }

            fun fact(n: Int): Long
                {
                    if (n >= 1)
                        return n * fact(n - 1)
                    else
                        return 1
                }

            override fun onFinish() {
                countDown(1000000,1000)
            }
        }
        countDown.start()
    }
}

