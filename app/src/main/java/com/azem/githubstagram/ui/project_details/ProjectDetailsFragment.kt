package com.azem.githubstagram.ui.project_details

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.azem.githubstagram.R
import com.azem.githubstagram.api.GitApi
import com.azem.githubstagram.databinding.FragmentProjectDetailsBinding
import com.azem.githubstagram.webview.WebViewActivity
import com.squareup.picasso.Picasso

class ProjectDetailsFragment : Fragment(R.layout.fragment_project_details) {

    private val args by navArgs<ProjectDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentProjectDetailsBinding.bind(view)

        binding.apply {
            repo = args.project
            cardViewLink.setOnClickListener {
                val intent = Intent(activity, WebViewActivity::class.java)
                intent.putExtra(GitApi.EXTERNAL_NAME, args.project.name)
                intent.putExtra(GitApi.EXTERNAL_URL, args.project.html_url)
                startActivity(intent)
            }

            imageViewProfile.setOnClickListener {
                val action =  ProjectDetailsFragmentDirections.actionProjectDetailsFragmentToProfileDetailsFragment(args.project.owner)
                findNavController().navigate(action)
            }
        }
    }
}