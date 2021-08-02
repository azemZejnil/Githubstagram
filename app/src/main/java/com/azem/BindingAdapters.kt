package com.azem

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.azem.githubstagram.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setAvatar")
fun ImageView.setAvatar(imgUrl: String?) {
    if (imgUrl != null)
    Picasso.get().load(imgUrl)
        .placeholder(R.drawable.ic_image_place_holder)
        .error(R.drawable.ic_image_place_holder)
        .into(this)
}


@BindingAdapter("setFormattedTime")
fun TextView.setFormattedTime(time: String?) {
    if (time != null)
        text = parseServerTime(time)
}

fun parseServerTime(time: String): String {
    val timezone = TimeZone.getTimeZone("UTC")
    val serverFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    serverFormat.timeZone = timezone
    val serverDate = serverFormat.parse(time)
    val clientFormat = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.getDefault())
    return clientFormat.format(serverDate!!)
}