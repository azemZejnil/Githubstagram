package com.azem.githubstagram.api

import retrofit2.http.*

interface GitApi {

    companion object {
        const val BASE_URL: String = "https://api.github.com/"
        const val GET_REPOS: String = "search/repositories"
        const val EXTERNAL_URL: String = "EXTERNAL_URL"
        const val EXTERNAL_NAME: String = "EXTERNAL_NAME"
    }

    @GET(GET_REPOS)
    suspend fun searchGithubProjects(
        @Query("q") q: String?,
        @Query("sort") sort: String?,
        @Query("page") page:Int?,
        @Query("per_page") per_page:Int?
    ): GitResponse

}