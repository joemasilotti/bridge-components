# Toast Component

Render a native, floating message that disappears after a few seconds.

Uses a native `Toast` on Android and a custom implementation on iOS. Feel free to customize `ToastComponent.swift` to match your design.

![Toast Component examples](/resources/screenshots/toast.png)

```erb
<%= link_to "Show a toast", "#", data: {
  controller: "bridge--toast",
  action: "bridge--toast#show",
  bridge_message: "This is a toast message."
} %>
```

<details>
<summary>HTML version</summary>
```html
<a
  href="#"
  data-controller="bridge--toast"
  data-action="bridge--toast#show"
  data-bridge-message="This is a toast message."
>
  Show a toast
</a>
```
</details>
