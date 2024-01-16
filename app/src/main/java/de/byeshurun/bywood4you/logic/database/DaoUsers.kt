package de.byeshurun.bywood4you.logic.database

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import de.byeshurun.bywood4you.model.User

class DaoUsers {
    companion object {
        private const val TABLE_NAME = "users"

        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_DEPARTMENT = "department"
        private const val COLUMN_PASSWORD = "password"

        private const val WHERE_CLAUSE = "$COLUMN_ID = ?";
    }
    fun createTable(): String {
        return "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_DEPARTMENT TEXT, " +
                "$COLUMN_PASSWORD TEXT)"
    }

    fun insertUser(db: SQLiteDatabase, userToInsert: User): Long {
        val contentValues = getContentValuesFromUser(userToInsert)

        val rowId = db.insert(TABLE_NAME, null, contentValues);
        db.close()

        return rowId
    }

    fun insertUsers(db: SQLiteDatabase, usersToInsert: List<User>): Long {

        var lastRowId = -1L

        for (user in usersToInsert) {
            val userContentValues = getContentValuesFromUser(user)

            lastRowId = db.insert(TABLE_NAME, null, userContentValues);
        }

        db.close()

        return lastRowId
    }

    fun getAllUsers(db: SQLiteDatabase): List<User> {
        val usersFromDbManager = mutableListOf<User>()

        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val userFromDb = getUserFromCursor(cursor)
                usersFromDbManager.add(userFromDb)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return usersFromDbManager
    }

    fun getUserById(db: SQLiteDatabase, userId: Int): User? {
        val query = "SELECT * FROM $TABLE_NAME WHERE $WHERE_CLAUSE"
        val selectionArgs = arrayOf(userId.toString())

        val cursor = db.rawQuery(query, selectionArgs)
        var userFromDb: User? = null

        if (cursor.moveToFirst()) {
            userFromDb = getUserFromCursor(cursor)
        }

        cursor.close()
        db.close()

        return userFromDb


    }

    fun checkUser(db: SQLiteDatabase?, userName: String, userPw: String): Boolean {

        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(userName, userPw)

        val cursor = db?.rawQuery(query, selectionArgs)
        var userFromDb: User? = null

        if (cursor?.moveToFirst() == true) {
            userFromDb = getUserFromCursor(cursor)
        }

        cursor?.close()
        db?.close()

        return (userFromDb != null)
    }

    fun updateUser(db: SQLiteDatabase, userToUpdate: User): Int {

        val contentValuesFromUser = getContentValuesFromUser(userToUpdate)
        val whereArgs = arrayOf(userToUpdate.id.toString())

        val affectedRows = db.update(
            TABLE_NAME,
            contentValuesFromUser,
            WHERE_CLAUSE,
            whereArgs
        )

        return affectedRows
    }

    fun deleteUser(db: SQLiteDatabase, id: Int): Int {

        val whereArgs = arrayOf(id.toString())
        val affectedRows = db.delete(
            TABLE_NAME,
            WHERE_CLAUSE,
            whereArgs,
        )
        return affectedRows;
    }

    private fun getContentValuesFromUser(user: User): ContentValues {
        val contentValues = ContentValues()

        contentValues.put(COLUMN_NAME, user.name)
        contentValues.put(COLUMN_DEPARTMENT, user.department)
        contentValues.put(COLUMN_PASSWORD, user.password)

        return contentValues
    }

    private fun getUserFromCursor(cursor: Cursor): User {

        val columnIndexId = cursor.getColumnIndex(COLUMN_ID)
        val columnIndexName = cursor.getColumnIndex(COLUMN_NAME)
        val columnIndexDepartment = cursor.getColumnIndex(COLUMN_DEPARTMENT)
        val columnIndexPassword = cursor.getColumnIndex(COLUMN_PASSWORD)

        val id = cursor.getInt(columnIndexId)
        val name = cursor.getString(columnIndexName)
        val department = cursor.getString(columnIndexDepartment)
        val password = cursor.getString(columnIndexPassword)

        val userFromDb = User(id, name, department, password);

        return userFromDb
    }
}