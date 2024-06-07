package ru.icecreamru.data.model

import com.icecreamok.kode.data.model.users.NetworkUserDto

data class UserDto(
    val avatarUrl: String,
    val birthday: String,
    val department: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String,
    val position: String,
    val userTag: String
)

fun NetworkUserDto.asEntity() = UserDto(
    avatarUrl = avatarUrl,
    birthday = birthday,
    department = department,
    firstName = firstName,
    id = id,
    lastName = lastName,
    phone = phone,
    position = position,
    userTag = userTag
)

fun List<NetworkUserDto>.asEntity(): ArrayList<UserDto> {
    val result = arrayListOf<UserDto>()
    this.forEach {
        result.add(it.asEntity())
    }
    return result
}
