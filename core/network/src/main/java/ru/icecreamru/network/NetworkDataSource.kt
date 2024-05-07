package ru.icecreamru.network

import com.icecreamok.kode.data.model.users.UsersResponse
import retrofit2.Response

interface NetworkDataSource {
    suspend fun getUsers(preferHeader: String): Response<UsersResponse>
}