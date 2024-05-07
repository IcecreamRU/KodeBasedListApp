package ru.icecreamru.network.retrofit

import com.icecreamok.kode.data.model.users.UsersResponse
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface RetrofitNetworkApi {
    @GET("/users")
    suspend fun getUsers(@Header("Prefer") preferHeader: String): Response<UsersResponse>
}

@Serializable
private data class NetworkResponse<T>(
    val data: T,
)


class RetrofitKodeNetwork {

}