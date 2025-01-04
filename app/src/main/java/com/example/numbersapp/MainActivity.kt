package com.example.numbersapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.numbersapp.repository.FactRepository
import com.example.numbersapp.ui.theme.NumbersAppTheme
import com.example.numbersapp.ui.view.Main
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumbersAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(modifier = Modifier.padding(innerPadding))
                }
            }
        }

        val fact = FactRepository()
        val myCoroutineScope = CoroutineScope(Dispatchers.Main)

        myCoroutineScope.launch {
            Log.d("result", fact.getMathFact(12).toString())
        }

    }
}
