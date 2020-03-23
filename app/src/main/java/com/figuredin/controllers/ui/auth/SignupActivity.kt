package com.figuredin.controllers.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.figuredin.controllers.Controller.other.Constant
import com.figuredin.controllers.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
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
            if (!email.isEmpty() && !password.isEmpty() && isEmailValid(email)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(
                                this,
                                "User created successfully. Verify email First",
                                Toast.LENGTH_LONG
                            ).show()

                            val user = mAuth.currentUser
                            if (user != null) {
                                storeInDb(user.uid, email)
                                user.sendEmailVerification()
                                Toast.makeText(this, "Verify email", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this, SigninActivity::class.java))
                                mAuth.signOut()
                            }
                        } else {
                            Toast.makeText(this, "Failed:" + task.exception, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            }else{
                Toast.makeText(this, "Email or password is empty or badly formatted", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun storeInDb(uid: String, email: String) {
        val firestoreDb = FirebaseFirestore.getInstance()
        val userRef = firestoreDb.collection(Constant.USER_COLLECTION).document(uid)
        val map = HashMap<String, Any>()
        map.put("email", email)
        map.put("createdAt", FieldValue.serverTimestamp())
        userRef.set(map)

    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
