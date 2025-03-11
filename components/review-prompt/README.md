# Review Prompt Component

Prompts the user for a review on the App Store on iOS and Google Play on Android.

Note that you will only see the review prompt UI on Android when running an app downloaded from Google Play.

Also note that the prompt might not appear every time. More information can be found on the [Apple Developer](https://developer.apple.com/design/human-interface-guidelines/ratings-and-reviews) and [Google Developer](https://developer.android.com/guide/playcore/in-app-review) sites.

![Review Prompt screenshots](screenshot.png)

```erb
<%= link_to "Prompt for review", "#", data: {
  controller: "bridge--review-prompt",
  action: "bridge--review-prompt#prompt"
} %>
```
