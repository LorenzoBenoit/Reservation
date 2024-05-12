package org.btssio.reservation

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Vérifie que l'importation de Button est correcte
        val buttonClickMe = findViewById<Button>(R.id.buttonClickMe)
        buttonClickMe.setOnClickListener {
            // Vérifie que l'importation de Toast est correcte
            Toast.makeText(this, "Bouton cliqué !", Toast.LENGTH_SHORT).show()

            val outilDatabaseHelper = OutilDatabaseHelper(this)

            // Utilisation de OutilDatabaseHelper pour insérer un outil dans la base de données
            val outilId = outilDatabaseHelper.insertOutil("Marteau", "Un marteau robuste pour toutes vos tâches de construction.")
            Log.d("OutilDatabase", "Nouvel outil inséré avec l'id: $outilId")
        }
    }
}
