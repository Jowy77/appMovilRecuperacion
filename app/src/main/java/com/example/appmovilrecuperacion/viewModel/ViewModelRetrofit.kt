package com.example.appmovilrecuperacion.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovilrecuperacion.ui.apiInterface.gamesApi.retrofitService
import com.example.appmovilrecuperacion.ui.model.Game
import com.example.appmovilrecuperacion.ui.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class estadoApi{
    IDLE, LOADING, SUCCESS, ERROR
}

class ViewModelRetrofit : ViewModel() {
    private val _listaUser: MutableStateFlow<List<User>?> = MutableStateFlow(null)
    var listaUser = _listaUser.asStateFlow()

    private val _listaGame: MutableStateFlow<List<Game>?> = MutableStateFlow(null)
    var listaGame = _listaGame.asStateFlow()

    private val _listaUserGame: MutableStateFlow<List<Game>?> = MutableStateFlow(null)
    var listaUserGame = _listaUserGame.asStateFlow()

    private val _estadoLlamada: MutableStateFlow<estadoApi> = MutableStateFlow(estadoApi.IDLE)
    var estadoLlamada = _estadoLlamada.asStateFlow()

    init {
        obtenerUser()
    }

    fun obtenerUser() {
        _estadoLlamada.value = estadoApi.LOADING
        viewModelScope.launch {
            var respuesta = retrofitService.getAllUsers()

            if (respuesta.isSuccessful) {
                _listaUser.value = respuesta.body()
                _estadoLlamada.value = estadoApi.SUCCESS
                println(respuesta.body().toString())
            } else {
                println("NO SE HAN PODIDO CARGAR LOS DATOS" + respuesta.errorBody())
            }
        }
    }

    fun obtenerUserGame(id: Int) {
        _estadoLlamada.value = estadoApi.LOADING
        viewModelScope.launch {
            var respuesta = retrofitService.getGamesByUser(id)
            if (respuesta.isSuccessful) {
                _listaUserGame.value = respuesta.body()
                _estadoLlamada.value = estadoApi.SUCCESS
                println(respuesta.body().toString())
            } else {
                println("NO SE HAN PODIDO CARGAR LOS DATOS" + respuesta.errorBody())
            }
        }
    }

    fun linkUserGame(idUser: Int, idGame: Int) {
        _estadoLlamada.value = estadoApi.LOADING
        viewModelScope.launch {
            try {
                var respuesta = retrofitService.linkGame(idUser, idGame)
                if (respuesta.isSuccessful) {
                    obtenerUserGame(idUser)
                    println("SE HA ENLAZADO EL JUEGO CON EL USUARIO")
                    _estadoLlamada.value = estadoApi.SUCCESS
                } else {
                    println("NO SE HA ENLAZADO EL JUEGO CON EL USUARIO" + respuesta.errorBody())
                    _estadoLlamada.value = estadoApi.ERROR
                }
            } catch (e: Exception) {
                println("HA OCURRIDO UN ERROR: ${e.message}")
                _estadoLlamada.value = estadoApi.ERROR
            }
        }
    }

    fun unlinkUserGame(idUser: Int, idGame: Int) {
        _estadoLlamada.value = estadoApi.LOADING
        viewModelScope.launch {
            try {
                var respuesta = retrofitService.unlinkGame(idUser, idGame)
                if (respuesta.isSuccessful) {
                    obtenerUserGame(idUser)
                    println("SE HA DESENLAZADO EL JUEGO CON EL USUARIO")
                    _estadoLlamada.value = estadoApi.SUCCESS
                } else {
                    println("NO SE HA DESENLAZADO EL JUEGO CON EL USUARIO" + respuesta.errorBody())
                    _estadoLlamada.value = estadoApi.ERROR
                }
            } catch (e: Exception) {
                println("HA OCURRIDO UN ERROR: ${e.message}")
                _estadoLlamada.value = estadoApi.ERROR
            }
        }
    }

        fun obtenerGame() {
            _estadoLlamada.value = estadoApi.LOADING
            viewModelScope.launch {
                var respuesta = retrofitService.getAllGames()
                if (respuesta.isSuccessful) {
                    _listaGame.value = respuesta.body()
                    _estadoLlamada.value = estadoApi.SUCCESS
                    println(respuesta.body().toString())
                } else {
                    println("NO SE HAN PODIDO CARGAR LOS DATOS" + respuesta.errorBody())
                }
            }
        }

        fun postGame(game: Game) {
            _estadoLlamada.value = estadoApi.LOADING
            viewModelScope.launch {
                try {
                    val response = retrofitService.createGame(game)

                    if (response.isSuccessful) {
                        obtenerGame()
                        println("SE HA CREADO EL JUEGO")
                        _estadoLlamada.value = estadoApi.SUCCESS
                    } else {
                        println("NO SE HA PODIDO CREAR EL JUEGO: ${response.errorBody()}")
                        _estadoLlamada.value = estadoApi.ERROR
                    }
                } catch (e: Exception) {
                    println("HA OCURRIDO UN ERROR: ${e.message}")
                    _estadoLlamada.value = estadoApi.ERROR
                }
            }
        }

        fun postUser(user: User) {
            _estadoLlamada.value = estadoApi.LOADING
            viewModelScope.launch {
                try {
                    val response = retrofitService.createUser(user)

                    if (response.isSuccessful) {
                        obtenerUser()
                        println("SE HA CREADO EL HUESPED")
                        _estadoLlamada.value = estadoApi.SUCCESS
                    } else {
                        println("NO SE HA PODIDO CREAR EL HUESPED: ${response.errorBody()}")
                        _estadoLlamada.value = estadoApi.ERROR
                    }

                } catch (e: Exception) {
                    println("HA OCURRIDO UN ERROR: ${e.message}")
                    _estadoLlamada.value = estadoApi.ERROR
                }
            }
        }
    fun putGame(id: Int, game: Game) {
        _estadoLlamada.value = estadoApi.LOADING
        viewModelScope.launch {
            try {
                val reponse = retrofitService.updateGame(id, game)
                if (reponse.isSuccessful) {
                    obtenerGame()
                    println("SE HA MODIFICADO EL JUEGO")
                    _estadoLlamada.value = estadoApi.SUCCESS
                } else {
                    println("NO SE HA PODIDO MODIFICAR EL JUEGO: ${reponse.errorBody()}")
                    _estadoLlamada.value = estadoApi.ERROR
                }
            } catch (e: Exception) {
                println("HA OCURRIDO UN ERROR: ${e.message}")
                _estadoLlamada.value = estadoApi.ERROR
            }
        }
    }
    }

