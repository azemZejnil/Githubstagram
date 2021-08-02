package com.azem.githubstagram.ui.home

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.azem.githubstagram.data.GitDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: GitDataRepository) : ViewModel() {

    val currentQuery = MutableLiveData(DEFAULT_QUERY)
    var currentSort = MutableLiveData(DEFAULT_SORT)

    val projects = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString, currentSort.value!!).cachedIn(viewModelScope)
    }

    fun searchRepos(query: String, sort: String) {
        currentQuery.value = query
        currentSort.value = sort
    }

    companion object {
        private const val DEFAULT_QUERY = "android"
        private const val DEFAULT_SORT = ""
    }

}