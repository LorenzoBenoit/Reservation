package org.btssio.reservation

// UtilisateurContract.kt
object UtilisateurContract {
    // Définition des noms des colonnes pour la table des utilisateurs
    object UtilisateurEntry {
        const val TABLE_NAME = "utilisateur"
        const val COLUMN_ID = "id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "mot_de_passe"
    }

    // Requête de création de la table des utilisateurs
    const val SQL_CREATE_TABLE_UTILISATEUR = """
        CREATE TABLE ${UtilisateurEntry.TABLE_NAME} (
            ${UtilisateurEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${UtilisateurEntry.COLUMN_EMAIL} TEXT,
            ${UtilisateurEntry.COLUMN_PASSWORD} TEXT
        )
    """

    // Requête de suppression de la table des utilisateurs
    const val SQL_DROP_TABLE_UTILISATEUR = "DROP TABLE IF EXISTS ${UtilisateurEntry.TABLE_NAME}"
}
