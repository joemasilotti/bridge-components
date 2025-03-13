# Notification Token Component

> [!WARNING]
> The Android version of this component is still in development.

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

|Text|Code|[iOS status](https://developer.apple.com/documentation/usernotifications/unauthorizationstatus)|[Android status](TODO)|
|---|---|---|---|
|not determined|-1|`notDetermined`|TODO|
|denied|0|`denied`|TODO|
|authorized|1|`authorized`, `provisional`, or `ephemeral`|TODO|
|unknown|-2|(anything else)|TODO|

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

TODO
