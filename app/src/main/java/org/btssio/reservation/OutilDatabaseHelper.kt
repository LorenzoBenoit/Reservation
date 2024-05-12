package org.btssio.reservation

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class OutilDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "outils.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "outil"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOM = "nom"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_RESERVE = "reserve"

        private const val SQL_CREATE_TABLE_OUTIL = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY,
                $COLUMN_NOM TEXT,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_RESERVE INTEGER
            )
        """
        private const val SQL_DELETE_TABLE_OUTIL = "DROP TABLE IF EXISTS $TABLE_NAME"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_OUTIL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_TABLE_OUTIL)
        onCreate(db)
    }

    fun getAllOutils(): List<Outil> {
        val outils = mutableListOf<Outil>()
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                val id = it.getLong(it.getColumnIndexOrThrow(COLUMN_ID))
                val nom = it.getString(it.getColumnIndexOrThrow(COLUMN_NOM))
                val description = it.getString(it.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val reserve = it.getInt(it.getColumnIndexOrThrow(COLUMN_RESERVE)) == 1
                outils.add(Outil(id, nom, description, reserve))
            }
        }
        return outils
    }

    fun insertOutil(nom: String, description: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOM, nom)
            put(COLUMN_DESCRIPTION, description)
            put(COLUMN_RESERVE, 0) // Initialiser la réservation à 0
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun updateReservationOutil(outilId: Long, reserve: Boolean): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_RESERVE, if (reserve) 1 else 0)
        }
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(outilId.toString())
        return db.update(TABLE_NAME, values, selection, selectionArgs)
    }

    fun checkUserCredentials(email: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "${UtilisateurContract.UtilisateurEntry.COLUMN_EMAIL} = ? AND ${UtilisateurContract.UtilisateurEntry.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(
            UtilisateurContract.UtilisateurEntry.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val count = cursor.count
        cursor.close()
        return count > 0
    }
}
