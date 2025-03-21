# Permissions Component

> [!IMPORTANT]
> This is a paid component. Gain access to the source code by [purchasing a Pro license](https://buy.stripe.com/fZeaF6bn9b9d4Pm14b).

Gets the status of the user's granted permissions, like location and push notifications.

![Permissions Component examples](/resources/screenshots/permissions.png)

```html
<table data-controller="bridge--permissions">
  <thead>
    <tr>
      <th>Permission</th>
      <th>Status</th>
      <th>Code</th>
    </tr>
  </thead>

  <tbody>
    <tr>
      <th>Camera</th>
      <td data-bridge--permissions-target="cameraStatus"></td>
      <td data-bridge--permissions-target="cameraCode"></td>
    </tr>

    <tr>
      <th>Location</th>
      <td data-bridge--permissions-target="locationStatus"></td>
      <td data-bridge--permissions-target="locationCode"></td>
    </tr>

    <tr>
      <th>Notifications</th>
      <td data-bridge--permissions-target="notificationsStatus"></td>
      <td data-bridge--permissions-target="notificationsCode"></td>
    </tr>
  </tbody>
</table>
```

## Notification permission status

The following notification permission statuses are returned from the app. Use the status code to determine behavior in your app as needed.

|Text|Code|iOS status|Android status|
|---|---|---|---|
|authorized|1|`authorized`, `provisional`, or `ephemeral`|`PERMISSION_GRANTED`|
|denied|0|`denied` or `restricted`|`PERMISSION_DENIED`|
|not determined|-1|`notDetermined`|N/A|
|unknown|-2|(anything else)|N/A|

## Additional requirements

Note that statuses might not be accurate until you configure the additional [location requirements](/components/location/README.md) and additional [notification token requirements](/components/notification-token/README.md).
