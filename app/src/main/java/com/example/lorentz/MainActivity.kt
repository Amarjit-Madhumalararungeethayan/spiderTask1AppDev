package com.example.lorentz

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.Animation
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.lorentz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    //disables back button
    override fun onBackPressed() {
        System.exit(0)
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.thanks.text = ""

        binding.imageView.setOnClickListener(){
            binding.imageView.animate().apply {
                duration = 500
                rotationXBy(360f)
            }.start()
            binding.imageView.setImageResource(R.drawable.screenshot_2021_06_14_at_3_51_47_pm)
        }

        binding.textView11.setOnClickListener(){
            binding.textView11.animate().apply {
                duration = 500
                rotationYBy(360f)
            }.start()
            binding.thanks.text = "Thanks for mentoring me Gokul Anna ❤️"
        }

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.hide()
        }

        //directs user to Lorentz Factor page
        binding.Lorentz.setOnClickListener(){
            val intent = Intent(this, Lorentz::class.java)
            startActivity(intent)
        }

        //directs user to SPI Factor page
        binding.spi.setOnClickListener(){
            val intent = Intent(this, SPI::class.java)
            startActivity(intent)
        }
        binding.button4.setOnClickListener(){
            val intent = Intent(this, Checker::class.java)
            startActivity(intent)
        }

    }
}