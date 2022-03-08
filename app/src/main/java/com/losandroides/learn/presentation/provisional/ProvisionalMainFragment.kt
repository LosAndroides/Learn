package com.losandroides.learn.presentation.provisional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.losandroides.learn.databinding.FragmentProvisionalMainBinding
import com.losandroides.learn.domain.model.Item
import com.losandroides.learn.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Deprecated("This has to be replaced by Jetpack Compose stuff")
@AndroidEntryPoint
class ProvisionalMainFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: FragmentProvisionalMainBinding
    private lateinit var provisionalItemAdapter: ProvisionalItemAdapter

    companion object {
        fun newInstance() = ProvisionalMainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProvisionalMainBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        initFlow()
    }

    private fun initFlow() {
        lifecycleScope.launch {
            mainViewModel.viewState.collect(::render)
        }
    }

    private fun render(viewState: MainViewModel.ViewState) {
        when (viewState) {
            is MainViewModel.ViewState.Content -> showContent(viewState.items)
        }
    }

    private fun showContent(items: List<Item>) {
        provisionalItemAdapter = ProvisionalItemAdapter(items)

        binding.rvItems.adapter = provisionalItemAdapter
        provisionalItemAdapter.notifyDataSetChanged()
    }
}
