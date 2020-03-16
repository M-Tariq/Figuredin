package com.figuredin.controllers.Controller

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.figuredin.controllers.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*


class SigninActivity : AppCompatActivity() {

    val mAuth=FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val mAuth=FirebaseAuth.getInstance();

        if (supportActionBar != null)
            supportActionBar?.hide()

        btn_sigin.setOnClickListener {
            val email:String=email_sigin.text.toString().trim();
            val password:String=password_sigin.text.toString();
            if (!email.isEmpty() && !password.isEmpty())
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    val user=mAuth.currentUser
                        if(user!=null){
                            val user1= mAuth.currentUser?.reload()
                            if(user.isEmailVerified){
                                Toast.makeText(this, "Sign in successfull", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this, MainActivity::class.java))
                            }
                            else{
                                Toast.makeText(this, "Email not verified", Toast.LENGTH_LONG).show()
                            }
                        }

                }else{
                Toast.makeText(this, "Authentication failed: \n"+task.exception, Toast.LENGTH_LONG).show()
            }
        }
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

    override fun onStart() {
        super.onStart()
        val user=mAuth.currentUser
        if(user!==null){
            Toast.makeText(this, "Already sign in", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
        } else{
            Toast.makeText(this, "Sigin first", Toast.LENGTH_LONG).show();
        }
    }
}
