package edu.miu.cs473.mdprecyclerview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.miu.cs473.mdprecyclerview.adapter.CustomAdapter
import edu.miu.cs473.mdprecyclerview.databinding.ActivityCartBinding
import edu.miu.cs473.mdprecyclerview.viewModel.Product
import java.lang.reflect.Type

class CartActivity : ComponentActivity() {

    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference =  getSharedPreferences("Cart", Context.MODE_PRIVATE)
        val gson = Gson()
        val json: String? = sharedPreference.getString("CartList", null)
        var cartList = mutableListOf<Product>()
        if (json != null) {
            val type: Type = object : TypeToken<List<Product?>?>() {}.type
            cartList = gson.fromJson(json, type);
        }

        binding.cartList.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(this@CartActivity,cartList)
        binding.cartList.adapter = adapter

        binding.backHomeBtn.setOnClickListener{
            val intent = Intent(this@CartActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}