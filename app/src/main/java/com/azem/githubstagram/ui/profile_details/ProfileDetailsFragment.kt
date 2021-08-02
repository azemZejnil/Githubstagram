package com.azem.githubstagram.ui.profile_details

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.azem.githubstagram.R
import com.azem.githubstagram.api.GitApi
import com.azem.githubstagram.databinding.FragmentProfileDetailsBinding
import com.azem.githubstagram.webview.WebViewActivity
import com.squareup.picasso.Picasso

class ProfileDetailsFragment : Fragment(R.layout.fragment_profile_details) {

    private val args by navArgs<ProfileDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileDetailsBinding.bind(view)

        binding.apply {
            owner = args.owner

            cardViewLink.setOnClickListener {
                val intent = Intent(activity, WebViewActivity::class.java)
                intent.putExtra(GitApi.EXTERNAL_NAME, args.owner.login)
                intent.putExtra(GitApi.EXTERNAL_URL, args.owner.html_url)
                startActivity(intent)
            }
        }
    }

}