package com.michaeludjiawan.sample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.michaeludjiawan.switchlayout.frame.FrameType
import com.michaeludjiawan.switchlayout.layout.infoLayout
import com.michaeludjiawan.switchlayout.state.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val stateErrorKey = "state_error"
    private val stateEmptyKey = "state_empty"
    private val stateCustomKey = "state_custom"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStates()

        btn_loading_full_screen.setOnClickListener {
            loadWithResetDelayed {
                switch_main.switch(StateConstants.STATE_LOADING_FULL)
            }
        }

        btn_loading_full_screen_keep.setOnClickListener {
            loadWithResetDelayed {
                switch_main.switch(StateConstants.STATE_LOADING_FULL, LoadType.ADD)
            }
        }

        btn_loading_in_frame.setOnClickListener {
            loadWithResetDelayed {
                switch_main.switch(StateConstants.STATE_LOADING)
            }
        }

        btn_loading_in_frame_keep.setOnClickListener {
            loadWithResetDelayed {
                switch_main.switch(StateConstants.STATE_LOADING, LoadType.ADD)
            }
        }

        btn_error.setOnClickListener {
            switch_main.switch(stateErrorKey)
        }

        btn_empty.setOnClickListener {
            switch_main.switch(stateEmptyKey)
        }

        btn_custom.setOnClickListener {
            switch_main.switch(stateCustomKey)
        }
    }

    private fun initStates() {
        switch_main.addStates {
            state { loadingState() }
            state { loadingFullScreenState() }
            state {
                infoState {
                    key = stateErrorKey
                    layout = infoLayout(this@MainActivity) {
                        imageResId = R.drawable.ic_error_black_24dp
                        message = "Error Page!"
                        onActionClickListener("Go back") {
                            switch_main.clear()
                        }
                    }
                }
            }
            state {
                infoState {
                    key = stateEmptyKey
                    layout = infoLayout(this@MainActivity) {
                        imageResId = R.drawable.ic_warning_black_24dp
                        message = "Empty Page!"
                        onActionClickListener("Go back") {
                            switch_main.clear()
                        }
                    }
                }
            }
            state {
                key = stateCustomKey
                layout = CustomLayout(this@MainActivity).apply {
                    onBtnClickListener = { switch_main.clear() }
                }
                frameType = FrameType.WINDOW
            }
        }
    }

    private fun loadWithResetDelayed(action: () -> Unit) {
        action()
        resetOnDelay()
    }

    private fun resetOnDelay() {
        Handler().postDelayed({
            switch_main.clear()
        }, 2000)
    }
}