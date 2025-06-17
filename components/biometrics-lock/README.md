# Biometrics Lock Component

When enabled, locks the app when it is backgrounded until the user authenticates via biometrics.

![Biometrics Lock Component examples](/resources/screenshots/biometrics-lock.png)

First, add the following to *every page* of your application. This ensures the app correctly locks when it enters the background.

```html
<meta data-controller="bridge--biometrics-lock">
```

Enable the lock by calling `#enable`. For example, when the user signs in.

```html
<div data-controller="bridge--biometrics-lock">
  <button data-action="bridge--biometrics-lock#enable">
    Enable lock
  </button>
</div>
```

Disable the lock by calling `#disable`. For example, when the user signs out.

```html
<div data-controller="bridge--biometrics-lock">
  <button data-action="bridge--biometrics-lock#disable">
    Disable lock
  </button>
</div>
```

## Additional requirements

### iOS

To access the user's biometrics on iOS, add the `NSFaceIDUsageDescription` value to your Info.plist. More information can be found in the [documentation](https://developer.apple.com/documentation/localauthentication/logging-a-user-into-your-app-with-face-id-or-touch-id).

### Android

The following additional dependencies are required on Android:

1. `androidx.biometric:biometric`
1. `androidx.lifecycle:lifecycle-process`
