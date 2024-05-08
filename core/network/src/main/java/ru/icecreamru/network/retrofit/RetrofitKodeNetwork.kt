package ru.icecreamru.network.retrofit

import com.icecreamok.kode.data.model.users.UsersResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import javax.inject.Inject
import javax.inject.Singleton


interface RetrofitNetworkApi {
    @GET("/users")
    suspend fun getUsers(@Header("Prefer") preferHeader: String): Response<UsersResponse>
}

// TODO: перенести в BuildConfig
// private const val KODE_BASE_URL = BuildConfig.BACKEND_URL
private const val KODE_BASE_URL = "https://stoplight.io/mocks/kode-education/trainee-test/25143926/"


@Serializable
private data class NetworkResponse<T>(
    val data: T,
)

@Singleton
internal class RetrofitKodeNetwork @Inject constructor(
    networkJson: Json
) {

}