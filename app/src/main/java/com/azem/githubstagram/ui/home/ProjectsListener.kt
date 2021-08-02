package com.azem.githubstagram.ui.home

import com.azem.githubstagram.data.model.Owner
import com.azem.githubstagram.data.model.Repository

interface ProjectsListener {
    fun onSearchRepositoryClick(repository: Repository)
    fun onSearchOwnerClick(owner: Owner)
}