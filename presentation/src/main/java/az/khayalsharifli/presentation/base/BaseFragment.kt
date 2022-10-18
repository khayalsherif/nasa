package az.khayalsharifli.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import az.khayalsharifli.presentation.R
import az.khayalsharifli.presentation.tools.NavigationCommand
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

abstract class BaseFragment<Binding : ViewBinding, ViewModel : BaseViewModel> : Fragment() {

    protected abstract val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> Binding

    private lateinit var binding: Binding

    protected abstract val kClass: KClass<ViewModel>
    val viewModel: ViewModel by lazy { getViewModel(kClass) { parametersOf(arguments) } }

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

        viewModel.commonEffect.observe(viewLifecycleOwner) {
            when (it) {
                is NoInternet -> showNoInternet()
                is BackEndError -> showUnKnow()
                is UnknownError -> showUnKnow()
                is MessageError -> showUnKnow()
                else -> showUnKnow()
            }
        }

        viewModel.navigationCommands.observe(viewLifecycleOwner) { command ->
            when (command) {
                is NavigationCommand.To -> {
                    command.extras?.let { extras ->
                        findNavController().navigate(
                            command.direction,
                            extras
                        )
                    } ?: run {
                        findNavController().navigate(
                            command.direction
                        )
                    }
                }
                is NavigationCommand.BackTo -> findNavController().getBackStackEntry(command.destinationId)
                is NavigationCommand.Back -> findNavController().popBackStack()
            }
        }
    }

    protected open val bindViews: Binding.() -> Unit = {}

    private fun <T : DialogFragment> T.show(tag: String? = null): T {
        this.show(this@BaseFragment.parentFragmentManager, tag)
        return this
    }

    protected open fun showNoInternet() {
        if (noInternetDialog?.isAdded == false)
            noInternetDialog?.show("internet-error-dialog")
    }

    protected open fun showUnKnow() {
        if (unknownDialog?.isAdded == false)
            unknownDialog?.show("un-know-error-dialog")
    }

    protected open val noInternetDialog: DialogFragment? by lazy {
        BaseBottomSheetDialog.build(
            title = R.string.no_internet_title,
            text = R.string.no_internet_error_text,
            image = R.drawable.ic_no_connection,
            action = { it.dismiss() }
        )
    }

    protected open val unknownDialog: DialogFragment? by lazy {
        BaseBottomSheetDialog.build(
            title = R.string.unknown_error_title,
            text = R.string.unknown_error_text,
            image = R.drawable.ic_info,
            action = { it.dismiss() }
        )
    }

}