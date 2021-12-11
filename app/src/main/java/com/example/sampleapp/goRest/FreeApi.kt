package com.example.sampleapp.goRest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.sampleapp.R
import com.example.sampleapp.goRest.networking.Networking
import com.example.sampleapp.goRest.networking.Repository
import kotlinx.android.synthetic.main.activity_free_api.*

class FreeApi : AppCompatActivity() {
    private lateinit var networking: Networking
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_free_api)
        launchUI()
    }

    private fun launchUI() {
        networking = Networking()
        btn_fetch.setOnClickListener { fetchUsers() }
        btn_post.setOnClickListener { startActivity(Intent(this, AddUser::class.java)) }
    }

    private fun fetchUsers() {
        pg_bar.isVisible = true
        Repository().fetchUsers(this) { response, _ ->
            response?.data?.let {
                rv.adapter = UserAdapter(it)
                pg_bar.isVisible = false
            }
        }

    }

}