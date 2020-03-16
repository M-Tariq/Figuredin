package com.figuredin.controllers.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.figuredin.controllers.R
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        if (supportActionBar != null)
            supportActionBar?.hide()

        btn_sigin.setOnClickListener {
            val email:String=email_sigin.text.toString().trim();
            val password:String=password_sigin.text.toString();
            Toast.makeText(this, ""+email+"\n"+password, Toast.LENGTH_LONG).show()
        }
        btn_signup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        tv_forgotpass.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
       if(remember_me.isChecked){
           //shared pref code
       }
    }
}
