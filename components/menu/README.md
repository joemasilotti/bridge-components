# Menu Component

Adds a native button to the navigation bar that, when tapped, renders a native menu powered by `UIMenu` on iOS and `DropdownMenu` on Android.

When a native menu item is tapped, the corresponding HTML element will be clicked.

![Menu Component examples](screenshot.png)

```html
<div data-controller="bridge--menu">
  <a href="/one"
    data-bridge--menu-target="item"
    data-bridge-title="One"
    data-bridge-ios-image="1.circle"
    data-bridge-android-image="Counter 1">
      One
  </a>

  <a href="/two"
    data-bridge--menu-target="item"
    data-bridge-title="Two"
    data-bridge-ios-image="2.circle"
    data-bridge-android-image="Counter 2">
      Two
  </a>
</div>
```

## Item customizations

The title of each item will get set from the HTML element's `innerText`. Override this by setting `data-bridge-title`.

iOS uses [SF Symbols](https://developer.apple.com/sf-symbols/), set `bridge-ios-image` to the name of the symbol.

Android uses [Material Symbols](https://fonts.google.com/icons), set `bridge-android-image` to the icon name.

For Android, download the [Material Symbols](https://fonts.google.com/icons) font and unzip the Outlined version to `app/src/main/res/font/material_symbols.ttf`.

## Hide the HTML submit button

Hide the HTML element when the "menu" component is registered with the following CSS.

```css
[data-bridge-components~="menu"]
[data-controller~="bridge--menu"] {
  display: none;
}
```
