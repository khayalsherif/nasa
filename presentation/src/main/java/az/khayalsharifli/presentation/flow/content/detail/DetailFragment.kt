package az.khayalsharifli.presentation.flow.content.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import az.khayalsharifli.presentation.R
import az.khayalsharifli.presentation.base.BaseFragment
import az.khayalsharifli.presentation.databinding.FragmentDetailBinding
import coil.ImageLoader
import coil.imageLoader
import coil.load

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    private val args: DetailFragmentArgs by navArgs()

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override val bindViews: FragmentDetailBinding.() -> Unit = {
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        setImage(args.imageUrl!!, args.date!!, imageView = ivNasa)
    }

    private fun setImage(imageUrl: String, date: String, imageView: ImageView) {
        val fullUrl =
            "https://api.nasa.gov/EPIC/archive/natural/${date}/png/${imageUrl}.png?api_key=wtDl5n1USYfB06iMdKnbcJWTn93dOL129LkFaWiS"

        imageView.load(fullUrl) {
            placeholder(R.drawable.ic_nasa)
        }

    }

}