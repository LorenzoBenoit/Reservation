package org.btssio.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var outilDatabaseHelper: OutilDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        outilDatabaseHelper = OutilDatabaseHelper(this)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (checkCredentials(email, password)) {
                // Connexion réussie
                Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show()
                // Redirection vers l'activité principale
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Informations de connexion incorrectes
                Toast.makeText(this, "Adresse e-mail ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkCredentials(email: String, password: String): Boolean {
        // Vérifie les informations de connexion dans la base de données
        return outilDatabaseHelper.checkUserCredentials(email, password)
    }
}
