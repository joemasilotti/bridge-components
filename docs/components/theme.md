# Theme Component

Toggle the device's dark/light mode appearance, styling native elements.

![Theme Component examples](/resources/screenshots/theme.png)

```html
<div data-controller="bridge--theme" data-bridge--theme-theme-value="dark"></div>
```

`data-bridge--theme-theme-value` accepts `"dark"` and `"light"` to set the device's theme to dark mode or light mode, respectively.

The Stimulus controller watches this attribute for changes and passes the new one to the iOS/Android app. The demo Rails application uses [an additional Stimulus controller](/examples/rails/app/javascript/controllers/theme_controller.js) to toggle the theme and write to this data attribute.

## Additional Android recommendation

The back navigation button might not be visible when forcing dark mode. Add the following to `app/src/main/res/values-night/themes.xml` to set an appropriate color.

```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <style name="Base.Theme.Demo" parent="Theme.Material3.DayNight.NoActionBar">
        <item name="toolbarNavigationButtonStyle">@style/Widget.AndroidDemo.Toolbar.Navigation.Tinted</item>
    </style>

    <style name="Widget.AndroidDemo.Toolbar.Navigation.Tinted" parent="Widget.AppCompat.Toolbar.Button.Navigation">
        <item name="tint">?colorOnSurface</item>
    </style>
</resources>
```
