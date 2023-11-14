package edu.miu.cs473.mdprecyclerview.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.miu.cs473.mdprecyclerview.ProductActivity
import edu.miu.cs473.mdprecyclerview.R
import edu.miu.cs473.mdprecyclerview.viewModel.Product
import java.lang.reflect.Type


class CustomAdapter(private val context: Context, private val mList: List<Product>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = mList[position]
        holder.imageView.setImageResource(product.image)
        holder.textView.text = product.productName
        holder.textView2.text = product.productDescription
        holder.price.text = "$${product.cost}"
        holder.productCard.setOnClickListener{
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra("productImage",product.image)
            intent.putExtra("productTitle",product.productName)
            intent.putExtra("productDesc",product.productDescription)
            intent.putExtra("productPrice",product.cost)
            context.startActivity(intent)
        }
        holder.addBtn.setOnClickListener{
            val sharedPreference =  context.getSharedPreferences("Cart",Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            val gson = Gson()
            val json: String? = sharedPreference.getString("CartList", null)
            var cartList = mutableListOf<Product>()
            if (json != null) {
                val type: Type = object : TypeToken<List<Product?>?>() {}.type
                cartList = gson.fromJson(json, type);
            }
            cartList.add(product)
            editor.putString("CartList",gson.toJson(cartList))
            editor.apply()
            Toast.makeText(context,"${product.productName} added to cart!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        val price: TextView = itemView.findViewById(R.id.price)
        val addBtn: Button = itemView.findViewById(R.id.addBtn)
        val productCard: CardView = itemView.findViewById(R.id.productCard)
    }
}