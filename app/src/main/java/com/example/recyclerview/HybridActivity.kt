package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityMainBinding

class HybridActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listItems = intent.getSerializableExtra(LauncherActivity.DATA_KEY) as List<Item>
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HybridActivity)
            adapter = HybridRecyclerViewAdapter(listItems)
            addItemDecoration(MarginItemDecoration(
                resources.getDimension(R.dimen.default_padding).toInt()))
        }
    }
}


class HybridRecyclerViewAdapter(private val listItems: List<Item>)
    : RecyclerView.Adapter<ComposeItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComposeItemViewHolder {
        return ComposeItemViewHolder(ComposeView(parent.context))
    }

    override fun onBindViewHolder(holder: ComposeItemViewHolder, position: Int) {
        holder.bindView(listItems[position])
    }

    override fun onViewRecycled(holder: ComposeItemViewHolder) {
        super.onViewRecycled(holder)
        holder.composeView.disposeComposition()
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}


class ComposeItemViewHolder(
    val composeView: ComposeView
) : RecyclerView.ViewHolder(composeView) {

    init {
        composeView.setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )
    }

    fun bindView(content: Item) {
        composeView.setContent {
            ViewItem(content)
        }
    }
}