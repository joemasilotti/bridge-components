import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

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

    this.send("connect", {title: submit.title}, () => {
      this.submitTarget.click()
    })
  }

  #removeButton() {
    this.send("disconnect")
  }
}
