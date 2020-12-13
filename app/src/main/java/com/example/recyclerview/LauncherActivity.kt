package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.Serializable

class LauncherActivity : AppCompatActivity() {

    companion object {
        const val DATA_KEY = "dataKey"
    }

    private val listItems = listOf(
        Item("23,603", "Spain"),
        Item("23,974", "Germany"),
        Item("21,638", "Iran"),
        Item("14,485", "France"),
        Item("8,897", "Korea, South"),
        Item("7,014", "Switzerland"),
        Item("5,071", "United Kingdom"),
        Item("4,216", "Netherlands"),
        Item("3,401", "Belgium"),
        Item("3,244", "Austria"),
        Item("2,257", "Norway"),
        Item("1,934", "Sweden"),
        Item("1,600", "Portugal"),
        Item("1,530", "Denmark")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        startActivity(Intent(this@LauncherActivity,
                            RecyclerViewActivity::class.java).apply {
                            putExtra(DATA_KEY, listItems as Serializable)
                        })
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Normal RecyclerView", fontSize = 32.sp)
                }
                Button(
                    onClick = {
                        startActivity(Intent(this@LauncherActivity,
                            LazyColumnActivity::class.java).apply {
                                putExtra(DATA_KEY, listItems as Serializable)
                        })
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Compose LazyColumn", fontSize = 32.sp)
                }
            }
        }
    }
}

data class Item(val number: String, val country: String): Serializable
