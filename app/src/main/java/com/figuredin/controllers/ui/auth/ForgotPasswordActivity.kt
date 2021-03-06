package com.figuredin.controllers.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.figuredin.controllers.R
import com.google.firebase.auth.FirebaseAuth
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
            val email: String = edt_email_forgot_id.text.toString().trim();
            if (!email.isEmpty() && isEmailValid(email)) {
                val mAuth = FirebaseAuth.getInstance()
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Check Your email", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, SigninActivity::class.java))
                    } else {
                        Toast.makeText(this, "Error:" + task.exception, Toast.LENGTH_LONG).show()
                    }

                }

            } else {
                Toast.makeText(this, "Email is empty or badly formatted", Toast.LENGTH_LONG).show()
            }
        }

    }
    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}