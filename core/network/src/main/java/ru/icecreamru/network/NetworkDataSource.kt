package ru.icecreamru.network

import ru.icecreamru.network.model.NetworkUsersResponse

interface NetworkDataSource {
    suspend fun getUsers(preferHeader: String): NetworkUsersResponse?
}