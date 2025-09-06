# Bridge Components for Hotwire Native apps

> by [Joe Masilotti](https://masilotti.com), the Hotwire Native guy

[Hotwire Native](https://native.hotwired.dev) enables seamless communication between native Swift and Kotlin code and web views in hybrid mobile apps. [Bridge components](https://native.hotwired.dev/overview/bridge-components) extend this functionality by providing reusable native components that interact your web views. They enable developers to break out of the web view container and **drive native features**.

This repository contains generalized, production-ready bridge components extracted from [real-world client projects](https://masilotti.com/services/). Once installed, each component can be added to any page of your app and customized with a bit of HTML.

## Free components

The source code for the iOS, Android, and web components are included in this repo.

### [Alert Component](components/alert/)

Displays a native alert dialog to confirm an action.

[![Alert Component examples](resources/screenshots/alert.png)](components/alert/)

### [Button Component](components/button/)

Adds a native button to the navigation bar on iOS and action bar on Android. The button can contain either a text string or an image.

[![Button Component examples](resources/screenshots/button.png)](components/button/)

### [Form Component](components/form/)

Replaces a form's submit `<button>` with a native one that disables during submission.

[![Form Component examples](resources/screenshots/form.png)](components/form/)

### [Haptic Component](components/haptic/)

Vibrate the device via the haptic engine.

[![Haptic Component examples](resources/screenshots/haptic.png)](components/haptic/)

### [Menu Component](components/menu/)

Adds a native button to the navigation bar that, when tapped, renders a native menu powered by `UIMenu` on iOS and `DropdownMenu` on Android.

[![Menu Component examples](resources/screenshots/menu.png)](components/menu/)

### [Review Prompt Component](components/review-prompt/)

Prompts the user for a review on the App Store on iOS and Google Play on Android.

[![Review Prompt Component examples](resources/screenshots/review-prompt.png)](components/review-prompt/)

### [Search Component](components/search/)

Displays a native search field that passes along queries to JavaScript for filtering logic.

[![Search Component examples](resources/screenshots/search.png)](components/search/)

### [Share Component](components/share/)

Adds a button that presents a native share sheet.

[![Share Component examples](resources/screenshots/share.png)](components/share/)

### [Theme Component](components/theme/)

Toggle the device's dark/light mode appearance, styling native elements.

[![Theme Component examples](resources/screenshots/theme.png)](components/theme/)

### [Toast Component](components/toast/)

Render a native, floating message that disappears after a few seconds.

[![Toast Component examples](resources/screenshots/toast.png)](components/toast/)

## PRO components

> [!IMPORTANT]
> Gain access to these components by [purchasing a Pro license](https://buy.stripe.com/fZeaF6bn9b9d4Pm14b). This is a **one-time payment** and not a subscription. It includes access to all bridge components available today, plus all future updates. View the [full pro license](PRO-LICENSE.md) before purchasing.

### [Barcode Scanner Component](components/barcode-scanner/)

Presents a camera that scans barcodes and QR codes using a native camera capture.

[![Barcode Scanner Component examples](resources/screenshots/barcode-scanner.png)](components/barcode-scanner/)

### [Biometrics Lock Component](components/biometrics-lock/)

When enabled, locks the app when it is backgrounded until the user authenticates via biometrics.

[![Biometrics Lock Component examples](resources/screenshots/biometrics-lock.png)](components/biometrics-lock/)

### [Document Scanner Component](components/document-scanner/)

Presents a camera that digitizes physical documents.

[![Document Scanner Component examples](resources/screenshots/document-scanner.png)](components/document-scanner/)

### [Location Component](components/location/)

Prompts the user for their precise location with a single system dialog.

[![Location Component examples](resources/screenshots/location.png)](components/location/)

### [Notification Token Component](components/notification-token/)

Gets the user's push notification token.

[![Notification Token Component examples](resources/screenshots/notification-token.png)](components/notification-token/)

### [Permissions Component](components/permissions/)

Gets the status of the user's granted permissions, like location and push notifications.

[![Permissions Component examples](resources/screenshots/permissions.png)](components/permissions/)

## Requirements

* Web: [Hotwire Native Bridge](https://native.hotwired.dev/reference/bridge-installation)
* iOS: [Hotwire Native iOS](https://github.com/hotwired/hotwire-native-ios) v1.2 or later
* Android:
    * [Hotwire Native Android](https://github.com/hotwired/hotwire-native-android) v1.0 or later
    * [Jetpack Compose](https://developer.android.com/develop/ui/compose/setup)
    * A serialization library, like [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization?tab=readme-ov-file#setup)
    * [Material Symbol](https://fonts.google.com/icons) font if using images
        * Unzip the downloaded font and copy the Outlined version `.ttf` to `app/src/main/res/font/material_symbols.ttf`

Check the [`examples/` directoy](examples/) for demo iOS, Android, and Rails apps.

## Installation

Each component requires a Stimulus controller and a Swift/Kotlin component.

### Web - Stimulus controllers

#### 1. Install the bridge-components package

Add the `bridge-components` module via yarn:

```bash
yarn add @joemasilotti/bridge-components
```

or npm:

```bash
npm install @joemasilotti/bridge-components
```

or with Rails importmaps:

```bash
bin/importmap pin @joemasilotti/bridge-components
```

#### 2. Register the Stimulus controllers

Register the Stimulus controllers after starting your Stimulus application.

Register all the available controllers:

```javascript
import { Application } from "@hotwired/stimulus"
import { controllers } from "@joemasilotti/bridge-components"

const application = Application.start()
application.load(controllers)
```

or manually register individual controllers:

```javascript
import { Application } from "@hotwired/stimulus"
import { AlertBridgeController, ButtonBridgeController } from "@joemasilotti/bridge-components"

const application = Application.start()
application.register("bridge--alert", AlertBridgeController)
application.register("bridge--button", ButtonBridgeController)
```

### iOS - Swift components

#### 1. Add the Swift package dependency

In Xcode, select File → Add Packages Dependencies… and enter `https://github.com/joemasilotti/bridge-components` in the search field. Make sure your project is set under "Add to Project" and click Add Package.

![Add the Swift package dependency](resources/screenshots/add-swift-package.png)

Then click Add Package again.

#### 2. Register the native components

Register all the available components:

```swift
import BridgeComponents
import HotwireNative
import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        Hotwire.registerBridgeComponents(BridgeComponent.all)
        return true
    }
}
```

or manually register individual components:

```swift
import BridgeComponents
import HotwireNative
import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        Hotwire.registerBridgeComponents([
            AlertComponent.self,
            ButtonComponent.self,
        ])
        return true
    }
}
```

### Android - Kotlin components

> [!WARNING]
> Android usage still requires manual installation. An official Gradle package is coming soon!

1. Copy the Kotlin file (`ExampleComponent.kt`) into your Android Studio project.
1. Register the component inside your `Application` subclass.

```kotlin
package com.masilotti.demo // Change to your package name.

import android.app.Application
import com.masilotti.demo.components.ExampleComponent // Import component here.
import dev.hotwire.core.bridge.BridgeComponentFactory
import dev.hotwire.core.bridge.KotlinXJsonConverter
import dev.hotwire.core.config.Hotwire
import dev.hotwire.navigation.config.registerBridgeComponents

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hotwire.registerBridgeComponents(
            BridgeComponentFactory("example", ::ExampleComponent)
        )
    }
}
```

## Usage

Once installed, use a component by adding a `data-controller` attribute that matches the name of the Stimulus controller.

For example, to use the [Button Component](components/button/):

```html
<a href="#" data-controller="bridge--button">Button</a>
```

Each component can then be configured further. Check the component's README in the [`components/` directory](components/) for more information.

## Need help?

If you need help installing, configuring, or using the components, feel free to [open a new discussion](https://github.com/joemasilotti/bridge-components/discussions/new?category=q-a) or [send me an email](mailto:joe@masilotti.com). I'd love to help!
