package com.azem.githubstagram.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.azem.githubstagram.api.GitApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitDataRepository @Inject constructor(private val gitApi: GitApi) {
    fun getSearchResults(query: String, sort: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GitPagingSource(gitApi, query, sort) }
        ).liveData
}