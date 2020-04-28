package com.michaeludjiawan.sample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.michaeludjiawan.switchlayout.loader.LoaderType
import com.michaeludjiawan.switchlayout.state.emptyState
import com.michaeludjiawan.switchlayout.state.errorState
import com.michaeludjiawan.switchlayout.state.loadingFullScreenState
import com.michaeludjiawan.switchlayout.state.loadingState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val stateCustomKey = "state_custom"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_loading_full_screen.setOnClickListener {
            loadWithResetDelayed {
                switch_main.replace { loadingFullScreenState() }
            }
        }

        btn_loading_full_screen_keep.setOnClickListener {
            loadWithResetDelayed {
                switch_main.add {
                    loadingFullScreenState()
                }
            }
        }

        btn_loading_in_frame.setOnClickListener {
            loadWithResetDelayed {
                switch_main.replace { loadingState() }
            }
        }

        btn_loading_in_frame_keep.setOnClickListener {
            loadWithResetDelayed {
                switch_main.add {
                    loadingState()
                }
            }
        }

        btn_error.setOnClickListener {
            switch_main.replace {
                errorState {
                    setImage(R.drawable.ic_error_black_24dp)
                    setMessage("Error Page!")
                    setAction("Go back") {
                        switch_main.switchToContent()
                    }
                }
            }
        }

        btn_empty.setOnClickListener {
            switch_main.replace {
                emptyState {
                    setImage(R.drawable.ic_warning_black_24dp)
                    setMessage("Empty Page!")
                    setAction("Go back") {
                        switch_main.switchToContent()
                    }
                }
            }
        }

        btn_custom.setOnClickListener {
            switch_main.replace {
                key = stateCustomKey
                layout = CustomLayout(this@MainActivity).apply {
                    onBtnClickListener = { switch_main.switchToContent() }
                }
                loaderType = LoaderType.WINDOW
            }
        }
    }

    private fun loadWithResetDelayed(action: () -> Unit) {
        action()
        resetOnDelay()
    }

    private fun resetOnDelay() {
        Handler().postDelayed({
            switch_main.switchToContent()
        }, 2000)
    }
}