package az.khayalsharifli.presentation.flow.content.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.presentation.base.BaseFragment
import az.khayalsharifli.presentation.databinding.FragmentHomeBinding
import az.khayalsharifli.presentation.flow.adapters.HomeAdapter
import az.khayalsharifli.presentation.tools.ClickListener
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), ClickListener {

    private val homeAdapter by lazy { HomeAdapter(this) }

    private lateinit var epicList: List<Epic>

    override val kClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override val bindViews: FragmentHomeBinding.() -> Unit = {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            requireActivity().finish()
        }

        rcHome.adapter = homeAdapter
        rcHome.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            viewModel.epicResponse.collect {
                epicList = it
                homeAdapter.setData(it)
            }
        }
    }

    override fun onClick(view: View, position: Int) {
        val item = epicList[position]
        val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
            item.image,
            getFormattedDate(item.date)
        )
        viewModel.navigate(direction)
    }


    private fun getFormattedDate(fullDate: String): String {
        val date = fullDate.take(10)
        val year = date.take(4)
        val month = date.substring(5, 7)
        val day = date.takeLast(2)
        return "$year/$month/$day"
    }

}