package com.michaeludjiawan.switchlayout.frame

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.michaeludjiawan.switchlayout.layout.DefaultDialogFragment
import com.michaeludjiawan.switchlayout.switcher.Switcher

class DialogFrame(parent: Switcher) : Frame {

    private val fragmentManager: FragmentManager = (parent.getContext() as AppCompatActivity).supportFragmentManager
    private var dialog: DialogFragment? = null

    override fun load(itemView: ViewGroup) {
        if (dialog == null) {
            dialog = DefaultDialogFragment(itemView)
        }

        if (!dialog!!.isAdded) {
            dialog!!.show(fragmentManager, "")
        }
    }

    override fun unload(itemView: ViewGroup) {
        dialog?.dismiss()
    }

}