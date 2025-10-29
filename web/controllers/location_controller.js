import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "location"
  static targets = ["result"]

  get(event) {
    event.stopImmediatePropagation()
    event.preventDefault()
    this.#getLocation()
  }

  #getLocation() {
    this.send("get", {}, message => {
      const {latitude, longitude} = message.data

      if (latitude === undefined || longitude === undefined) {
        this.dispatch("error")

        if (this.hasResultTarget) {
          this.resultTarget.innerText = `Your location could not be found. Did you grant location permission?`
        }
      } else {
        this.dispatch("retrieved", {detail: {latitude, longitude}})

        if (this.hasResultTarget) {
          this.resultTarget.innerText = `Your location is ${latitude}, ${longitude}.`
        }
      }
    })
  }
}
