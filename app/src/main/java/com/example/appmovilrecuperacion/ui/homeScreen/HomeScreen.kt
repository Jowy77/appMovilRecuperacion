package com.example.appmovilrecuperacion.ui.homeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appmovilrecuperacion.ui.components.CardItem
import com.example.appmovilrecuperacion.ui.components.CreateDailog
import com.example.appmovilrecuperacion.ui.components.UpdateDialog
import com.example.appmovilrecuperacion.ui.model.Game
import com.example.appmovilrecuperacion.viewModel.ViewModelRetrofit

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController?, viewModel: ViewModelRetrofit) {
    val itemList = remember { mutableStateListOf(1,2,3,4,5) }
    val selectedItems = remember { mutableStateListOf<Int>() }
    var showCheckboxes by remember { mutableStateOf(false) }

    var openDialogCrearHuesped by remember { mutableStateOf(false) }
    var openDialogModificar by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color(red = 255, green = 255, blue = 255))
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Your list of games",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            color = Color.Black
        )
        Row {
            //BOTON AÑADIR
            ExtendedFloatingActionButton(
                onClick = {
                    openDialogCrearHuesped = true
                },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                androidx.compose.material3.Text(
                    text = "ADD HUESPED",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp),
                )
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "ADD",
                    tint = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }
            //BOTON ELIMINAR
            Button(
                enabled = selectedItems.isNotEmpty(),
                onClick = {
                    itemList.removeAll(selectedItems)
                    selectedItems.clear()
                    showCheckboxes = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(color = Color.Cyan)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Black
                )
            }
            if (openDialogCrearHuesped) {
                CreateDailog(viewModel = viewModel) {
                    openDialogCrearHuesped = false
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 60.dp)
            ) {
                items(itemList.size){index ->
                    val item = itemList[index]
                    CardItem(
                        item = item,
                        isSelected = selectedItems.contains(item),
                        showCheckbox = showCheckboxes,
                        onClick = {
                            if (selectedItems.isEmpty()) {
                                openDialogModificar=true
                            } else {
                                if (selectedItems.contains(item)) {
                                    selectedItems.remove(item)
                                } else {
                                    selectedItems.add(item)
                                }
                                if (selectedItems.isEmpty()) {
                                    showCheckboxes = false
                                }
                            }
                        },
                        onLongClick = {
                            showCheckboxes = true
                            if (!selectedItems.contains(item)) {
                                selectedItems.add(item)
                            }
                        }
                    )
                }
            }
        }
    }
}
