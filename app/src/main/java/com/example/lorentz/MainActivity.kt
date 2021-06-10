package com.example.lorentz

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lorentz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    //disables back button
    override fun onBackPressed() {
        // super.onBackPressed();
        // Not calling **super**, disables back button in current screen.
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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