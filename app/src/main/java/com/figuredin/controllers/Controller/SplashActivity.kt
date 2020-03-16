package com.figuredin.controllers.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.figuredin.controllers.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread{
            Thread.sleep(3000)
        }.start()

        intent = Intent(this, SigninActivity::class.java)
        startActivity(intent)
    }

}
