package ru.icecreamru.data.repository

import kotlinx.coroutines.flow.Flow
import ru.icecreamru.data.model.UserDto


private const val headerSuccess = "code=200, example=success"
private const val headerGeneric = "code=200, dynamic=true"
private const val headerHttp500 = "code=500, example=error-500"

enum class ExpectedResponse(val header: String) {
    SUCCESS("code=200, example=success"),
    GENERIC("code=200, dynamic=true"),
    HTTP_500("code=500, example=error-500")
}

interface UsersRepository {
    suspend fun getUsers(expectedResponse: ExpectedResponse): Flow<List<UserDto>>
}