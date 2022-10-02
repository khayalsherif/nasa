package az.khayalsharifli.presentation.flow.content.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import az.khayalsharifli.presentation.base.BaseFragment
import az.khayalsharifli.presentation.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override val bindViews: FragmentSplashBinding.() -> Unit = {

    }

}