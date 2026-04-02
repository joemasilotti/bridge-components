import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "share"

  static values = {
    hidden: Boolean
  }

  connect() {
    super.connect()
    if (!this.hiddenValue) {
      this.#addButton()
    }
  }

  share() {
    const url = this.bridgeElement.bridgeAttribute("url") || window.location.href
    this.send("share", {url}, () => {})
  }

  #addButton() {
    const element = this.bridgeElement
    const url = element.bridgeAttribute("url") || window.location.href
    const color = element.bridgeAttribute("color")
    this.send("connect", {url, color}, () => {})
  }
}
