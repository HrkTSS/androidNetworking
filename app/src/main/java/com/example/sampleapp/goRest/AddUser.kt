package com.example.sampleapp.goRest

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.sampleapp.R
import com.example.sampleapp.goRest.Constant.TAG
import com.example.sampleapp.goRest.networking.Repository
import kotlinx.android.synthetic.main.activity_add_user.*

class AddUser : AppCompatActivity() {
    lateinit var user: UserData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        getExtra()
    }

    private fun getExtra() {
        if (intent.hasExtra("users")) {
            user = intent.getParcelableExtra("users") ?: UserData()
            et_name.setText(user.name)
            et_mail.setText(user.email)
            et_gender.setText(user.gender)
            et_active.setText(user.status)
            btn_create.isVisible = false
        } else btn_update.isVisible = false
        launchUI()
    }

    private fun launchUI() {
        btn_create.setOnClickListener { createUser() }
        btn_update.setOnClickListener { updateUser() }
    }

    private fun updateUser() {
        val data=getUserData()
        data.id=user.id
        Repository().updateUser(this, data) { res, err ->
            if (res != null) {Toast.makeText(this, res.toString(), Toast.LENGTH_SHORT).show()}
            else Toast.makeText(
                this,
                err?.data?.map { "${it.field} : ${it.message} \n" }.toString(),
                Toast.LENGTH_SHORT
            ).show()
            Log.d(TAG, err?.data.toString())
            clearFields(et_active,et_gender,et_mail,et_name)
        }
    }

    private fun createUser() {
        Repository().postUsers(this, getUserData()) { res, err ->
            if (res != null) Toast.makeText(this, res.toString(), Toast.LENGTH_SHORT).show()
            else Toast.makeText(
                this,
                err?.data?.map { "${it.field} : ${it.message} \n" }.toString(),
                Toast.LENGTH_SHORT
            ).show()
            Log.d(TAG, err?.data.toString())
            clearFields(et_active,et_gender,et_mail,et_name)
        }
    }

    private fun getName() = et_name.text.toString()
    private fun getEmail() = et_mail.text.toString()
    private fun getGender() = et_gender.text.toString()
    private fun getActive() = et_active.text.toString()

    private fun clearFields(vararg et: EditText) {
        for (item in et) item.text.clear()
    }

    private fun getUserData(): UserData {
        val userData = UserData()
        userData.email = getEmail()
        userData.gender = getGender()
        userData.name = getName()
        userData.status = getActive()
        return userData
    }
}