package com.figuredin.controllers.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.figuredin.controllers.R
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread{
            Thread.sleep(3000)
        }.start()
        val mAuth=FirebaseAuth.getInstance()
        val user=mAuth.currentUser

        if (user != null) {
            if (user.isEmailVerified){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this, SigninActivity::class.java))
            }

        }
        startActivity(Intent(this, SigninActivity::class.java))
    }

}
