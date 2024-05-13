package com.example.easyshop

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyshop.databinding.ActivitySmartPhonesBinding
import com.example.easyshop.locals.SmartPhoneDao
import com.example.easyshop.locals.SmartPhoneDatabase
import com.example.easyshop.locals.SmartPhoneEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SmartPhonesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySmartPhonesBinding
    private lateinit var adapter: SmartPhoneAdapter
    private lateinit var smartPhoneDao: SmartPhoneDao
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySmartPhonesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val smartPhoneDatabase = SmartPhoneDatabase.getDatabase(this)
        smartPhoneDao = smartPhoneDatabase.smartPhoneDao()
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val dataInserted = sharedPreferences.getBoolean("data_inserted", false)
        if (!dataInserted) {
            insertDummyData()
        }

        loadDataFromDatabase()
    }

    private fun loadDataFromDatabase() {
        GlobalScope.launch(Dispatchers.IO) {
            val smartPhoneList = smartPhoneDao.getAllSmartPhones()
            launch(Dispatchers.Main) {
                setupRecyclerView(smartPhoneList)
            }
        }
    }

    private fun setupRecyclerView(smartPhoneList: List<SmartPhoneEntity>) {
        adapter = SmartPhoneAdapter(smartPhoneList)
        with(binding) {
            smartPhoneRV.layoutManager = LinearLayoutManager(this@SmartPhonesActivity)
            smartPhoneRV.adapter = adapter
        }
    }

    private fun insertDummyData() {
        GlobalScope.launch(Dispatchers.IO) {
            smartPhoneDao.insertSmartPhones(dummySmartPhoneData)
            sharedPreferences.edit().putBoolean("data_inserted", true).apply()
        }
    }

    companion object {
        private val dummySmartPhoneData = listOf(
            SmartPhoneEntity(
                smartPhoneIV = R.drawable.ic_launcher_background,
                smartPhoneTitle = "Iphone 15 pro max",
                smartPhoneDescription = "Natural Titanium . iPhone 15 Pro Max has a 6.7-inch all-screen Super Retina XDR display with the Dynamic Island.",
                price = 1200
            ),
            SmartPhoneEntity(
                smartPhoneIV = R.drawable.ic_launcher_background,
                smartPhoneTitle = "Iphone 15 pro",
                smartPhoneDescription = "Black Titanium . iPhone 15 Pro has a 6.1-inch all-screen Super Retina XDR display with the Dynamic Island.",
                price = 100
            ),
            SmartPhoneEntity(
                smartPhoneIV = R.drawable.ic_launcher_background,
                smartPhoneTitle = "Iphone 15",
                smartPhoneDescription = "Yellow . iPhone 15 has a 6.1-inch all-screen Super Retina XDR display.",
                price = 800
            ),
            SmartPhoneEntity(
                smartPhoneIV = R.drawable.ic_launcher_background,
                smartPhoneTitle = "Iphone 15 plus",
                smartPhoneDescription = "Pink . iPhone 15 Plus has a 6.3-inch all-screen Super Retina XDR.",
                price = 900
            )
        )
    }
}
