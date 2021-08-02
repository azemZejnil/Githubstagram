package com.azem.githubstagram.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.azem.githubstagram.api.GitApi
import com.azem.githubstagram.data.model.Repository
import java.lang.Exception


private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class GitPagingSource(private val gitApi: GitApi, private val query: String, private val sort: String) : PagingSource<Int, Repository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = gitApi.searchGithubProjects(query,sort, position, params.loadSize)
            val projects = response.items

            LoadResult.Page(
                data = projects,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (projects.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        TODO("Not yet implemented")
    }
}


