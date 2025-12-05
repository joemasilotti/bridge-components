# Notification Token Component

> [!IMPORTANT]
> This is a PRO component. Gain access to the Swift and Kotlin code by [purchasing a PRO license](https://masilotti.com/bridge-components/pro).

Gets the user's push notification token.

![Notification Token Component examples](/resources/screenshots/notification-token.png)

```html
<div data-controller="bridge--notification-token">
  <button data-action="bridge--notification-token#get">
    Get notification token
  </button>

  <p data-bridge--notification-token-target="token"></p>
</div>
```

## JavaScript events

The `result` target is optional. `bridge--notification-token:retrieved` is dispatched when a notification token is retrieved.

```html
<div data-controller="my-controller"
    data-action="bridge--notification-token:retrieved@window->my-controller#retrieved">
</div>
```

```javascript
// my_controller.js

import { Controller } from "@hotwired/stimulus"

export default class extends Controller {
  retrieved(event) {
    const token = event.detail.token
    console.debug(`Retrieved notification token: ${token}.`)
  }
}
```

## Additional requirements

Accessing the user's notification token requires additional steps for each platform. Check the example apps for a full working version of each.

### iOS

Enable the Push Notification capability in Xcode. See the [documentation](https://developer.apple.com/documentation/usernotifications/registering-your-app-with-apns#Enable-the-push-notifications-capability) for more details (and a screenshot).

Then add the following to `AppDelegate.swift` to post new notification token data to the bridge component.

```swift
class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {
        NotificationCenter.default.post(name: .didReceiveDeviceToken, object: deviceToken)
    }
}
```

### Android

Sending notifications on Android is done through [Firebase Cloud Messaging (FCM)](https://firebase.google.com/docs/cloud-messaging). Sign up for a free [Firebase](https://console.firebase.google.com/) account and create a new project with an attached Android app.

Then download the `google-services.json` file and move it to your Android's project under the `App/` directory.

#### Dependencies

Set the versions of each dependency by adding the following to `libs.versions.toml`.

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

Register `WebFragment` and kick off the Firebase integration by adding the following to your `Application` subclass.

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
