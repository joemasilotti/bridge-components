# Barcode Scanner Component

> [!IMPORTANT]
> This is a paid component. Gain access to the source code by [purchasing a Pro license](https://buy.https://buy.stripe.com/fZeaF6bn9b9d4Pm14b?prefilled_promo_code=EARLYACCESSstripe.com/fZeaF6bn9b9d4Pm14b).

Presents a camera that scans barcodes and QR codes using a native camera capture.

This component requires a real camera so it needs to be run on a physical device.

![Barcode Scanner Component examples](/resources/screenshots/barcode-scanner.png)

## Supported codes

By default, the following codes are recognized:

* [QR codes](https://en.wikipedia.org/wiki/QR_code)
* [EAN-8](https://en.wikipedia.org/wiki/EAN-8)
* [EAN-13](https://en.wikipedia.org/wiki/International_Article_Number)
* [PDF417](https://en.wikipedia.org/wiki/PDF417)
* [Code 128](https://en.wikipedia.org/wiki/Code_128)

Additional codes can be added by updating one line of the native component. iOS 18 supports [15 symbologies](https://developer.apple.com/documentation/vision/vnbarcodesymbology/code93-2geph#Supported-Symbologies) and Android 15 supports [15 barcode formats](https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.BarcodeFormat).

## Additional requirements

### iOS

(none)

### Android

To use the [Google code scanner API](https://developers.google.com/ml-kit/vision/barcode-scanning/code-scanner), add the following meta data to your `<application>` in `AndroidManifest.xml`.

```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    <application>
        <meta-data
            android:name="com.google.mlkit.vision.DEPENDENCIES"
            android:value="barcode_ui" />
    </application>
</manifest>
```

You'll also need to add the `com.google.android.gms:play-services-code-scanner` dependency.
