package com.yassir.moviesappyassir.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.yassir.moviesappyassir.databinding.FragmentMainBinding
import com.yassir.moviesappyassir.ui.adapter.MovieLoadingAdapter
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

    private val header by lazy {
        MovieLoadingAdapter { moviesAdapter.retry() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moviesList.apply {
            adapter = moviesAdapter
            adapter = moviesAdapter.withLoadStateHeader(
                header = header
            )
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

                header.loadState = when (loadStates.refresh) {
                    is LoadState.NotLoading -> loadStates.append
                    else -> loadStates.refresh
                }
            }
        }
    }

    private fun onMovieClicked(movieId: Int) {
        val action =
            MoviesListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment((movieId))
        view?.findNavController()?.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}