package com.example.recyclerview

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recyclerview.LauncherActivity.Companion.DATA_KEY

class LazyColumnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listItems = intent.getSerializableExtra(DATA_KEY) as List<Item>
        setContent {
            MyLazyColumn(
                listItems = listItems
            )
        }
    }
}

@Composable
fun MyLazyColumn(
    modifier: Modifier = Modifier,
    listItems: List<Item>,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp)
    ) {
        items(listItems) {
            ViewItem(itemText = it)
            Spacer(modifier = Modifier.fillMaxWidth().height(16.dp))
        }
    }
}

@Composable
fun ViewItem(
    itemText: Item
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = Color(0xFFCCCCCC),
    ) {
        Row {
            Text(
                text = itemText.number,
                modifier = Modifier.padding(8.dp),
                style = TextStyle(fontSize = 24.sp, color = Color.Red),
                textAlign = TextAlign.Center
            )
            Text(
                text = itemText.country,
                modifier = Modifier.padding(8.dp),
                style = TextStyle(fontSize = 24.sp, color = Color.Black),
                textAlign = TextAlign.Center
            )
        }
    }
}
