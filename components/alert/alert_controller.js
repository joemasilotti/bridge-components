import { BridgeComponent } from "@hotwired/hotwire-native-bridge"
import { BridgeElement } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "alert"
  #stopImmediatePropagation = true

  show(event) {
    if (this.#stopImmediatePropagation) {
      event.stopImmediatePropagation()
      this.#showAlert()
    }
  }

  #showAlert() {
    const element = this.bridgeElement
    const data = {
      title: element.title,
      description: element.bridgeAttribute("description"),
      destructive: element.bridgeAttribute("destructive") == "true",
      confirm: element.bridgeAttribute("confirm"),
      dismiss: element.bridgeAttribute("dismiss")
    }

    this.send("show", data, () => {
      this.#stopImmediatePropagation = false
      this.bridgeElement.click()
      this.#stopImmediatePropagation = true
    })
  }
}
