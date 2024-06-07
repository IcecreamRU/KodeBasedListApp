package ru.icecreamru.network.retrofit

import androidx.core.os.trace
import com.icecreamok.kode.data.model.users.NetworkUsersResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import ru.icecreamru.network.NetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton


interface RetrofitNetworkApi {
    @GET("/users")
    suspend fun getUsers(@Header("Prefer") preferHeader: String): Response<NetworkUsersResponse>
}

// TODO: перенести в BuildConfig
// private const val KODE_BASE_URL = BuildConfig.BACKEND_URL
private const val KODE_BASE_URL = "https://stoplight.io/mocks/kode-education/trainee-test/25143926/"


//@Singleton
//internal class RetrofitKodeNetwork @Inject constructor(
//    networkJson: Json,
//    okhttpCallFactory: dagger.Lazy<Call.Factory>
//) : NetworkDataSource {
//    private val networkApi = trace("RetrofitKodeNetwork") {
//        Retrofit.Builder()
//            .baseUrl(KODE_BASE_URL)
//            .callFactory(okhttpCallFactory.get())
//            .addConverterFactory(
//                networkJson.asConverterFactory("application/json".toMediaType()),
//            )
//            .build()
//            .create(RetrofitNetworkApi::class.java)
//
//    }
//
//    override suspend fun getUsers(preferHeader: String): Response<NetworkUsersResponse> =
//        networkApi.getUsers(preferHeader)
//}