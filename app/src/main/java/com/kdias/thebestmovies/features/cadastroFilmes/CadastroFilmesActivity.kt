package com.kdias.thebestmovies.features.cadastroFilmes

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType.TYPE_NULL
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kdias.thebestmovies.R
import com.kdias.thebestmovies.features.listaFilmes.fragment.model.Movie
import kotlinx.android.synthetic.main.activity_cadastro_filmes.*
import kotlinx.android.synthetic.main.activity_login.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CadastroFilmesActivity : AppCompatActivity() {
    var fbAuth = FirebaseAuth.getInstance()
    var cal = Calendar.getInstance()
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebase: FirebaseStorage? = null
    private var storageReference: StorageReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_filmes)

        firebase = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        edtReleased.isFocusable = true
        edtReleased.isFocusableInTouchMode = true
        edtReleased.inputType = TYPE_NULL

        btnUploadPoster.setOnClickListener {
            launchGallery()
        }

        cadastrar.setOnClickListener{view ->
            saveMovie(view)
        }


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


    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }


    private fun saveMovie(view: View?) {

        val selectId = rdgroup.checkedRadioButtonId
        val typeMovie: String

        if (selectId != -1) {
            val radio: RadioButton = findViewById(selectId)
            typeMovie = radio.text.toString()
        } else {
            typeMovie = ""
        }


        val ref = FirebaseDatabase.getInstance().getReference("Movies")
        val idMovie = ref.push().key

        val movie = Movie(
            idMovie!!,
            typeMovie,
            edtDescricao.text.toString().trim(),
            edtReleased.text.toString(),
            edtTitle.text.toString().trim(),
            edtPoster.text.toString(),
            edtRutime.text.toString().trim(),
            edtUrlTrailer.text.toString().trim()

        )

        ref.child(idMovie).setValue(movie).addOnCompleteListener {
            Snackbar.make(view!!, "Cadastro feito com sucesso", Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data

            Log.e("FILEPAH",filePath.toString())

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
