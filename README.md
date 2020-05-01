# SwitchLayout

A layout which allow you to handle different states in a flexible way

![Demo](https://media.giphy.com/media/QZsIEBVO44VTopEt8s/giphy.gif)

# Download

```implementation 'com.michaeludjiawan.switchlayout:switchlayout:0.3.0'```

# Usage

**Add in XML**

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

**Switch between state**

- Use ```add()``` or ```replace()``` to switch state with or without removing existing state.
- Use predefined state or create your own state.
- Switch back to content state using ```switchToContent()```

```
switch_layout.add { loadingState() }
switch_layout.replace { // this: State.Builder
    key = "custom_key"
    layout = CustomLayout()
    loaderType = LoaderType.DEFAULT // or WINDOW, PERSISTENT
}
```

**Predefined State**

- ```loadingState()```. Show progress dialog.
- ```loadingFullState()```. Show progress dialog in a window dialog.
- ```emptyState()``` and ```errorState()```. Show default layout with customizable image, message, and button.

**Custom State**

```
switch.replace {
    key = "custom_key"
    layout = CustomLayout()
    loaderType = LoaderType.DEFAULT
}

or

val customState = state(switch_layout) {
    key = "custom_key"
    layout = CustomLayout()
    loaderType = LoaderType.DEFAULT
}

switch_layout.replace(customState)
```
