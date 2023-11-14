package edu.miu.cs473.mdprecyclerview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.miu.cs473.mdprecyclerview.adapter.CustomAdapter
import edu.miu.cs473.mdprecyclerview.databinding.ActivityMainBinding
import edu.miu.cs473.mdprecyclerview.viewModel.Product

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.productList.layoutManager = LinearLayoutManager(this)
        val products = ArrayList<Product>()
        val sampleImage = androidx.core.R.drawable.ic_call_answer_video
        products.add(Product(sampleImage,"iPad", "iPad Pro 11-inch", 400.0))
        products.add(Product(sampleImage,"MacBook M3 Pro", "12-core CPU\n18-core GPU", 2500.00))
        products.add(Product(sampleImage,"Dell Inspiron", "13th Gen Intel® Core™ i7", 1499.00))
        products.add(Product(sampleImage,"Logitech Keyboard", "Logitech - PRO X\nTKL LIGHTSPEED Wireless", 199.00))
        products.add(Product(sampleImage,"MacBook M3 Max", "14-core CPU\n30-core GPU", 3499.00))
        val adapter = CustomAdapter(this@MainActivity,products)
        binding.productList.adapter = adapter
    }
}