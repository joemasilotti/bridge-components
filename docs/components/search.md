# Search Component

Displays a native search field that passes along queries to JavaScript for filtering logic.

![Search Component examples](/resources/screenshots/search.png)

Note that all filtering is implemented in the bridge component. When copy-pasting that file into your codebase, replace with your own implementation or call out to an external API if needed.

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
