package ru.icecreamru.people.main

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.icecreamru.designsystem.theme.KodeTheme

@Composable
fun PeopleScreen(
    peopleViewModel: PeopleViewModel = viewModel()
) {
    KodeTheme {
        Scaffold(
            topBar = { SearchBar(Modifier.padding(8.dp)) }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                val context = LocalContext.current
                val data = remember { peopleViewModel.getMockTabs() }
                val titles = ArrayList<String>()

                data.pages.forEach {
                    titles.add(it.title)
                }
                TabBar(pages = data, onPageClick = { page ->
                    data.selected.value = data.pages.indexOf(page)
                })
                UsersContent(data.pages.toList()[data.selected.value].persons, onUserClick = {
                    Toast.makeText(
                        context,
                        it.getFullName(),
                        Toast.LENGTH_SHORT
                    ).show()
                })
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun PeopleScreenContentPreview() {
    KodeTheme {
        PeopleScreen()
    }
}
