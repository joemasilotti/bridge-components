import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "alert"
  #cancel = true

  show(event) {
    if (this.#cancel) {
      event.stopImmediatePropagation()
      event.preventDefault()
      this.#showAlert()
    }
  }

  #showAlert() {
    const element = this.bridgeElement

    const title = element.title || "Are you sure?"
    const description = element.bridgeAttribute("description")
    const destructive = element.bridgeAttribute("destructive") == "true"
    const confirm = element.bridgeAttribute("confirm") || "OK"
    const dismiss = element.bridgeAttribute("dismiss") || "Cancel"

    const data = {title, description, destructive, confirm, dismiss}
    this.send("show", data, () => {
      this.#cancel = false
      this.bridgeElement.click()
      this.#cancel = true
    })
  }
}
