package com.michaeludjiawan.switchlayout.frame

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.michaeludjiawan.switchlayout.layout.DefaultDialogFragment
import com.michaeludjiawan.switchlayout.switcher.Switcher

class DialogFrame(
    private val parent: Switcher,
    private val frameType: FrameType
) : Frame {

    private val fragmentManager: FragmentManager = (parent.getContext() as AppCompatActivity).supportFragmentManager
    private var dialog: DialogFragment? = null

    override fun load(itemView: ViewGroup) {
        if (dialog == null) {
            dialog = DefaultDialogFragment(itemView)
        }

        val fragmentTransaction = fragmentManager.beginTransaction()

        if (dialog!!.isAdded) {
            fragmentTransaction.show(dialog!!)
        } else {
            fragmentTransaction.add(getContainerId(), dialog!!)
        }

        fragmentTransaction.commit()
    }

    override fun unload(itemView: ViewGroup) {
        dialog?.dismiss()
    }

    private fun getContainerId(): Int = when (frameType) {
        FrameType.LAYOUT -> parent.getId()
        FrameType.WINDOW -> 0
    }

    class Builder(private val parent: Switcher) {
        var frameType: FrameType =
            FrameType.LAYOUT

        fun build(): Frame =
            DialogFrame(parent, frameType)
    }
}