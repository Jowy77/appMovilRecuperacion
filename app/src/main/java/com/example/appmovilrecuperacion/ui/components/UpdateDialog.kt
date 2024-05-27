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
import com.example.appmovilrecuperacion.ui.model.User
import com.example.appmovilrecuperacion.viewModel.ViewModelRetrofit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDialog(game: Game, viewModel: ViewModelRetrofit, onCloseDialog: () -> Unit){
    var nuevoNombreJuego by remember { mutableStateOf(game.nombreJuego) }
    var nuevaFechaSalida by remember { mutableStateOf(game.fechaSalida) }
    var nuevaloracionPersonal by remember { mutableStateOf(game.caratula) }
    var nuevaCaratula by remember { mutableStateOf(game.caratula) }

    AlertDialog(
        onDismissRequest = {
            onCloseDialog()

        },
        title = {
            Text(text = "MODIFICAR/ELIMINAR GAME")
        },
        text = {
            Column {
                Text("DATOS ACTUALES DEL JUEGO:")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nuevoNombreJuego,
                    onValueChange = { nuevoNombreJuego = it },
                    label = { Text("Actualizar nombre juego") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nuevaloracionPersonal,
                    onValueChange = { nuevaloracionPersonal = it },
                    label = { Text("Actualizar valoriacion") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nuevaFechaSalida,
                    onValueChange = { nuevaFechaSalida = it },
                    label = { Text("Actualizar fecha salida") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nuevaCaratula,
                    onValueChange = { nuevaCaratula = it },
                    label = { Text("Actualizar caratula") }
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
                    viewModel.putGame(game.idGame, game)
                    onCloseDialog()
                }) {
                Text("Actualizar")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    viewModel.unlinkUserGame(1, game.idGame)
                    onCloseDialog()

                }) {
                Text("Borrar")
            }
        }
    )
}