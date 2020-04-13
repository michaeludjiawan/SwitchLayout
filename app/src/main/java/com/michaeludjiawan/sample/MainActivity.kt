package com.michaeludjiawan.sample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.michaeludjiawan.switchlayout.frame.FrameType
import com.michaeludjiawan.switchlayout.layout.infoLayout
import com.michaeludjiawan.switchlayout.switcher.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_loading_full_screen.setOnClickListener {
            loadWithResetDelayed {
                switch_main.switch {
                    loadingFullScreenState()
                }
            }
        }

        btn_loading_full_screen_keep.setOnClickListener {
            loadWithResetDelayed {
                switch_main.switch(LoadType.ADD) {
                    loadingFullScreenState()
                }
            }
        }

        btn_loading_in_frame.setOnClickListener {
            loadWithResetDelayed {
                switch_main.switch {
                    loadingState()
                }
            }
        }

        btn_loading_in_frame_keep.setOnClickListener {
            loadWithResetDelayed {
                switch_main.switch(LoadType.ADD) {
                    loadingState()
                }
            }
        }

        btn_error.setOnClickListener {
            switch_main.switch {
                infoState {
                    layout = infoLayout(this@MainActivity) {
                        imageResId = R.drawable.ic_error_black_24dp
                        message = "Error Page!"
                        onActionClickListener("Go back") {
                            switch_main.clear()
                        }
                    }
                }
            }
        }

        btn_empty.setOnClickListener {
            switch_main.switch {
                infoState {
                    layout = infoLayout(this@MainActivity) {
                        imageResId = R.drawable.ic_warning_black_24dp
                        message = "Empty Page!"
                        onActionClickListener("Go back") {
                            switch_main.clear()
                        }
                    }
                }
            }
        }

        btn_custom.setOnClickListener {
            switch_main.switch {
                state {
                    key = "CUSTOM_STATE"
                    layout = CustomLayout(this@MainActivity).apply {
                        onBtnClickListener = { switch_main.clear() }
                    }
                    frame {
                        frameType = FrameType.WINDOW
                    }
                }
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