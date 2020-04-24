package com.michaeludjiawan.switchlayout.layout

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.michaeludjiawan.switchlayout.R
import kotlinx.android.synthetic.main.fragment_default_dialog.*

class DefaultDialogFragment(private val loaderView: View) : DialogFragment() {

    private val initialParent = loaderView.parent as ViewGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_default_dialog, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        isCancelable = false

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialParent.removeView(loaderView)
        fl_dialog_over_frame.addView(loaderView)
        loaderView.visibility = View.VISIBLE
    }

    override fun dismiss() {
        super.dismiss()
        fl_dialog_over_frame.removeView(loaderView)
        initialParent.addView(loaderView)
        loaderView.visibility = View.GONE
    }
}