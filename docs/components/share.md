# Share Component

Adds a button that presents a native share sheet sharing a URL. By default uses `window.location.href` but can be customized with `bridge-data-url`.

![Share Component examples](/resources/screenshots/share.png)

## Share the current URL

```html
<meta data-controller="bridge--share">
```

## Share a custom URL

```html
<meta data-controller="bridge--share" data-bridge-url="https://bridgecomponents.dev">
```

## Customizing share button colors

On iOS, the color of the share button will default to your [app's tint color](https://developer.apple.com/documentation/xcode/specifying-your-apps-color-scheme), set via `AccentColor` in Assets.xcassets. Override this by adding a new color named `BridgeworkShareColor`. Or set all UI elements provided by this library with a new color named `BridgeworkColor`.

On Android, the color of the share button will default to Material's `colorOnSurface`. Override this by adding a new color to `colors.xml` named `bridgework_share_color`. Or set all UI elements provided by this library with a new color named `bridgework_color`.

These defaults can be overridden (on both platforms) for individual share buttons by setting a HEX code for `data-bridge-color` in the HTML.

```html
<meta data-controller="bridge--share" data-bridge-color="#804F9F">
```
