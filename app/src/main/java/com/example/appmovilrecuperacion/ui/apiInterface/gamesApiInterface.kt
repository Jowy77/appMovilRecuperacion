package com.example.appmovilrecuperacion.ui.apiInterface

import com.example.appmovilrecuperacion.ui.model.Game
import com.example.appmovilrecuperacion.ui.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

private const val BASE_URL =
    "http://192.168.1.33:8080/api/v1/"

interface gamesApiInterface {
    @GET("users")
    @Headers("Accept: application/json")
    suspend fun getAllUsers(): Response<List<User>>

    @POST("users/create")
    suspend fun createUser(@Body user: User) : Response<Void>

    @GET("games")
    suspend fun getAllGames(): Response<List<Game>>

    @GET("users/{id}/games")
    suspend fun getGamesByUser(@Path("id") id: Int): Response<List<Game>>

    @POST("users/{idUser}/games/{idGame}")
    suspend fun linkGame(@Path("idUser") idUser: Int, @Path("idGame") idGame: Int): Response<Void>

    @DELETE("users/{idUser}/games/{idGame}")
    suspend fun unlinkGame(@Path("idUser") idUser: Int, @Path("idGame") idGame: Int): Response<Void>

    @POST("games/create")
    suspend fun createGame(@Body game: Game): Response<Void>

    @PUT("games/put/{id}")
    suspend fun updateGame(@Path("id") id: Int, @Body game: Game): Response<Void>
}

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object gamesApi{
    val retrofitService : gamesApiInterface by lazy {
        retrofit.create(gamesApiInterface::class.java)
    }
}