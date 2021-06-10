package com.example.lorentz

import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lorentz.databinding.ActivityLorentzBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.sqrt

class Lorentz : AppCompatActivity() {

    lateinit var binding: ActivityLorentzBinding

    override fun onBackPressed() {
        // super.onBackPressed();
        // Not calling **super**, disables back button in current screen.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLorentzBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener() {
            checkFun()
        }
        binding.button2.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun checkFun() {
        val velo = try {
            binding.textView7.text.toString().toDouble()
        } catch (ex: NumberFormatException) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_LONG).show()
            return
        }
        val input1 = try {
            binding.textView8.text.toString().toDouble()
        } catch (ex: NumberFormatException) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_LONG).show()
            return
        }
        if(velo > 300000000){
            Toast.makeText(this, "Speed cannot be greater than light", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Lorentz::class.java)
            startActivity(intent)
        }
        else if(velo == 300000000.toDouble()){
            Toast.makeText(this, "Lorentz Factor Tends to Infinity ♾", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Lorentz::class.java)
            startActivity(intent)
        }
        else{
            var temp = velo.div(300000000)
            var res = 1 / sqrt(1 - (temp.pow(2)))

            val df = DecimalFormat("#.###")
            df.roundingMode = RoundingMode.CEILING

            if (df.format(res).toDouble() == input1) {
                binding.crt.text = "Well Done 😎"
                binding.textView10.text = " Correct Answer ->"
                binding.textView.text = df.format(res).toString()
                binding.abc.setBackgroundResource(R.color.green)
                countDown1(200,10)
                successSound()

            } else if(df.format(res).toDouble() != input1){
                binding.crt.text = "Wrong answer 😞"
                binding.textView10.text = " Correct Answer ->"
                binding.textView.text = df.format(res).toString()
                binding.abc.setBackgroundResource(R.color.red)
                countDown1(200,10)
                failSound()
                vibrateNow()
            }
        }








    }

    private fun vibrateNow() {
        val v = getSystemService(VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            v.vibrate(500)
        }

    }
    private fun countDown1(x: Int, y: Int) {
        val countDown: CountDownTimer
        val X = x.toLong()
        val Y = y.toLong()
        countDown = object : CountDownTimer(X, Y) {
            override fun onTick(millisUntil: Long) {
            }

            override fun onFinish() {
                binding.abc.setBackgroundResource(R.color.black)
            }
        }
        countDown.start()
    }
    private fun successSound() {
        val ping = MediaPlayer.create(this, R.raw.success_ping)
        ping.start()
    }

    private fun failSound() {
        val ping = MediaPlayer.create(this, R.raw.wrong_ping)
        ping.start()
    }
}