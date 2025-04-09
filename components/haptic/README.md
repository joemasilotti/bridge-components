
# Haptic Component

Allows you to access the haptics engine. Easily extend for your own needs.

```eruby
  <%= link_to "Vibrate", "#", class: "btn btn-lg btn-outline-primary", data: {
    bridge_feedback: 'success',
    controller: "bridge--haptic",
    action: "bridge--haptic#vibrate"
  } %>
```
