package com.example.angatkinmirea
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.angatkinmirea.ui.theme.AngatkinMIREATheme

class MainActivity : ComponentActivity() {
    private val TAG = "LIFECYCLE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        enableEdgeToEdge()
        setContent {
            AngatkinMIREATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Hello(name = "Александр", modifier = Modifier.padding(innerPadding))
                    // ItemListScreen(itemsList)
                    CounterScreen()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}

@Composable
fun CounterScreen(viewModel: CounterViewModel = viewModel()) {
    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Счётчик: ${viewModel.count}", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.plus_count() }) {
            Text("Увеличить")
        }
    }
}

data class ListItem (
    val title: String,
    val description: String,
    val imageRes: Int
)

val itemsList = listOf(
    ListItem(
        title = "Первый элемент",
        description = "Описание первого элемента",
        imageRes = R.drawable.circle
    ),
    ListItem(
        title = "Второй элемент",
        description = "Описание второго элемента",
        imageRes = R.drawable.circle
    ),
    ListItem(
        title = "Третий элемент",
        description = "Описание третьего элемента",
        imageRes = R.drawable.circle
    ),
)

@Composable
fun ItemListScreen(items: List<ListItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            ItemCard(item)
        }
    }
}

@Composable
fun ItemCard(item: ListItem) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                modifier = Modifier.size(80.dp).padding(end = 12.dp)
            )
            Column {
                Text(text = item.title, style = MaterialTheme.typography.titleMedium)
                Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun Hello(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "",
        modifier = modifier
    )
}