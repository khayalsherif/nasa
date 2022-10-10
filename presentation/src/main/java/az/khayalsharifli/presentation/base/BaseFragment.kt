package az.khayalsharifli.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Binding : ViewBinding, ViewModel : BaseViewModel> : Fragment() {

    protected abstract val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> Binding

    private lateinit var binding: Binding

    //protected abstract val vmClazz: KClass<ViewModel>
    //val viewModel: ViewModel by lazy { getViewModel(vmClazz) { parametersOf(arguments) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingCallBack.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews.invoke(binding)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    protected open val bindViews: Binding.() -> Unit = {}

}