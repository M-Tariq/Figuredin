package com.figuredin.controllers.Controller

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.figuredin.controllers.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    val mAuth=FirebaseAuth.getInstance()
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
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "User created successfully. Verify email First", Toast.LENGTH_LONG).show()

                    val user=mAuth.currentUser
                    if (user != null) {
                        user.sendEmailVerification()
                        Toast.makeText(this,"Verify email", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, SigninActivity::class.java))
                        mAuth.signOut()
                    }
                }else{

                    Toast.makeText(this, "Failed:"+task.exception, Toast.LENGTH_LONG).show()
                }
            }
            //Toast.makeText(this, ""+email+"\n"+password, Toast.LENGTH_LONG).show()
        }
    }
}
