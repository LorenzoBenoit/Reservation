package org.btssio.reservation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.btssio.reservation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var outilDatabaseHelper: OutilDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Utilisation du databinding pour lier le layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialisation de OutilDatabaseHelper
        outilDatabaseHelper = OutilDatabaseHelper(this)

        // Gestionnaire d'événements pour le bouton d'ajout d'outil
        binding.buttonAjouterOutil.setOnClickListener {
            val nomOutil = binding.editTextNom.text.toString()
            val descriptionOutil = binding.editTextDescription.text.toString()

            val nouvelOutilId = outilDatabaseHelper.insertOutil(nomOutil, descriptionOutil)
            if (nouvelOutilId != -1L) {
                Toast.makeText(this, "Nouvel outil ajouté avec l'ID : $nouvelOutilId", Toast.LENGTH_SHORT).show()
                // Mettre à jour la liste des outils après l'ajout
                val updatedOutils = outilDatabaseHelper.getAllOutils().sortedBy { it.nom }
                val adapter = OutilAdapter(this, android.R.layout.simple_spinner_item, updatedOutils)
                binding.spinnerOutils.adapter = adapter
            } else {
                Toast.makeText(this, "Échec de l'ajout de nouvel outil", Toast.LENGTH_SHORT).show()
            }
        }

        // Gestionnaire d'événements pour le bouton de réservation
        binding.buttonReserver.setOnClickListener {
            val selectedOutil = binding.spinnerOutils.selectedItem as Outil
            val outilId = selectedOutil.id
            val isReserved = selectedOutil.isReserved // Mettez ici votre méthode pour vérifier si l'outil est déjà réservé
            val newReservationStatus = !isReserved // Inverser l'état de la réservation
            val rowsUpdated = outilDatabaseHelper.updateReservationOutil(outilId, newReservationStatus)
            if (rowsUpdated > 0) {
                val message = if (newReservationStatus) "Outil réservé avec succès." else "Réservation annulée avec succès."
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Échec de la réservation de l'outil.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
