package ru.icecreamru.people

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.icecreamru.designsystem.theme.KodeTheme

@Composable
fun ErrorContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Icon(painter = painterResource(id = R.drawable.ic_ufo), contentDescription = null)
        Text(text = "Какой-то сверхразум все сломал")
    }
}

@Preview
@Composable
fun ErrorContentPreview() {
    KodeTheme {
        ErrorContent()
    }
}