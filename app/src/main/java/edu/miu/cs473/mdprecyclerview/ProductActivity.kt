package edu.miu.cs473.mdprecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import edu.miu.cs473.mdprecyclerview.databinding.ActivityProductBinding

class ProductActivity : ComponentActivity() {

    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setImageResource(intent.getIntExtra("productImage", androidx.core.R.drawable.ic_call_answer_video))
        binding.productTitle.text = intent.getStringExtra("productTitle")
        binding.desc.text = intent.getStringExtra("productDesc")
        binding.priceText.text = intent.getDoubleExtra("productPrice",0.00).toString()

        binding.homeBtn.setOnClickListener{
            val intent = Intent(this@ProductActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}


