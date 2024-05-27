package com.example.appmovilrecuperacion.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appmovilrecuperacion.ui.model.Game
import com.example.appmovilrecuperacion.viewModel.ViewModelRetrofit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateDailog(viewModel: ViewModelRetrofit, onCloseDialog: () -> Unit){
    var nuevoNombreJuego by remember { mutableStateOf("") }
    var nuevaFechaSalida by remember { mutableStateOf("") }
    var nuevaloracionPersonal by remember { mutableStateOf("") }
    var nuevaCaratula by remember { mutableStateOf("") }
    val game = Game(nuevoNombreJuego, nuevaFechaSalida, nuevaloracionPersonal, nuevaCaratula)

    AlertDialog(
        onDismissRequest = {
            onCloseDialog()

        },
        title = {
            Text(text = "CREAR JUEGO")
        },
        text = {
            Column {
                Text("Introduce los datos para el juego:")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nuevoNombreJuego,
                    onValueChange = { nuevoNombreJuego = it },
                    label = { Text("Nombre juego") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nuevaFechaSalida,
                    onValueChange = { nuevaFechaSalida = it },
                    label = { Text("Fecha de salida") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nuevaloracionPersonal,
                    onValueChange = { nuevaloracionPersonal = it },
                    label = { Text("Valoracion personal") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nuevaCaratula,
                    onValueChange = { nuevaCaratula = it },
                    label = { Text("Caratula") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    game.nombreJuego = nuevoNombreJuego
                    game.fechaSalida = nuevaFechaSalida
                    game.caratula = nuevaCaratula
                    game.valoracionPersonal = nuevaloracionPersonal
                    viewModel.postGame(game)
                    onCloseDialog()

                }) {
                Text("Crear")
            }
        },
        dismissButton = {
            Button(
                onClick = {

                    onCloseDialog()

                }) {
                Text("Salir")
            }
        }
    )
}