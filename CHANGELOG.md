# Changelog

## v0.13.1

* Fix menu component not removing nav bar button on controller disconnect

## v0.13.0

* Lower minimum iOS version from 16.0 to 15.6
* Use `SKStoreReviewController.requestReview(in:)` on iOS 15, `AppStore.requestReview(in:)` on iOS 16+

## v0.12.2

* Fix type mismatch for destructive menu items

## v0.12.1

* Add option for destructive menu items
* Fix button component not removing nav bar button on controller disconnect

## v0.12.0

* Customize colors on Button, Menu, and Share components
* Add ERB example to docs

## v0.11.1

* Add NFC tag writing support

## v0.11.0

* Add NFC Component
* Fire JavaScript events on barcode scan, location retrieve, and notification token
* Migrate Search Component to JavaScript events

## v0.10.1

* Fix duplicate Menu buttons
* Restore existing search query/state on back navigation

## v0.10.0

* Namespace components and emit structured events
* Prepare for Swift 6 concurrency rules

### Breaking changes

Components are now namespaced under `bridge--`. Update your `data-controller` attributes accordingly.

## v0.9.2

* Expand Search Component documentation
* Persist query when navigating back to search
* Add optional placeholder for search bar

## v0.9.1

* Option to customize URL when sharing

## v0.9.0

* Set color of buttons based on theme

## v0.8.3

* Fix exports for esbuild

## v0.8.2

* Align package name with artifact
* Update installation instructions for Android

## v0.8.0

* Android package via JitPack

## v0.7.0

* Reorganize project structure to better support packages

## v0.6.2

* Official [NPM package](https://www.npmjs.com/package/@joemasilotti/bridge-components)

## v0.6.0

* `ButtonComponent` now supports buttons on both the left and right side.
* Official [Swift package](https://swiftpackageindex.com/joemasilotti/bridge-components)

### Breaking changes

The `ButtonComponent` no longer sends the "connect" message event. It now sends "left" and "right" to support adding buttons to both sides of the screen.
