package ru.icecreamru.data.repository

import ru.icecreamru.data.model.UserDto
import ru.icecreamru.data.model.asEntity
import ru.icecreamru.network.NetworkDataSource

class NetworkUsersRepository(
    private val networkDataSource: NetworkDataSource
) : UsersRepository {
    override suspend fun getUsers(expectedResponse: ExpectedResponse): List<UserDto> {
        return networkDataSource.getUsers(expectedResponse.header).items.asEntity()
    }
}