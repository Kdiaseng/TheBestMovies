package com.kdias.thebestmovies.features.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

import com.kdias.thebestmovies.R
import com.kdias.thebestmovies.features.cadastroFilmes.CadastroFilmesActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener{view ->

            signIn(view,edtEmail.text.toString(), edtPassword.text.toString())
        }
    }

    private fun signIn(view: View?, email: String, password: String) {
        showMessage(view,"Authenticating...")
        fbAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,
            OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful){
                    var intent = Intent(this@LoginActivity, CadastroFilmesActivity:: class.java)
                    intent.putExtra("id", fbAuth.currentUser?.email)
                    startActivity(intent)
                }else{
                    showMessage(view,"Error: ${task.exception?.message}")
                }
            })
    }

    private fun showMessage(view: View?, message: String) {
        Snackbar.make(view!!, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }
}
