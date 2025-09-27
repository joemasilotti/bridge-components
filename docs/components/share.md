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
