# Barcode Scanner Component

Presents a camera that scans a barcodes and QR codes using a native camera capture.

This component requires a real camera so it needs to be run on a physical device.

![Barcode Scanner Component examples](/resources/screenshots/barcode-scanner.png)

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
