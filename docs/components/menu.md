# Menu Component

Adds a native button to the navigation bar that, when tapped, renders a native menu powered by `UIMenu` on iOS and `DropdownMenu` on Android.

When a native menu item is tapped, the corresponding HTML element will be clicked.

![Menu Component examples](/resources/screenshots/menu.png)

```html
<div data-controller="bridge--menu">
  <a href="/one"
    data-bridge--menu-target="item"
    data-bridge-title="One"
    data-bridge-ios-image="1.circle"
    data-bridge-android-image="counter_1">
      One
  </a>

  <a href="/two"
    data-bridge--menu-target="item"
    data-bridge-title="Two"
    data-bridge-ios-image="2.circle"
    data-bridge-android-image="counter_2">
      Two
  </a>
</div>
```

## Item customizations

The title of each item will get set from the HTML element's `innerText`. Override this by setting `data-bridge-title`.

iOS uses [SF Symbols](https://developer.apple.com/sf-symbols/), set `bridge-ios-image` to the name of the symbol.

Android uses [Material Symbols](https://fonts.google.com/icons), set `bridge-android-image` to the icon name.

For Android, download the [Material Symbols](https://fonts.google.com/icons) font and unzip the Outlined version to `app/src/main/res/font/material_symbols.ttf`.

Items (text and image) can also be colored red by setting `bridge-destructive` to `true` to indicate destructive actions.

```html
<div data-controller="bridge--menu">
  <a href="/one"
    data-bridge--menu-target="item"
    data-bridge-destructive="true">
      One
  </a>
</div>
```

## Customizing menu button colors

On iOS, the color of the menu button will default to your [app's tint color](https://developer.apple.com/documentation/xcode/specifying-your-apps-color-scheme), set via `AccentColor` in Assets.xcassets. Override this by adding a new color named `BridgeworkMenuColor`. Or set all UI elements provided by this library with a new color named `BridgeworkColor`.

On Android, the color of the menu button will default to Material's `colorOnSurface`. Override this by adding a new color to `colors.xml` named `bridgework_menu_color`. Or set all UI elements provided by this library with a new color named `bridgework_color`.

These defaults can be overridden (on both platforms) for individual menu buttons by setting a HEX code for `data-bridge-color` in the HTML.

```html
<div data-controller="bridge--menu" data-bridge-color="#804F9F">
  <!-- ... -->
</div>
```

## Hide the HTML

Hide the HTML element when the "menu" component is registered with the following CSS.

```css
[data-bridge-components~="menu"]
[data-controller~="bridge--menu"] {
  display: none;
}
```
