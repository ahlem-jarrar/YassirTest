package com.yassir.moviesappyassir.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.yassir.moviesappyassir.R
import com.yassir.moviesappyassir.databinding.FragmentMainBinding
import com.yassir.moviesappyassir.ui.adapter.MoviesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MoviesListViewModel>()

    private val moviesAdapter by lazy {
        MoviesListAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        initSwipeToRefresh()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moviesList.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        initData()
    }

    private fun initData() {
        lifecycleScope.launchWhenResumed {
            viewModel.getTrendingMovies().collectLatest { list ->
                moviesAdapter.submitData(list)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.selectedMovieId.collectLatest { movieId ->
                onMovieClicked(movieId)
            }
        }

        lifecycleScope.launchWhenResumed {
            moviesAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.refresh.isRefreshing = loadStates.refresh is LoadState.Loading

            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.isOffline.collectLatest {
                if (it) showOfflineMessage()
            }
        }
    }

    private fun showOfflineMessage() {
        Toast.makeText(context, R.string.offline_app, Toast.LENGTH_SHORT).show()
    }

    private fun onMovieClicked(movieId: Int) {
        val action =
            MoviesListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment((movieId))
        view?.findNavController()?.navigate(action)
    }

    private fun initSwipeToRefresh() {
        binding.refresh.setOnRefreshListener { moviesAdapter.refresh() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}