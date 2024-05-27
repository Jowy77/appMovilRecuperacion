package com.example.appmovilrecuperacion.ui.routes

sealed class Routes (val ruta : String) {
    object LoginScreen : Routes("loginScreen")
    object NewUserScreen : Routes("newuserscreen")
    object HomeScreen : Routes("homescreen")
    object AddGameScreen : Routes("addgameScreen")
    object GameInfoScreen : Routes("gameinfoscreen")

}