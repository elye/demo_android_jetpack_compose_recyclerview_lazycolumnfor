package com.example.recyclerview

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.databinding.ViewItemBinding

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listItems = intent.getSerializableExtra(LauncherActivity.DATA_KEY) as List<Item>
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            adapter = RecyclerViewAdapter(listItems)
            addItemDecoration(MarginItemDecoration(
                resources.getDimension(R.dimen.default_padding).toInt()))
        }
    }
}

class RecyclerViewAdapter(private val listItems: List<Item>)
    : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ViewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}

class ItemViewHolder(private val viewBinding: ViewItemBinding)
    : RecyclerView.ViewHolder(viewBinding.root) {
    fun bindView(content: Item) {
        viewBinding.itemNumber.text = content.number
        viewBinding.itemCountry.text = content.country
    }
}

class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceHeight
            }
            left =  spaceHeight
            right = spaceHeight
            bottom = spaceHeight
        }
    }
}
