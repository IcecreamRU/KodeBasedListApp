package ru.icecreamru.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.icecreamru.data.model.UserDto
import ru.icecreamru.data.model.asEntity
import ru.icecreamru.network.NetworkDataSource

class NetworkUsersRepository(
    private val networkDataSource: NetworkDataSource
) : UsersRepository {
    override suspend fun getUsers(expectedResponse: ExpectedResponse): Flow<List<UserDto>> =
        flow {
            emit(
                networkDataSource.getUsers(expectedResponse.header)
                    ?.items
                    ?.map { it.asEntity() } ?: emptyList()
            )
        }
}