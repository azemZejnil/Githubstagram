package com.azem.githubstagram.ui.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.azem.githubstagram.R
import com.azem.githubstagram.data.model.Owner
import com.azem.githubstagram.data.model.Repository
import com.azem.githubstagram.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener, ProjectsListener {

    private lateinit var dialog: BottomSheetDialog
    private lateinit var bottomSheetView: View

    private val viewModel by viewModels<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        val adapter = ProjectsAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            imageViewSearch.setOnClickListener {
                searchForKeyword(editTextSearch.text.toString())
                editTextSearch.setText("")
            }
            imageViewFilters.setOnClickListener {
                dialog.show()
            }
            editTextSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchForKeyword(editTextSearch.text.toString())
                    editTextSearch.setText("")
                    return@OnEditorActionListener true
                }
                false
            })
        }

        viewModel.projects.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        initBottomSheet()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initBottomSheet() {
        bottomSheetView = layoutInflater.inflate(R.layout.layout_bottom_sheet_filter, null)
        bottomSheetView.findViewById<TextView>(R.id.textViewStars).setOnClickListener(this)
        bottomSheetView.findViewById<TextView>(R.id.textViewForks).setOnClickListener(this)
        bottomSheetView.findViewById<TextView>(R.id.textViewUpdated).setOnClickListener(this)
        bottomSheetView.findViewById<TextView>(R.id.textViewClose).setOnClickListener(this)

        dialog = BottomSheetDialog(requireActivity())
        dialog.setContentView(bottomSheetView)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.textViewStars -> {
                viewModel.currentSort.value = "stars"
            }
            R.id.textViewForks -> {
                viewModel.currentSort.value = "forks"
            }
            R.id.textViewUpdated -> {
                viewModel.currentSort.value = "updated"
            }
            R.id.textViewClose -> {
                dialog.dismiss()
            }
        }
        viewModel.searchRepos(viewModel.currentQuery.value!!,viewModel.currentQuery.value!!)
        dialog.dismiss()
    }

    override fun onSearchRepositoryClick(repository: Repository) {
        val action =  HomeFragmentDirections.actionHomeFragmentToProjectDetailsFragment(repository)
        findNavController().navigate(action)
    }

    override fun onSearchOwnerClick(owner: Owner) {
        val action =  HomeFragmentDirections.actionHomeFragmentToProfileDetailsFragment(owner)
        findNavController().navigate(action)
    }

    private fun searchForKeyword(keyword: String){
        if (keyword.isNotEmpty()) {
            binding.recyclerView.scrollToPosition(0)
            viewModel.searchRepos(keyword,viewModel.currentSort.value!!)
        }
    }

}
