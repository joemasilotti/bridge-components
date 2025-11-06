import { BridgeComponent } from "@hotwired/hotwire-native-bridge"
import { BridgeElement } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "form"
  static targets = ["submit"]

  connect() {
    super.connect()
    this.#addButton()
  }

  disconnect() {
    super.disconnect()
    this.#removeButton()
  }

  submitStart(event) {
    this.submitTarget.disabled = true
    this.send("disableSubmit")
  }

  submitEnd(event) {
    this.submitTarget.disabled = false
    this.send("enableSubmit")
  }

  #addButton() {
    const submit = new BridgeElement(this.submitTarget)
    const color = this.bridgeElement.bridgeAttribute("color")

    this.send("connect", {title: submit.title, color}, () => {
      this.submitTarget.click()
    })
  }

  #removeButton() {
    this.send("disconnect")
  }
}
