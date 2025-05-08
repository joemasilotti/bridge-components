# Document Scanner Component

> [!IMPORTANT]
> This is a paid component. Gain access to the source code by [purchasing a Pro license](https://buy.stripe.com/fZeaF6bn9b9d4Pm14b).

Presents a camera that digitizes physical documents and renders the images. The underlying native APIs on iOS and Android are configured to:

* automatically detect documents
* trim, crop, and rotate captures
* remove imperfections like shadows and wrinkles
* recognize multiple pages

This component requires a real camera so it needs to be run on a physical device.

![Document Scanner Component examples](/resources/screenshots/document-scanner.png)

```html
<div data-controller="bridge--document-scanner">
  <button data-action="bridge--document-scanner#scan">Scan</button>

  <div data-bridge--document-scanner-target="result"></div>
</div>
```

## Additional requirements

### iOS

(none)

### Android

To use the [document scanner with ML Kit](https://developers.google.com/ml-kit/vision/doc-scanner/android),add the `com.google.android.gms:play-services-mlkit-document-scanner` dependency.
