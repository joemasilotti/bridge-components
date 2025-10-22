# NFC Component

> [!IMPORTANT]
> This is a PRO component. Gain access to the Swift and Kotlin code by [purchasing a PRO license](https://buy.stripe.com/fZeaF6bn9b9d4Pm14b).

Read text and URLs from NFC tags.

This component requires a real NFC chip so it needs to be run on a physical device.

![NFC Component examples](/resources/screenshots/nfc.png)

```html
<div data-controller="bridge--nfc">
  <button data-action="bridge--nfc#scan">Scan</button>
  <p data-bridge--nfc-target="result"></p>
</div>
```

### iOS

Enable the NFC capability and entitlement in Xcode. See the [documentation](https://developer.apple.com/documentation/corenfc/building-an-nfc-tag-reader-app#Configure-the-App-to-Detect-NFC-Tags) for more details.

Then add the `NFCReaderUsageDescription` key as an array item to your Info.plist file. Add a single `NDEF` for the value. See the example iOS app for more details.

### Android

TODO
