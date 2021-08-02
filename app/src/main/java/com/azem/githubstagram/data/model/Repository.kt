package com.azem.githubstagram.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(val id:Int, val name:String, val owner: Owner, val description:String, val html_url: String,
                      val language:String, val created_at:String, val stargazers_count: Int, val open_issues_count:Int,
                      val updated_at: String? = null, val watchers: Long? = null,
                      val forks: Long? = null,
                      val open_issues: Long? = null,
) : Parcelable
