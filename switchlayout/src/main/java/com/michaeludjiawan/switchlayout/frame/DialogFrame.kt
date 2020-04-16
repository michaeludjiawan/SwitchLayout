package com.michaeludjiawan.switchlayout.frame

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.michaeludjiawan.switchlayout.layout.DefaultDialogFragment

class DialogFrame(context: Context) : Frame {

    private val fragmentManager: FragmentManager = (context as AppCompatActivity).supportFragmentManager
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