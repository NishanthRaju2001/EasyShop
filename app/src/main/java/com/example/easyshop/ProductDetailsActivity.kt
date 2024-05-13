package com.example.easyshop

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyshop.databinding.ActivityProductDetailsBinding
import com.example.easyshop.locals.SmartPhoneDao
import com.example.easyshop.locals.SmartPhoneDatabase
import com.example.easyshop.locals.SmartPhoneEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var smartPhoneDao: SmartPhoneDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val smartPhoneDatabase = SmartPhoneDatabase.getDatabase(this)
        smartPhoneDao = smartPhoneDatabase.smartPhoneDao()

        val smartPhoneId = intent.getLongExtra("smartPhoneId", -1)

        if (smartPhoneId != -1L) {
            loadSmartPhoneDetails(smartPhoneId)
        } else {

        }
        binding.backIV.setOnClickListener {
            navigateToDashboard()
        }
    }

    private fun loadSmartPhoneDetails(smartPhoneId: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            val smartPhone = smartPhoneDao.getSmartPhoneById(smartPhoneId)
            launch(Dispatchers.Main) {
                displaySmartPhoneDetails(smartPhone)
            }
        }
    }
    private fun navigateToDashboard() {
        val intent = Intent(this, SmartPhonesActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun displaySmartPhoneDetails(smartPhone: SmartPhoneEntity?) {
        if (smartPhone != null) {
            binding.phoneName.text = smartPhone.smartPhoneTitle
            binding.phoneDescription.text = smartPhone.smartPhoneDescription
            binding.phoneCost.text = "${smartPhone.price} USD"
            binding.phoneIV.setImageResource(smartPhone.smartPhoneIV)

        } else {
        }
    }
}
