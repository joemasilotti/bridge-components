import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "toast"

  show(event) {
    event.stopImmediatePropagation()
    event.preventDefault()
    this.#showToast()
  }

  #showToast() {
    const message = this.bridgeElement.bridgeAttribute("message")
    this.send("show", {message}, () => {})
  }
}
