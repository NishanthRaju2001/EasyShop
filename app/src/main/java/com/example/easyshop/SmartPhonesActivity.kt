package com.example.easyshop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyshop.databinding.ActivitySmartPhonesBinding

class SmartPhonesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySmartPhonesBinding
    private lateinit var adapter: SmartPhoneAdapter
    private val smartPhoneList = ArrayList<SmartPhoneList>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySmartPhonesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        prepareDataSet()
    }

    private fun prepareDataSet() {

        smartPhoneList.apply {
            add(
                SmartPhoneList(
                    smartPhoneIV = R.drawable.ic_launcher_background,
                    smartPhoneTitle = "Iphone 15 pro max",
                    smartPhoneDescription = "Natural Titanium . iPhone 15 Pro Max has a 6.7-inch all-screen Super Retina XDR display with the Dynamic Island.",
                    price = 1200
                )
            )
            add(
                SmartPhoneList(
                    smartPhoneIV = R.drawable.ic_launcher_background,
                    smartPhoneTitle = "Iphone 15 pro",
                    smartPhoneDescription = "Blue Titanium . iPhone 15 Pro Max has a 6.3-inch all-screen Super Retina XDR display with the Dynamic Island.",
                    price = 1000
                )
            )
            add(
                SmartPhoneList(
                    smartPhoneIV = R.drawable.ic_launcher_background,
                    smartPhoneTitle = "Realme Narzo 50",
                    smartPhoneDescription = "realme narxo 50(Blue, 4GB RAM, 64GB Storage), Helium Processor",
                    price = 200
                )
            )
            add(
                SmartPhoneList(
                    smartPhoneIV = R.drawable.ic_launcher_background,
                    smartPhoneTitle = "One Plus Nord 2E",
                    smartPhoneDescription = "Mate Blue, 8GB RAM, 128GB Storage,MediaTek Dimensity 1200.",
                    price = 500
                )
            )
        }
        adapter = SmartPhoneAdapter(smartPhoneList)
        with(binding) {
            smartPhoneRV.layoutManager = LinearLayoutManager(this@SmartPhonesActivity)
            smartPhoneRV.adapter = adapter
        }
    }
}