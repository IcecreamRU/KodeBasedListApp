package ru.icecreamru.people.main.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import ru.icecreamru.people.main.Person

data class Pages(
    val pages: MutableList<Page>,
    var selected: MutableState<Int> = mutableIntStateOf(0)
)

data class Page(val title: String, val persons: ArrayList<Person>)