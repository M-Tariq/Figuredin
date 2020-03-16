package com.figuredin.controllers.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.figuredin.controllers.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btn_signin_signup.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
            finish()
        }

        btn_signup_signup.setOnClickListener(){
            val email:String=email_signup.text.toString().trim();
            val password:String=password_signup.text.toString();
            Toast.makeText(this, ""+email+"\n"+password, Toast.LENGTH_LONG).show()
        }
    }
}
