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
import androidx.compose.material.IconButton
import androidx.compose.material3.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appmovilrecuperacion.R
import com.example.appmovilrecuperacion.ui.components.CardItem
import com.example.appmovilrecuperacion.ui.components.CreateDailog
import com.example.appmovilrecuperacion.ui.components.UpdateDialog
import com.example.appmovilrecuperacion.ui.model.Game
import com.example.appmovilrecuperacion.viewModel.ViewModelRetrofit

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController?, viewModel: ViewModelRetrofit) {
    val itemList = remember { mutableStateListOf(1,2,3,4,5) }
    val selectedItems = remember { mutableStateListOf<Int>() }
    var showCheckboxes by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

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

        SearchBar(
            query = query,
            onQueryChange = {query = it},
            onSearch = {active = false},
            active = active,
            onActiveChange = {active = it},
            placeholder = { Text(text = "Search")},
            leadingIcon = {
                IconButton(
                    onClick = {},
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.search),
                        contentDescription = null,
                        modifier = Modifier.padding(3.dp)) }
            },
            trailingIcon = {
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier.padding(4.dp)
                ) { Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.padding(3.dp))}},
            modifier = Modifier.fillMaxWidth()
        ){
            //SEARCHBAR
        }

        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement =  Arrangement.Center){
            //BOTON AÑADIR
            ExtendedFloatingActionButton(
                onClick = {
                    openDialogCrearHuesped = true
                },
                modifier = Modifier
                    .padding(8.dp),
            ) {
                androidx.compose.material3.Text(
                    text = "ADD GAME",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp),
                )
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "ADD",
                    tint = Color.Cyan,
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
