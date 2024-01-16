package de.byeshurun.bywood4you.logic.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import de.byeshurun.bywood4you.model.User

class DbManager private constructor(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "bywood4you.db"
        private const val DB_VERSION = 1

        @Volatile
        private var instance: DbManager? = null

        fun getInstance(context: Context): DbManager {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = DbManager(context)
                    }
                }
            }
            return instance!!
        }
    }
    private val daoUsers by lazy { DaoUsers() }


    override fun onCreate(db: SQLiteDatabase?) {
    db?.execSQL(daoUsers.createTable())
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertUser(user: User): Long {
        val db = writableDatabase
        return daoUsers.insertUser(db, user)
    }

    fun insertUsers(users: List<User>): Long {
        val db = writableDatabase
        return daoUsers.insertUsers(db, users)
    }

    fun getAllUsers(): List<User> {
        val db = readableDatabase
        return daoUsers.getAllUsers(db)
    }

    fun getUserById(id: Int): User? {
        val db = readableDatabase
        return daoUsers.getUserById(db, id)
    }

    fun checkUser(userName: String, userPassword: String): Boolean {
        val db = readableDatabase
        return daoUsers.checkUser(db, userName, userPassword)
    }

    fun updateUser(user: User): Int {
        val db = writableDatabase
        return daoUsers.updateUser(db, user)
    }

    fun deleteUserById(id: Int): Int {
        val db = writableDatabase
        return daoUsers.deleteUser(db, id)
    }

}