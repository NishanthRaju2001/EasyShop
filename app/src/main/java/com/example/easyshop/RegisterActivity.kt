package com.example.easyshop

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.easyshop.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityRegisterBinding.inflate(layoutInflater)
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
        sharedPreferences=SecuredSharedPref.getRegisterSecuredSharePref(this)

    }
    private fun initViews(){
        with(binding){
            val value =sharedPreferences.getString(PrefConst.IS_REGISTER, NO)
            if(value== YES){
                jumpToLoginScreen()
            }
            registerBTN.setOnClickListener{
               // sharedPreferences.edit().putString(PrefConst.IS_REGISTER, YES).apply()
                jumpToLoginScreen()
            }
            loginTextRegister.setOnClickListener{
              // sharedPreferences.edit().putString(PrefConst.IS_REGISTER, YES).apply()
                jumpToLoginScreen()
            }
        }
    }


    private fun jumpToLoginScreen() {
        startActivity(Intent(this,LoginActivity::class.java))
    }
    private companion object{
        const val YES ="yes"
        const val NO ="no"
    }
}