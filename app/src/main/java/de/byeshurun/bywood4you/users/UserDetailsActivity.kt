package de.byeshurun.bywood4you.users

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import de.byeshurun.bywood4you.R
import de.byeshurun.bywood4you.logic.database.DbManager
import de.byeshurun.bywood4you.model.User

class UserDetailsActivity : AppCompatActivity(), OnClickListener {

    companion object {
        private val TAG: String? = UserDetailsActivity::class.simpleName
        private const val NO_USER_ID: Int = -1
    }

    private var currentUser: User? = null

    private val userId: TextView by lazy { this.findViewById(R.id.userId) }
    private val userName: TextView by lazy { this.findViewById(R.id.userName) }
    private val userDepartment: TextView by lazy { this.findViewById(R.id.userDepartment) }
    private val userPassword: TextView by lazy { this.findViewById(R.id.userPassword) }
    private val submitUser: Button by lazy { this.findViewById(R.id.submitUser) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.user_details_activity_layout)

        this.submitUser.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        showUserById()
        Log.i(TAG, "onResume()")
    }

    private fun showUserById() {
        val userId = this.intent.getIntExtra(this.getString(R.string.user_id), NO_USER_ID)

        if (userId != NO_USER_ID) {
            //            currentUser = MockData.users.find { it.id == userId }!!

            currentUser = DbManager.getInstance(this).getUserById(userId)
            this.userId.setText(currentUser?.id.toString())
            this.userName.setText(currentUser?.name)
            this.userDepartment.setText(currentUser?.department)
            this.userPassword.setText(currentUser?.password)

        }
    }

    override fun onClick(v: View?) {

        val name = this.userName.text.toString()
        val department = this.userDepartment.text.toString()
        val password = this.userPassword.text.toString()

        if (userDataIsValid(name, department, password)) {

            if (userNeedsToBeUpdated()) {
                val userToUpdate = User(currentUser!!.id, name, department, password)

                DbManager.getInstance(this).updateUser(userToUpdate)
            } else {
                val userToInsert = User(name, department, password)

                DbManager.getInstance(this).insertUser(userToInsert)
            }
            showUserSubmitSuccessMessage()
        } else {
            showUserSubmitFailMessage()
        }
    }

    private fun userNeedsToBeUpdated() = currentUser != null

    private fun userDataIsValid(name: String, department: String, password: String): Boolean {
        return (name.isNotEmpty() && department.isNotEmpty() && password.isNotEmpty())
    }

    private fun showUserSubmitSuccessMessage() {
        showUserInformation(R.string.submitted_user_data_successfully,)
    }

    private fun showUserSubmitFailMessage() {
        showUserInformation(R.string.submitting_user_data_failed)
    }


    private fun showUserInformation(@StringRes messageResId: Int) {
        Toast.makeText(
            this,
            messageResId,
            Toast.LENGTH_SHORT
        ).show()
    }
}