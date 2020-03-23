package com.figuredin.controllers.Controller

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.figuredin.controllers.R
import com.figuredin.controllers.ui.auth.SigninActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        actionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        actionBar!!.title = "Figuredin"
        actionBar.setCustomView(R.layout.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.logout){
           val mAuth=FirebaseAuth.getInstance()
            mAuth.signOut()
            startActivity(Intent(this, SigninActivity::class.java))
            finish()
        }
        return super.onOptionsItemSelected(item)

    }
}
