# NFC Component

> [!IMPORTANT]
> This is a PRO component. Gain access to the Swift and Kotlin code by [purchasing a PRO license](https://buy.stripe.com/fZeaF6bn9b9d4Pm14b).

Read and write text and URLs to NFC tags.

This component requires a real NFC chip so it needs to be run on a physical device.

![NFC Component examples](/resources/screenshots/nfc.png)

```html
<div data-controller="bridge--nfc">
  <button data-action="bridge--nfc#read">Read</button>
  <p data-bridge--nfc-target="result"></p>

  <input data-bridge--nfc-target="value" value="Hello, world!" type="text">
  <button data-action="bridge--nfc#write">Write</button>
</div>
```

## Additional requirements

### iOS

Enable the NFC capability and entitlement in Xcode. See the [documentation](https://developer.apple.com/documentation/corenfc/building-an-nfc-tag-reader-app#Configure-the-App-to-Detect-NFC-Tags) for more details.

Then add the `NFCReaderUsageDescription` key as an array item to your Info.plist file. Add a single `NDEF` for the value. See the example iOS app for more details.

### Android

#### `WebFragment`

To use `NfcAdapter` we need to hook into the fragment presenting the web view.

Register `WebFragment` in your `Application` subclass.

```kt
package com.masilotti.demo

import android.app.Application

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hotwire.registerFragmentDestinations(WebFragment::class)
        Hotwire.defaultFragmentDestination = WebFragment::class
    }
}
```

#### Permissions

Then add the following meta data to your `<application>` in `AndroidManifest.xml`.

```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    <uses-permission android:name="android.permission.NFC" />
    <uses-feature android:name="android.hardware.nfc" android:required="false" />

    <application>
        <!-- ... -->
    </application>
</manifest>
```
