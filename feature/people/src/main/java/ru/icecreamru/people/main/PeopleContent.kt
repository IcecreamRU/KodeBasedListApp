package ru.icecreamru.people.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import ru.icecreamru.designsystem.theme.KodeTheme
import ru.icecreamru.people.R

@Composable
fun UserElement(
    person: Person,
    onUserClick: (Person) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onUserClick(person) }),
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
fun UserRow(
    userList: List<Person>,
    modifier: Modifier = Modifier,
    onUserClick: (Person) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(22.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
    ) {
        items(userList) {
            UserElement(it, onUserClick = onUserClick)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersContent(
    userList: List<Person>,
    onUserClick: (Person) -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    PullToRefreshBox(
        isRefreshing = isRefreshing, onRefresh = {
            isRefreshing = true
            scope.launch {
                delay(1500)
                isRefreshing = false
            }
        },
        modifier = modifier
    ) {
        UserRow(userList, modifier, onUserClick)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun UserElementPreview() {
    KodeTheme {
        UserElement(Person.mock(), onUserClick = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun UserRowPreview() {
    KodeTheme {
        UserRow(Person.mockList(15), onUserClick = {})
    }
}