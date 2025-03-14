# Notification Token Component

Gets the user's push notification permission status and notification token.

![Notification Token Component examples](/resources/screenshots/notification-token.png)

```html
<div data-controller="bridge--notification-token">
  <button data-action="bridge--notification-token#getToken">
    Get notification token
  </button>

  <p data-bridge--notification-token-target="status"></p>
  <p data-bridge--notification-token-target="token"></p>
</div>
```

## Notification permission status

The following notification permission statuses are returned from the app. Use the status code to determine behavior in your app as needed.

|Text|Code|[iOS status](https://developer.apple.com/documentation/usernotifications/unauthorizationstatus)|Android status|
|---|---|---|---|
|not determined|-1|`notDetermined`|N/A|
|denied|0|`denied`|`PERMISSION_DENIED`|
|authorized|1|`authorized`, `provisional`, or `ephemeral`|`PERMISSION_GRANTED`|
|unknown|-2|(anything else)|N/A|

Note that Android returns "denied" status even before asking the user for permission.

## Additional requirements

Accessing the user's notification token requires additional steps for each platform. Check the example apps for a full working version of each.

### iOS

Enable the Push Notification capability in Xcode. See the [documentation](https://developer.apple.com/documentation/usernotifications/registering-your-app-with-apns#Enable-the-push-notifications-capability) for more details (and a screenshot).

Then add the following to `AppDelegate.swift` to post new notification token data to the bridge component.

```swift
func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {
    NotificationCenter.default.post(name: .didReceiveDeviceToken, object: deviceToken)
}
```

### Android

First, sign up for a free [Firebase](https://console.firebase.google.com/) account and create a new project with an attached Android app.

Download the `google-services.json` file and move it to your Android's project under the `App/` directory.

#### Dependencies

First, set the versions of each dependency by adding the following to `libs.versions.toml`.

```toml
[versions]
firebaseBom = "33.10.0"

[libraries]
firebase-bom = { module = "com.google.firebase:firebase-bom", version.ref = "firebaseBom" }
firebase-messaging = { module = "com.google.firebase:firebase-messaging" }
```

Then, add the following to the project's `build.gradle.kts` file.

```kts
plugins {
    id("com.google.gms.google-services") version "4.4.2" apply false
}
```

And the following to the app's `build.gradle.kts` file.

```kts
plugins {
    id("com.google.gms.google-services")
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
}
```

#### `WebFragment`

To register for notifications we need to hook into the fragment presenting the web view.

Copy-paste `WebFragment.kt` from [components/shared/](/components/shared/) into your app.

Register the new fragment and kick off the Firebase integration by adding the following to your `Application` subclass.

```kt
package com.masilotti.demo

import android.app.Application
import com.google.firebase.FirebaseApp

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        Hotwire.registerFragmentDestinations(WebFragment::class)
        Hotwire.defaultFragmentDestination = WebFragment::class
    }
}
```

#### Permissions

Finally, add the notification permissions to your `AndroidManifest.xml`.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />

    <application>
        <!-- ...
    </application>
</manifest>
```

Check the [example Android app](/examples/android) for a working demo. You'll still need to bring your own `google-services.json` for it to work.
