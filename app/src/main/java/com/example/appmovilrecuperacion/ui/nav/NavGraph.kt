package com.example.appmovilrecuperacion.ui.nav

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appmovilrecuperacion.R
import com.example.appmovilrecuperacion.ui.components.AppBar
import com.example.appmovilrecuperacion.ui.components.DrawerBody
import com.example.appmovilrecuperacion.ui.components.DrawerHeader
import com.example.appmovilrecuperacion.ui.newUserScreen.NewUserScreen
import com.example.appmovilrecuperacion.ui.components.MenuItem
import com.example.appmovilrecuperacion.ui.homeScreen.HomeScreen
import com.example.appmovilrecuperacion.ui.loginScreen.LoginScreen
import com.example.appmovilrecuperacion.ui.routes.Routes
import com.example.appmovilrecuperacion.viewModel.ViewModelRetrofit
import com.example.appmovilrecuperacion.viewModel.estadoApi
import kotlinx.coroutines.launch


@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter", "ComposableDestinationInComposeScope",
    "SuspiciousIndentation", "UnusedMaterialScaffoldPaddingParameter", "RememberReturnType"
)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    val showScaffold =
        currentDestination != Routes.LoginScreen.ruta && currentDestination != Routes.NewUserScreen.ruta

    val selectedItems = remember { mutableStateListOf<Int>() }
    var showCheckboxes by remember { mutableStateOf(false) }

    val viewModel: ViewModelRetrofit = viewModel()
    val estado by viewModel.estadoLlamada.collectAsState()
    val listaUsers by viewModel.listaUser.collectAsState()

    if (estado == estadoApi.LOADING) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
            Text(
                text = "CARGANDO API HOTEL",
                fontSize = 20.sp
            )
        }
    }else {

        if (showScaffold) {
            Scaffold(
                modifier = Modifier.background(Color(red = 255, green = 255, blue = 255)),
                scaffoldState = scaffoldState,
                topBar = {
                    AppBar(
                        onNavigationIconClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    )
                },
                drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                drawerContent = {
                    Column(
                        modifier = Modifier
                            .background(Color(red = 255, green = 255, blue = 255))
                            .fillMaxSize()
                    ) {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "home",
                                    title = "Home",
                                    contentDescription = "Go to home screen",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "push",
                                    title = "Add game",
                                    contentDescription = "Go to external api game list",
                                    icon = Icons.Default.Add
                                ),
                                MenuItem(
                                    id = "close",
                                    title = "Close session",
                                    contentDescription = "Close session",
                                    icon = Icons.Default.ExitToApp
                                ),
                            ),
                            onItemClick = {
                                when (it.id) {
                                    "home" -> navController.navigate(Routes.HomeScreen.ruta)
                                    "close" -> navController.navigate(Routes.LoginScreen.ruta)
                                }
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            }
                        )
                    }
                },
                floatingActionButton = {
                    if (selectedItems.isNotEmpty()) {
                    }
                },
                bottomBar = {
                    Row(
                        modifier = Modifier
                            .background(Color(red = 255, green = 255, blue = 255))
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { navController.navigate(Routes.HomeScreen.ruta) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.home),
                                contentDescription = "",
                                tint = Color.Black,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(onClick = { /*DIALOGO DE AGREGAR JUEGO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = "",
                                tint = Color.Black,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                },
                content = {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.LoginScreen.ruta
                    ) {
                        composable(Routes.LoginScreen.ruta) {
                            LoginScreen(navController = navController)
                        }
                        composable(Routes.NewUserScreen.ruta) {
                            NewUserScreen(navController = navController)
                        }
                        composable(Routes.HomeScreen.ruta) {
                            HomeScreen(navController = navController, viewModel = viewModel)
                        }
                    }
                }
            )
        } else {
            NavHost(navController = navController, startDestination = Routes.LoginScreen.ruta) {
                composable(Routes.LoginScreen.ruta) {
                    LoginScreen(navController = navController)
                }
                composable(Routes.NewUserScreen.ruta) {
                    NewUserScreen(navController = navController)
                }
                composable(Routes.HomeScreen.ruta) {
                    HomeScreen(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginPreview() {
    NavGraph()
}