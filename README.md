# SwitchLayout

A layout which allow you to handle different states in a flexible way

# Download

```implementation 'com.michaeludjiawan.switchlayout:switch-layout:0.1.0'```

# Usage

First, wrap your content layout with `SwitchLayout`

```
<com.michaeludjiawan.switchlayout.SwitchLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/switch_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <LinearLayout
        android:id="@+id/content_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <!-- CONTENT -->
    </LinearLayout>
    
</com.michaeludjiawan.switchlayout.SwitchLayout>
```

Then easily switch state to provided common states or your customized state

```
// Switch to in-frame loading state
switch_layout.switch { loadingState() }

// Switch to window loading state
switch_layout.switch { loadingFullScreenState() }

// Switch to empty/error
switch_layout.switch { 
  infoState() {
    layout = infoLayout(this@MainActivity) {
      imageResId = R.drawable.ic_error_black_24dp
      message = "Error Page!"
      onActionClickListener("Go back") {
        switch_main.clear()
      }
    }
  }
}

// Switch back to content state
switch_layout.clear()
```

Implementation of common states

```
// SwitcherDsl.kt

fun Switcher.loadingState(builderAction: (State.Builder.() -> Unit) = {}): State = state {
    key = STATE_LOADING
    layout = DefaultLoadingItem(getContext())
    builderAction()
}

fun Switcher.loadingFullScreenState(builderAction: (State.Builder.() -> Unit) = {}) = loadingState {
    frame {
        frameType = FrameType.WINDOW
    }
    builderAction()
}

fun Switcher.infoState(builderAction: State.Builder.() -> Unit = {}): State = state {
    layout = DefaultInfoLayout(getContext())
    builderAction()
}
```

Or create a fully customized state according to your need

```
switch_main.switch {
  state {
    key = "CUSTOM_STATE"
    layout = YourCustomLayout()
    frame {
      frameType = FrameType.WINDOW
    }
    existingStateVisibility = View.GONE
    }
}
```
