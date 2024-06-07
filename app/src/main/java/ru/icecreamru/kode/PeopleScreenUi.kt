package ru.icecreamru.kode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
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
import ru.icecreamru.kode.ui.theme.KodeTheme
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
            placeholder = painterResource(R.drawable.ic_launcher_background), //TODO: подставить ic_chicken
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
fun UserScreen(modifier: Modifier = Modifier) {
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
fun PeopleScreenTest(modifier: Modifier = Modifier) {

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBar(modifier: Modifier = Modifier) {
    var state by remember {
        mutableIntStateOf(0)
    }
    val titles = listOf("Все", "Designers", "Analysts", "Managers", "iOS", "Android")
    ScrollableTabRow(
        selectedTabIndex = state, modifier = modifier
    ) {
        titles.forEachIndexed { index, title ->
            Tab(
                selected = state == index,
                onClick = { state = index },
                text = { Text(text = title, maxLines = 1) }
            )
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {

}

@Preview
@Composable
fun testPreview() {

}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun TabBarPreview() {
    KodeTheme {
        TabBar()
    }
}
//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun SearchBarPreview() {
//    KodeTheme {
//        SearchBar()
//    }
//}
//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun UserElementPreview() {
//    KodeTheme {
//        UserElement(Person.mock())
//    }
//}
//
//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun UserRowPreview() {
//    KodeTheme {
//        UserRow()
//    }
//}

//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun UserScreenPreview() {
//    KodeTheme {
//        UserScreen()
//    }
//}
//
//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun PeopleScreenContentPreview() {
//    KodeTheme {
//        PeopleScreen()
//    }
//}


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
