package com.figuredin.controllers.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.figuredin.controllers.R
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btn_sigin_forgotpass.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
            finish()
        }
        btn_sendlink.setOnClickListener {
            val email:String=edt_email_forgot_id.text.toString().trim();

        }
    }
}
