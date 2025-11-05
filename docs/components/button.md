# Button Component

Adds a native button to the navigation bar on iOS and action bar on Android.

When the native button is tapped, the underlying HTML element will be clicked.

The button can contain either a text string or an image.

![Button Component examples](/resources/screenshots/button.png)

## Text button

Sets the content of the button to the title of the element it is attached. Here, an `<a>` tag.

```erb
<%= link_to "Button", "#", data: {
  controller: "bridge--button"
} %>
```

<details>
<summary>HTML version</summary>

```html
<a href="#" data-controller="bridge--button">Button</a>
```
</details>


## Image button

Sets the content of the button to an image.

iOS uses [SF Symbols](https://developer.apple.com/sf-symbols/), set `bridge-ios-image` to the name of the symbol.

Android uses [Material Symbols](https://fonts.google.com/icons), set `bridge-android-image` to the icon name.

```erb
<%= link_to "Button", "#", data: {
  controller: "bridge--button",
  bridge_ios_image: "photo",
  bridge_android_image: "Image"
} %>
```

<details>
<summary>HTML version</summary>

```html
<a 
    href="#"
    data-controller="bridge--button"
    data-bridge-ios-image="photo"
    data-bridge-android-image="Image"
>Button</a>
```
</details>

## Left buttons

Set `data-bridge-side="left"` to add a button to the *left* side of the navigation bar on iOS and action bar on Android. It correctly will not override the back button.

```erb
<%= link_to "Button", "#", data: {
  controller: "bridge--button",
  bridge_side: "left"
} %>
```

<details>
<summary>HTML version</summary>

```html
<a
    href="#"
    data-controller="bridge--button"
    data-bridge-side="left"
>Button</a>
```
</details>

## Customizing button colors

On iOS, the color of the button will default to your [app's tint color](https://developer.apple.com/documentation/xcode/specifying-your-apps-color-scheme), set via `AccentColor` in Assets.xcassets. Override this by adding a new color named `BridgeworkButtonColor`. Or set all UI elements provided by this library with a new color named `BridgeworkColor`.

On Android, ...

These defaults can be overridden for individual buttons by setting a HEX code for `data-bridge-color` in the HTML.

```html
<a
    href="#"
    data-controller="bridge--button"
    data-bridge-color="#804F9F"
>Button</a>
```

## Hide the HTML button

Hide the HTML button when the "button" component is registered with the following CSS.

```css
[data-bridge-components~="button"]
[data-controller~="bridge--button"] {
  display: none;
}
```
