package az.khayalsharifli.presentation.flow.content.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import az.khayalsharifli.presentation.base.BaseFragment
import az.khayalsharifli.presentation.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override val bindViews: FragmentDetailBinding.() -> Unit = {

    }
}