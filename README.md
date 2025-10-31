# Bridge Components for Hotwire Native apps

> by [Joe Masilotti](https://masilotti.com), the Hotwire Native guy

[Hotwire Native](https://native.hotwired.dev) enables seamless communication between native Swift and Kotlin code and web views in hybrid mobile apps. [Bridge components](https://native.hotwired.dev/overview/bridge-components) extend this functionality by providing reusable native components that interact your web views. They enable developers to break out of the web view container and **drive native features**.

This repository contains generalized, production-ready bridge components extracted from [real-world client projects](https://masilotti.com/services/). Once installed, each component can be added to any page of your app and customized with a bit of HTML.

![Bridge component examples](resources/screenshots/bridge-components.png)

## Components

[See all the components](https://masilotti.com/bridge-components/#components)

* [Alert](docs/components/alert.md)
* [Barcode Scanner](docs/components/barcode-scanner.md)
* [Biometrics Lock](docs/components/biometrics-lock.md)
* [Button](docs/components/button.md)
* [Document Scanner](docs/components/document-scanner.md)
* [Form](docs/components/form.md)
* [Haptic](docs/components/haptic.md)
* [Location](docs/components/location.md)
* [Menu](docs/components/menu.md)
* [NFC](docs/components/nfc.md)
* [Notification Token](docs/components/notification-token.md)
* [Permissions](docs/components/permissions.md)
* [Review Prompt](docs/components/review-prompt.md)
* [Search](docs/components/search.md)
* [Share](docs/components/share.md)
* [Theme](docs/components/theme.md)
* [Toast](docs/components/toast.md)

## Requirements

* [Hotwire Native Bridge](https://native.hotwired.dev/reference/bridge-installation)
* [Hotwire Native iOS](https://github.com/hotwired/hotwire-native-ios) v1.2 or later
* Android
    * [Hotwire Native Android](https://github.com/hotwired/hotwire-native-android) v1.0 or later
    * Kotlin v2.2.0

Check the [`examples/` directory](examples/) for demo iOS, Android, and Rails apps.

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

Register the Stimulus controllers after starting your Stimulus application:

```javascript
import { Application } from "@hotwired/stimulus"
import { controllers } from "@joemasilotti/bridge-components"

const application = Application.start()
application.load(controllers)
```

### iOS - Swift components

#### 1. Add the Swift package dependency

In Xcode, select File → Add Packages Dependencies… and enter `https://github.com/joemasilotti/bridge-components` in the search field. Make sure your project is set under "Add to Project" and click Add Package. Then click Add Package again.

#### 2. Register the native components

Import the framework and register all the available components in `AppDelegate.swift`:

```swift
import BridgeComponents // THIS LINE
import HotwireNative
import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        Hotwire.registerBridgeComponents(Bridgework.coreComponents) // THIS LINE
        return true
    }
}
```

### Android - Kotlin components

#### 1. Add the JitPack repository to your build file

In Android Studio, add the following line to `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // THIS LINE
    }
}
```

#### 2. Add the dependency 

Add the following line to the bottom of `build.gradle.kts` (Module :app), replacing `<latest-version>` with the [latest release](https://github.com/joemasilotti/bridge-components/releases):

```kotlin
dependencies {
    // ...

    implementation("dev.hotwire:core:<hotwire-native-version>")
    implementation("dev.hotwire:navigation-fragments:<hotwire-native-version>")

    implementation("com.github.joemasilotti:bridge-components:<latest-verison>") // THIS LINE
}
```

#### 3. Register the native components

In your `Application()` subclass add the following:

```kotlin
package com.your.package.name

import android.app.Application
import com.masilotti.bridgecomponents.shared.Bridgework // THIS LINE
import dev.hotwire.core.bridge.KotlinXJsonConverter // THIS LINE
import dev.hotwire.core.config.Hotwire
import dev.hotwire.navigation.config.registerBridgeComponents // THIS LINE

class YourApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hotwire.config.jsonConverter = KotlinXJsonConverter() // THIS LINE
        Hotwire.registerBridgeComponents(*Bridgework.coreComponents) // THIS LINE
    }
}
```

## Usage

Once installed, use a component by adding a `data-controller` attribute that matches the name of the Stimulus controller.

For example, to use the [Button Component](docs/components/button.md):

```html
<a href="#" data-controller="bridge--button">Button</a>
```

Each component can then be configured further. Check the [`docs/components/` directory](docs/components/) for more information.

### JavaScript events

A few controllers fire [custom JavaScript events](https://stimulus.hotwired.dev/reference/controllers#cross-controller-coordination-with-events) prefixed with the `bridge--` namespace. You can listen for these to perform custom handling when:

* A [search query](docs/components/search.md) is executed
* A [notification token](docs/components/notification-token.md) is retrieved
* A [location](docs/components/location.md) is retrieved
* A [barcode](docs/components/barcode-scanner.md) is scanned

## Need help?

If you need help installing, configuring, or using the components, feel free to [open a new discussion](https://github.com/joemasilotti/bridge-components/discussions/new?category=q-a) or [send me an email](mailto:joe@masilotti.com). I'd love to help!
