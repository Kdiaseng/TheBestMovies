package com.kdias.thebestmovies.features.cadastroFilmes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.kdias.thebestmovies.R
import kotlinx.android.synthetic.main.activity_cadastro_filmes.*

class CadastroFilmesActivity : AppCompatActivity() {
    var fbAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_filmes)

        btnLogout.setOnClickListener { view ->
            showMessage(view, "Logging Out...")
            signOut()
        }

        fbAuth.addAuthStateListener {
            if(fbAuth.currentUser == null){
                this.finish()
            }
        }
    }

    fun showMessage(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    fun signOut(){
        fbAuth.signOut()

    }
}
