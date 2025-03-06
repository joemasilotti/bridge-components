# Form Component

Replaces a form's submit `<button>` with a native one that disables during submission.

The title of the native button will get set from the HTML `<button>`'s `innerText`. Override this by setting `data-bridge-title`, as shown below.

Make sure to wire up the two `data-action` attributes to ensure the button disables while the form submission is in progress.

![Form Component examples](screenshot.png)

```erb
<%= form_with model: @model, data: {
  controller: "bridge--form",
  action: "turbo:submit-start->bridge--form#submitStart turbo:submit-end->bridge--form#submitEnd"
} do |form| %>
  <%# ... %>

  <%= form.submit "Submit form", data: {
    bridge__form_target: "submit",
    bridge_title: "Submit"
  } %>
<% end %>
```

## Hide the HTML submit button

Hide the HTML submit button when the "form" component is registered with the following CSS.

```css
[data-bridge-components~="form"]
[data-controller~="bridge--form"]
[type="submit"] {
  display: none;
}
```
