# Location Component

> [!IMPORTANT]
> This is a PRO component. Gain access to the Swift and Kotlin code by [purchasing a PRO license](https://buy.stripe.com/fZeaF6bn9b9d4Pm14b).

Prompts the user for their precise location with a single system dialog.

![Location Component examples](/resources/screenshots/location.png)

```html
<div data-controller="bridge--location">
  <button data-action="bridge--location#get">Get location</button>

  <p data-bridge--location-target="result"></p>
</div>
```

## Additional requirements

Accessing the user's location requires an additional step for each platform. Check the example apps for a full working version of each.

### iOS

To access the user's location on iOS, add the `NSLocationWhenInUseUsageDescription` value to your Info.plist. More information can be found in the [documentation](https://developer.apple.com/documentation/bundleresources/choosing-the-location-services-authorization-to-request).

### Android

To access the user's location on Android, add the following permissions to your `AndroidManifest.xml`.

```
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

You'll also need to add the `com.google.android.gms:play-services-location` dependency. More information can be found in the [documentation](https://developers.google.com/location-context/fused-location-provider).
