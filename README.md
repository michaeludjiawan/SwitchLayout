# SwitchLayout

A layout which allow you to handle different states in a flexible way

# Download

```implementation 'com.michaeludjiawan.switchlayout:switchlayout:0.2.0'```

# Usage

Wrap your content layout with `SwitchLayout`

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

Define your states

```
switch_layout.addStates {
    state {
        key = stateCustomKey
        layout = CustomLayout()
        frameType = FrameType.WINDOW
    }
    // or use predefined common states
    state { loadingState }
    state { loadingFullScreenState() }
    state {
         errorLayout {
            setImage(R.drawable.ic_error)
            setMessage("Error Page")
            setAction("Action") {
                switch_main.switchToContent()
            }
        }
    }
    state {
        emptyLayout {
            setImage(R.drawable.ic_empty)
            setMessage("Empty Page")
            setAction("Action") {
                switch_main.switchToContent()
            }
        }
    }
}
```

Switch between states

```
switch_main.switch("custom state key")

// switch to predefined states
switch_main.switch(StateConstants.STATE_LOADING)
switch_main.switch(StateConstants.STATE_LOADING_FULL, LoadType.ADD)
switch_main.switch(StateConstants.STATE_ERROR)
switch_main.switch(StateConstants.STATE_EMPTY)

// convenience method to switch back to content state
switch_main.switchToContent()
```