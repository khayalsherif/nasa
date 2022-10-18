package az.khayalsharifli.presentation.flow.content.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import az.khayalsharifli.presentation.base.BaseFragment
import az.khayalsharifli.presentation.databinding.FragmentDetailBinding
import coil.imageLoader
import coil.request.ImageRequest
import kotlin.reflect.KClass

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    private val args: DetailFragmentArgs by navArgs()

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override val kClass: KClass<DetailViewModel>
        get() = DetailViewModel::class

    override val bindViews: FragmentDetailBinding.() -> Unit = {
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        val fullUrl =
            "https://api.nasa.gov/EPIC/archive/natural/${args.date!!}/png/${args.imageUrl!!}.png?api_key=wtDl5n1USYfB06iMdKnbcJWTn93dOL129LkFaWiS"

        val imageLoader = requireContext().imageLoader
        val request = ImageRequest.Builder(requireContext())
            .data(fullUrl)
            .target {
                ivNasa.setImageDrawable(it)
            }
            .listener(
                onStart = {
                    layoutLoading.progressBar.isVisible = true
                },
                onSuccess = { _, _ ->
                    layoutLoading.progressBar.isGone = true
                },
                onError = { _, _ ->
                    layoutLoading.progressBar.isGone = true
                }
            ).build()

        imageLoader.enqueue(request)

    }


}