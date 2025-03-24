# Alert Component

Displays a native alert dialog to confirm an action.

When the native confirm button is tapped, the underlying HTML element will be clicked.

The dialog can be optionally customized.

![Alert Component examples](/resources/screenshots/alert.png)

## Title

Set the title of the alert with `data-bridge-title`. If not set, the `innerText` of the element will be used.

```erb
<%= link_to "Link title", "#", data: {
  controller: "bridge--alert",
  action: "bridge--alert#show",
  bridge_title: "Overridden Alert Title"
} %>
```

<details>
<summary>HTML version</summary>

```html
<a
  href="#"
  data-controller="bridge--alert"
  data-action="bridge--alert#show"
  data-bridge-title="Overridden Alert Title"
>
  Link title
</a>
```

</details>

## Description

An optional description can be set with `data-bridge-description`. This appears as smaller text below the title in the alert.

```erb
<%= link_to "Link title", "#", data: {
  controller: "bridge--alert",
  action: "bridge--alert#show",
  bridge_description: "Custom alert description."
} %>
```

<details>
<summary>HTML version</summary>

```html
<a
    href="#"
    data-controller="bridge--alert"
    data-action="bridge--alert#show"
    data-bridge-description="Custom alert description"
>Link title</a>
```

</details>

## Destructive

Set `data-bridge-destructive="true"` to show a red confirmation button on iOS.

```erb
<%= link_to "Delete this record?", "#", data: {
  controller: "bridge--alert",
  action: "bridge--alert#show",
  bridge_destructive: true
} %>
```

<details>
<summary>HTML version</summary>

```html
<a
    href="#"
    data-controller="bridge--alert"
    data-action="bridge--alert#show"
    data-bridge-destructive="true"
>Delete this record?</a>
```

</details>

## Confirm and dismiss button titles

Override the default button titles, OK and Cancel, by setting `data-bridge-confirm` and `data-bridge-dismiss`.

```erb
<%= link_to "Customized button titles", "#", data: {
  controller: "bridge--alert",
  action: "bridge--alert#show",
  bridge_confirm: "Confirm",
  bridge_dismiss: "Cancel"
} %>
```

<details>
<summary>HTML version</summary>

```html
<a
    href="#"
    data-controller="bridge--alert"
    data-action="bridge--alert#show"
    data-bridge-confirm="Confirm"
    data-bridge-dismiss="Cancel"
>Customized button titles</a>
```

</details>
