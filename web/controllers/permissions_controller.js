import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "permissions"
  static targets = [
    "cameraStatus", "cameraCode",
    "locationStatus", "locationCode",
    "notificationsStatus", "notificationsCode"
  ]

  connect() {
    super.connect()

    this.#getCamera()
    this.#getLocation()
    this.#getNotifications()
  }

  #getCamera() {
    this.send("camera", {}, message => {
      const {code, text} = message.data
      this.cameraStatusTarget.innerText = text
      this.cameraCodeTarget.innerHTML = `<code>${code}</code>`
    })
  }

  #getLocation() {
    this.send("location", {}, message => {
      const {code, text} = message.data
      this.locationStatusTarget.innerText = text
      this.locationCodeTarget.innerHTML = `<code>${code}</code>`
    })
  }

  #getNotifications() {
    this.send("notifications", {}, message => {
      const {code, text} = message.data
      this.notificationsStatusTarget.innerText = text
      this.notificationsCodeTarget.innerHTML = `<code>${code}</code>`
    })
  }
}
