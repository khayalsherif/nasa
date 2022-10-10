package az.khayalsharifli.presentation.flow.content.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import az.khayalsharifli.presentation.base.BaseFragment
import az.khayalsharifli.presentation.databinding.FragmentSplashBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    private val viewModel by viewModel<SplashViewModel>()

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override val bindViews: FragmentSplashBinding.() -> Unit = {
        lifecycleScope.launch {
            viewModel.splashState.collect {
                when (it) {
                    true -> {
                        val direction =
                            SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                        findNavController().navigate(direction)
                    }
                    false -> {}
                }
            }
        }
    }

}