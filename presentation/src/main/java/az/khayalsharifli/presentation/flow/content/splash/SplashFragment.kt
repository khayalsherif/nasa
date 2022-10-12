package az.khayalsharifli.presentation.flow.content.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import az.khayalsharifli.presentation.base.BaseFragment
import az.khayalsharifli.presentation.databinding.FragmentSplashBinding
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override val kClass: KClass<SplashViewModel>
        get() = SplashViewModel::class

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