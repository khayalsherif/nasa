package az.khayalsharifli.presentation.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import az.khayalsharifli.presentation.R
import az.khayalsharifli.presentation.databinding.FragmentBottomSheetDialogBaseBinding
import az.khayalsharifli.presentation.delegation.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BaseBottomSheetDialog : BottomSheetDialogFragment() {

    val binding by viewBinding(FragmentBottomSheetDialogBaseBinding::bind)

    private var image: Int = R.drawable.ic_info
    private var title: Int = R.string.unknown_error_title
    private var text: Int = R.string.unknown_error_text

    private var action: (BaseBottomSheetDialog) -> Unit = {}
    private var canCancel: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_bottom_sheet_dialog_base, container, false)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)

        //This line transparent your dialog background
        dialog.setOnShowListener {
            (view?.parent as ViewGroup).background = ColorDrawable(Color.TRANSPARENT)
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView.setImageResource(image)
        binding.title.setText(title)
        binding.text.setText(text)

        binding.button.setOnClickListener {
            action(this)
        }
        isCancelable = canCancel
    }

    companion object {
        fun build(
            image: Int,
            title: Int,
            text: Int,
            action: (BaseBottomSheetDialog) -> Unit = {},
            cancelable: Boolean = true
        ): BaseBottomSheetDialog {
            return BaseBottomSheetDialog().apply {
                this.text = text
                this.title = title
                this.image = image
                this.action = action
                this.canCancel = cancelable
            }
        }
    }

}
