package com.azem.githubstagram.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Owner(val login:String, val avatar_url:String, val html_url: String,
                 val type: String) : Parcelable