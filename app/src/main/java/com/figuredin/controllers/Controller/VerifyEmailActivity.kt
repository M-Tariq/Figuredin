package com.figuredin.controllers.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.figuredin.controllers.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_verify_email.*

class VerifyEmailActivity : AppCompatActivity() {
    val mAuth= FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_email)
        val user=mAuth.currentUser
        if (user != null) {
        btn_resend_verify.setOnClickListener {

            if (user!=null){
                user.sendEmailVerification()
            }
        }
            if(user.isEmailVerified){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}
