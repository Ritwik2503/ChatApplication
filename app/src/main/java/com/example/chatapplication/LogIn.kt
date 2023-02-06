package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    private lateinit var edtEmail:EditText
    private lateinit var edtPassword:EditText
    private lateinit var btnLogIn: Button
    private lateinit var btnSignUp:Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        mAuth= FirebaseAuth.getInstance()

        edtEmail= findViewById(R.id.edt_email)
        edtPassword= findViewById(R.id.edt_password)
        btnLogIn= findViewById(R.id.btnLogIn)
        btnSignUp= findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        btnLogIn.setOnClickListener {
            val email= edtEmail.text.toString()
            val password= edtPassword.text.toString()

            logIn(email,password)
        }

    }

    private fun logIn(email: String,password:String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent= Intent(this@LogIn,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LogIn,"User Does not exist",Toast.LENGTH_SHORT).show()
                }
            }
    }
}

