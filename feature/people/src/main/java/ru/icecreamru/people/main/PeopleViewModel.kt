package ru.icecreamru.people.main

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.icecreamru.data.repository.NetworkUsersRepository
import ru.icecreamru.people.main.model.Page
import ru.icecreamru.people.main.model.Pages
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val networkUsersRepository: NetworkUsersRepository
) : ViewModel() {
    private val peopleData = getMockPeople()
    val sortedPeopleData = peopleData.toMutableStateList()

    fun getMockTabs(): Pages {
        val result = ArrayList<Page>()
        val pages = Pages(result)
        result.add(Page(title = "Все", persons = Person.mockList(10)))
        result.add(Page(title = "Designers", persons = Person.mockList(5)))
        result.add(Page(title = "Android", persons = Person.mockList(1)))
        result.add(Page(title = "iOS", persons = Person.mockList(0)))
        return pages
    }
}

private fun getMockPeople() = List(30) { i -> Person.mock() }


