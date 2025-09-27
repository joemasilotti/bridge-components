import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "share"

  connect() {
    super.connect()
    this.#addButton()
  }

  #addButton() {
    const element = this.bridgeElement
    const url = element.bridgeAttribute("url") || window.location.href
    this.send("connect", {url}, () => {})
  }
}
