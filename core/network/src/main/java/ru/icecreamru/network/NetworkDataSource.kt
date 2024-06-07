package ru.icecreamru.network

import com.icecreamok.kode.data.model.users.NetworkUsersResponse
import retrofit2.Response

interface NetworkDataSource {
    suspend fun getUsers(preferHeader: String): NetworkUsersResponse
}