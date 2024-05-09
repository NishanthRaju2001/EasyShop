package com.example.easyshop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easyshop.databinding.ActivityDashboardBinding
import com.example.easyshop.databinding.ActivityMainBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var adapter: CategoryAdapter
    private val categoryList = ArrayList<CategoryList>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        prepareDataSet()

    }
    private fun prepareDataSet() {

        categoryList.apply {
            add(
                CategoryList(
                    albumIV = R.drawable.smart_phone,
                    categoryTitle = "Smart Phones"
                )
            )
            add(
                CategoryList(
                    albumIV = R.drawable.laptop_icon,
                    categoryTitle = "Laptops"
                )
            )
            add(
                CategoryList(
                    albumIV = R.drawable.furniture_icon,
                    categoryTitle = "Furniture"
                )
            )
            add(
                CategoryList(
                    albumIV = R.drawable.washing_machine_icon,
                    categoryTitle = "Washing Machine"
                )
            )
            add(
                CategoryList(
                    albumIV = R.drawable.mens_cloth_icon,
                    categoryTitle = "Mens Clothing"
                )
            )
            add(
                CategoryList(
                    albumIV = R.drawable.womens_cloth_icon,
                    categoryTitle = "Women Clothing"
                )
            )
        }
        adapter = CategoryAdapter(categoryList)
        with(binding) {
            categoryRV.layoutManager=GridLayoutManager(this@DashboardActivity,2)
            categoryRV.adapter=adapter
        }
    }
}