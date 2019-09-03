package com.kdias.thebestmovies.features.cadastroFilmes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType.TYPE_NULL
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.kdias.thebestmovies.R
import kotlinx.android.synthetic.main.activity_cadastro_filmes.*
import java.text.SimpleDateFormat
import java.util.*

class CadastroFilmesActivity : AppCompatActivity() {
    var fbAuth = FirebaseAuth.getInstance()
    var cal = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_filmes)

        edtReleased.isFocusable = true
        edtReleased.isFocusableInTouchMode = true
        edtReleased.inputType = TYPE_NULL



        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, mothOfYear: Int, dayOfMoth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, mothOfYear)
                cal.set(Calendar.DAY_OF_YEAR, dayOfMoth)
                updateDateInView()

            }

        }

        btnInputDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                DatePickerDialog(
                    this@CadastroFilmesActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_YEAR)
                ).show()
            }

        })

        fbAuth.addAuthStateListener {
            if (fbAuth.currentUser == null) {
                this.finish()
            }
        }
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        edtReleased.setText(sdf.format(cal.getTime()))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_logout) {
            showMessage(item.actionView, "Logging Out...")
            signOut()
        }

        return super.onOptionsItemSelected(item)
    }

    fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    fun signOut() {
        fbAuth.signOut()

    }
}
