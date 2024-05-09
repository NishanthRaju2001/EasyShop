package com.example.easyshop

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.easyshop.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initPref()
        initViews()
    }
    private fun initPref(){
        sharedPreferences=SecuredSharedPref.getSecuredSharePref(this)

    }
    private fun initViews(){
        with(binding){
            val value =sharedPreferences.getString(PrefConst.IS_LOGIN, NO)
            if(value== YES){
                jumpToDashboardScreen()
            }
            loginBTN.setOnClickListener{
                sharedPreferences.edit().putString(PrefConst.IS_LOGIN, YES).apply()
                jumpToDashboardScreen()
            }
            registerTV.setOnClickListener{
                //sharedPreferences.edit().putString(PrefConst.IS_LOGIN, YES).apply()
                jumpToRegisterScreen()
            }
        }
    }

    private fun jumpToRegisterScreen() {
        startActivity(Intent(this,RegisterActivity::class.java))
    }

    private fun jumpToDashboardScreen() {
        startActivity(Intent(this,DashboardActivity::class.java))
    }
    private companion object{
        const val YES ="yes"
        const val NO ="no"
    }
}