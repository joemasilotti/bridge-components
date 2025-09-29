# Search Component

Displays a native search field that passes along queries to JavaScript for filtering logic.

![Search Component examples](/resources/screenshots/search.png)

Note that by default all filtering is implemented in the Stimulus controller. See below to search via a network request.

```erb
<%= tag.div data: {
  controller: "bridge--search",
  "bridge--search-items-value": ["One", "Two", "Three"].to_json
} %>
```

<details>
<summary>HTML version</summary>

```html
<div
  data-controller="bridge--search"
  data-bridge--search-items-value="[&quot;One&quot;,&quot;Two&quot;,&quot;Three&quot;]"
></div>
```
</details>

## Customize search behavior

You'll most likely want to implement your own Stimulus controller to interact with this component:

```javascript
// app/javascript/controllers/bridge/search_controller.js

import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "search"
  static targets = ["input"]

  connect() {
    super.connect()

    // Optional placeholder text.
    const placeholder = this.inputTarget.placeholder
    this.send("connect", {placeholder}, (message) => {
      const query = message.data.query
      this.inputTarget.value = query

      // Submit form on every keystroke.
      this.element.requestSubmit()
    })
  }
}
```

```erb
<%= form_with url: "/search/results", method: :get, "data-controller": "bridge--search" do |form| %>
  <%= form.search_field :query, "data-bridge--search-target": "input", placeholder: "Search..." %>
  <%= form.submit "Search" %>
<% end %>
```

<details>
<summary>HTML version</summary>

```html
<form action="/search/results" method="get" data-controller="bridge--search">
  <input type="search" name="query" data-bridge--search-target="input" placeholder="Search..." name="query">
  <input type="submit" data-bridge--search-target="submit" value="Search">
</form>
```

</details>
