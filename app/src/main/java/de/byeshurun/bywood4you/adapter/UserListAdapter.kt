package de.byeshurun.bywood4you.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.byeshurun.bywood4you.R
import de.byeshurun.bywood4you.model.User
import de.byeshurun.bywood4you.users.UserDetailsActivity

class UserListAdapter (
        private val context: Context,
        private val users: List<User>
    ) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    inner class ViewHolder(userItem: View) : RecyclerView.ViewHolder(userItem) {
        val userInfoUserName: TextView = userItem.findViewById(R.id.userInfoUserName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.user_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentUsers = users[position]

        holder.userInfoUserName.text = currentUsers.name
        holder.itemView.setOnClickListener {

            this.context.startActivity(
                Intent(this.context, UserDetailsActivity::class.java)
                    .putExtra(this.context.getString(R.string.user_id), currentUsers.id)
            )
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}
