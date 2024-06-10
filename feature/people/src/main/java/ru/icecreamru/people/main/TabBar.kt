package ru.icecreamru.people.main

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.icecreamru.designsystem.theme.KodeTheme
import ru.icecreamru.people.main.model.Page
import ru.icecreamru.people.main.model.Pages

@Composable
fun TabBar(pages: Pages, onPageClick: (Page) -> Unit, modifier: Modifier = Modifier) {
    var state = pages.selected.value

    ScrollableTabRow(
        selectedTabIndex = state,
        edgePadding = 16.dp,
        modifier = modifier,
    ) {
        pages.pages.toList().forEachIndexed { index, page ->
            val style = getStyle(state, index)
            val color = getColor(state, index)
            Tab(
                selected = state == index,
                onClick = {
                    state = index
                    onPageClick(pages.pages[index])
                },
                text = { Text(text = page.title, style = style, color = color, fontSize = 15.sp) }
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

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun TabBarPreview() {
    KodeTheme {
//        TabBar()
    }
}