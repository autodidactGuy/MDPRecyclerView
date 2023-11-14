package edu.miu.cs473.mdprecyclerview.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.miu.cs473.mdprecyclerview.ProductActivity
import edu.miu.cs473.mdprecyclerview.R
import edu.miu.cs473.mdprecyclerview.viewModel.Product

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
        holder.addBtn.setOnClickListener{
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra("productImage",product.image)
            intent.putExtra("productTitle",product.productName)
            intent.putExtra("productDesc",product.productDescription)
            intent.putExtra("productPrice",product.cost)
            context.startActivity(intent)
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
        //val itemView: View = itemView;
    }
}