package az.khayalsharifli.presentation.flow.content.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import az.khayalsharifli.presentation.base.BaseFragment
import az.khayalsharifli.presentation.databinding.FragmentHomeBinding
import az.khayalsharifli.presentation.flow.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val viewModel by viewModel<HomeViewModel>()

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override val bindViews: FragmentHomeBinding.() -> Unit = {
        lifecycleScope.launch {
            viewModel.epicResponse.collect {
                println(it)
            }
        }
    }
}