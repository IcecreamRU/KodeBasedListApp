package ru.icecreamru.people.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.icecreamru.designsystem.theme.KodeTheme
import ru.icecreamru.people.R
import java.util.UUID


@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(painterResource(id = R.drawable.ic_search), contentDescription = null)
        },
        placeholder = {
            Text(text = "Введи имя, тег, почту...")
        },
        trailingIcon = {
            Icon(painterResource(id = R.drawable.ic_filter), contentDescription = null)
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(40.dp)
    )
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SearchBarPreview() {
    KodeTheme {
        SearchBar(modifier = Modifier.padding(8.dp))
    }
}

data class Person(
    val avatarUrl: String,
    val birthday: String,
    val department: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String,
    val position: String,
    val userTag: String
) {
    fun getFullName() = "$firstName $lastName"

    companion object {
        fun mockList(count: Int): ArrayList<Person> {
            val result = ArrayList<Person>()
            for (i in 0 until count) {
                result.add(mock())
            }
            return result
        }

        fun mock(): Person {
            return Person(
                avatarUrl = "https://t4.ftcdn.net/jpg/00/97/58/97/360_F_97589769_t45CqXyzjz0KXwoBZT9PRaWGHRk5hQqQ.jpg",
                birthday = "03.06.1995",
                department = "Android",
                firstName = "Денис",
                id = UUID.randomUUID().toString(),
                lastName = "Затеев",
                phone = "555-4545",
                position = "Developer",
                userTag = "dev",
            )

        }

    }
}
