package ru.icecreamru.people

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.icecreamru.designsystem.theme.KodeTheme
import java.util.UUID

// TODO: move to :feature:people

@Composable
fun UserElement(person: Person, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(person.avatarUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_chicken),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
        )

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Row {
                Text(
                    person.getFullName(),
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 16.sp,
                    modifier = Modifier.alignByBaseline()
                )
                Text(
                    person.userTag, style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .alignByBaseline()
                        .padding(start = 4.dp)
                )
            }
            Text(
                person.position, color = MaterialTheme.colorScheme.secondary,
                fontSize = 14.sp,
            )
        }

    }
}


@Composable
fun UserRow(modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(22.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
    ) {
        items(15) {
            UserElement(person = Person.mock())
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersContent(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    PullToRefreshBox(
        isRefreshing = isRefreshing, onRefresh = {
            isRefreshing = true
            scope.launch() {
                delay(1500)
                isRefreshing = false
            }
        },
        modifier = modifier
    ) {
        UserRow()
    }
}

@Composable
fun PeopleScreen() {
    KodeTheme {
        Scaffold(
            topBar = { SearchBar(Modifier.padding(8.dp)) }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                TabBar()
                UsersContent()
            }
        }
    }
}

@Composable
fun TabBar(modifier: Modifier = Modifier) {
    var state by remember {
        mutableIntStateOf(0)
    }
    val titles = listOf("Все", "Designers", "Analysts", "Managers", "iOS", "Android")
    ScrollableTabRow(
        selectedTabIndex = state,
        edgePadding = 16.dp,
        modifier = modifier,
    ) {
        titles.forEachIndexed { index, title ->
            val style = getStyle(state, index)
            val color = getColor(state, index)
            Tab(
                selected = state == index,
                onClick = { state = index },
                text = { Text(text = title, style = style, color = color, fontSize = 15.sp) }
            )
        }
    }
}

@Composable
private fun getStyle(state: Int, index: Int) = if (state == index) {
    MaterialTheme.typography.labelMedium
} else {
    MaterialTheme.typography.labelSmall
}

@Composable
private fun getColor(state: Int, index: Int) = if (state == index) {
    Color.Black
} else {
    MaterialTheme.colorScheme.tertiary
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
//    Row(modifier = modifier) {
//        Icon(painterResource(id = R.drawable.ic_search), contentDescription = null)
//        Icon(painterResource(id = R.drawable.ic_filter), contentDescription = null)
//    }
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
fun TabBarPreview() {
    KodeTheme {
        TabBar()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SearchBarPreview() {
    KodeTheme {
        SearchBar(modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun UserElementPreview() {
    KodeTheme {
        UserElement(Person.mock())
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun UserRowPreview() {
    KodeTheme {
        UserRow()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun UserScreenPreview() {
    KodeTheme {
        UsersContent()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun PeopleScreenContentPreview() {
    KodeTheme {
        PeopleScreen()
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
