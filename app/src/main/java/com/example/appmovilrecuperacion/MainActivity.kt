package com.example.appmovilrecuperacion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.appmovilrecuperacion.ui.nav.NavGraph
import com.example.appmovilrecuperacion.ui.theme.AppMovilRecuperacionTheme

class MainActivity : ComponentActivity() {
    @SuppressLint(
        "UnusedMaterial3ScaffoldPaddingParameter",
        "UnusedMaterialScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppMovilRecuperacionTheme {

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                        NavGraph()
                }
            }
        }
    }
}