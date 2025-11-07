import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "button"

  connect() {
    super.connect()
    this.#addButton()
  }

  disconnect() {
    super.disconnect()
    this.#removeButton()
  }

  #addButton() {
    const element = this.bridgeElement
    const side = element.bridgeAttribute("side") || "right"
    const iosImage = element.bridgeAttribute("ios-image")
    const androidImage = element.bridgeAttribute("android-image")
    const color = element.bridgeAttribute("color")
    const data = {title: element.title, iosImage, androidImage, color}

    this.send(side, data, () => {
      this.element.click()
    })
  }

  #removeButton() {
    this.send("disconnect")
  }
}
