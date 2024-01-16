package de.byeshurun.bywood4you.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.byeshurun.bywood4you.R
import de.byeshurun.bywood4you.adapter.UserListAdapter
import de.byeshurun.bywood4you.data.MockData
import de.byeshurun.bywood4you.logic.database.DbManager

class UserAdministrationActivity : AppCompatActivity() {
    private val TAG: String? = UserAdministrationActivity::class.simpleName
    private val userList: RecyclerView by lazy { this.findViewById(R.id.userList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_administration_activity_layout)
    }

    override fun onResume() {
        super.onResume()
        initUserList()
//        Log.i(TAG,"onResume()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,"onStop()")
    }

    private fun initUserList() {
        userList.layoutManager = LinearLayoutManager(this)
//        userList.adapter = UserListAdapter(this, MockData.users)
        userList.adapter = UserListAdapter(this, DbManager.getInstance(this).getAllUsers())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.user_administration_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.addUser){
            startUserDetailsActivity()
        }
        return true
    }

    private fun startUserDetailsActivity() {
        this.startActivity(Intent(this, UserDetailsActivity::class.java))
    }

}

