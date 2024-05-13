package com.example.easyshop

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.easyshop.databinding.ActivityRegisterBinding
import com.example.easyshop.locals.User
import com.example.easyshop.locals.UserDao
import com.example.easyshop.locals.UserDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userDao: UserDao
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDao = UserDatabase.getInstance(this).userDao()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initPref()
        initViews()
    }

    private fun initPref() {
        sharedPreferences = SecuredSharedPref.getRegisterSecuredSharePref(this)
    }

    private fun initViews() {
        with(binding) {
            val value = sharedPreferences.getString(PrefConst.IS_REGISTER, NO)
            if (value == YES) {
                jumpToLoginScreen()
            }
            registerBTN.setOnClickListener {
                val fullName = fullNameET.text.toString()
                val mobileNumber = mobileET.text.toString()
                val email = emailET.text.toString()
                val password = pwdET.text.toString()

                if (fullName.isNotEmpty() && mobileNumber.isNotEmpty() && email.isNotEmpty() &&
                    email.isNotEmpty() && password.isNotEmpty()
                ) {
                    val user = User(
                        fullName = fullName,
                        mobileNumber = mobileNumber,
                        email = email,
                        password = password
                    )
                    registerUser(user)
                    jumpToLoginScreen()
                } else {
                }
            }

            loginTextRegister.setOnClickListener {
                jumpToLoginScreen()
            }
        }
    }

    private fun registerUser(user: User) {
        userDao.insertUser(user)
        sharedPreferences.edit().putString(PrefConst.IS_REGISTER, YES).apply()
    }


    private fun jumpToLoginScreen() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private companion object {
        const val YES = "yes"
        const val NO = "no"
    }
}
