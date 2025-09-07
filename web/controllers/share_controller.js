import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "share"

  connect() {
    super.connect()
    this.#addButton()
  }

  #addButton() {
    const url = window.location.href
    this.send("connect", {url}, () => {})
  }
}
