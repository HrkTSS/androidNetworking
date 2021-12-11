package com.example.sampleapp.goRest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import kotlinx.android.synthetic.main.list_users.view.*

class UserAdapter(private val users: List<UserData>) :
    RecyclerView.Adapter<UserAdapter.UserHolder>() {
    class UserHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {
        val name = view.tv_one!!
        val email = view.tv_two!!
        val gender = view.tv_three!!
        val status = view.tv_four!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_users, parent, false),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = users[position]
        holder.name.text = user.name
        holder.email.text = user.email
        holder.gender.text = user.gender
        holder.status.text = user.status
        holder.itemView.setOnClickListener {
            holder.context.startActivity(
                Intent(
                    holder.context, AddUser::class.java
                ).putExtra("users", user)
            )
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}