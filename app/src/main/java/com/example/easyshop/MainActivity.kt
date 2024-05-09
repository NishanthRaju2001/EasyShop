package com.example.easyshop

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.easyshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        initPreferences()
        initViews()

    }
    private fun initViews(){
        with(binding){
            val value =sharedPreferences.getString(PrefConst.IS_MOBILE, NO)
            if(value== YES){
                jumpToHomeScreen()
            }
            nextBTN.setOnClickListener{
                sharedPreferences.edit().putString(PrefConst.IS_MOBILE, YES).apply()
                jumpToHomeScreen()
            }
            skipTV.setOnClickListener{
                sharedPreferences.edit().putString(PrefConst.IS_MOBILE, YES).apply()
                jumpToLoginScreen()
            }
        }
    }

    private fun jumpToLoginScreen() {
        startActivity(Intent(this,LoginActivity::class.java))
    }

    private fun jumpToHomeScreen() {
        startActivity(Intent(this,FurnitureActivity::class.java))
    }

    private fun initPreferences(){
        sharedPreferences=getSharedPreferences(PrefConst.MOBILE_FILE_NAME, Context.MODE_PRIVATE)
    }
    private companion object{
        const val YES ="yes"
        const val NO ="no"
    }
}