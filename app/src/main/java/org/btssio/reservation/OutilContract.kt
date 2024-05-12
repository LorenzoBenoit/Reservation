package org.btssio.reservation

// OutilContract.kt
object OutilContract {
    // Définition des noms des colonnes pour la table des outils
    object OutilEntry {
        const val TABLE_NAME = "outil"
        const val COLUMN_ID = "id"
        const val COLUMN_NOM = "nom"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_RESERVE = "reserve"
    }

    // Requête de création de la table des outils
    const val SQL_CREATE_TABLE_OUTIL = """
        CREATE TABLE ${OutilEntry.TABLE_NAME} (
            ${OutilEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${OutilEntry.COLUMN_NOM} TEXT,
            ${OutilEntry.COLUMN_DESCRIPTION} TEXT
        )
    """

    // Requête de suppression de la table des outils
    const val SQL_DROP_TABLE_OUTIL = "DROP TABLE IF EXISTS ${OutilEntry.TABLE_NAME}"
}
