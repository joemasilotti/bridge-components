# Search Component

Displays a native search field that dispatches JavaScript events on query changes.

![Search Component examples](/resources/screenshots/search.png)

Add the bridge component markup:

```html
<div data-controller="bridge--search"></div>
```

Then listen for the `bridge--search:queried` JavaScript event. This is dispatched every time the user changes the query in the native search field.

```html
<div data-controller="my-search"
     data-action="bridge--search:queried@window->my-search#search">
</div>
```

```javascript
// my_search_controller.js

import { Controller } from "@hotwired/stimulus"

export default class extends Controller {
  search(event) {
    const query = event.detail.query
    console.debug(`User searched for '${query}'.`)
    // Perform filtering...
  }
}
```

See the example Rails app to see how client-side filtering can be done.
