# Haptic Component

Vibrate the device via the haptic engine.

This component requires a physical device. Android requires Android 11 or later.

![Haptic Component examples](/resources/screenshots/haptic.png)

```html
<button data-controller="bridge--haptic" data-action="bridge--haptic#vibrate">
  Vibrate
</button>
```

## Haptic options

Optionally, set `data-bridge-feedback` to set different haptic effects.

|Value|[iOS haptic](https://developer.apple.com/documentation/uikit/uinotificationfeedbackgenerator)|[Android haptic](https://developer.android.com/reference/kotlin/android/view/HapticFeedbackConstants)|
|---|---|---|
|`success` (or empty)|`.success`|`.CONFIRM`|
|`warning`|`.warning`|`.REJECT`|
|`error`|`.error`|`.REJECT`|
