import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "button"

  connect() {
    super.connect()
    this.#notifyBridgeOfConnect()
  }

  #notifyBridgeOfConnect() {
    const element = this.bridgeElement
    const image = element.bridgeAttribute("ios-image")
    const side = element.bridgeAttribute("side") || "right"
    this.send("connect", {title: element.title, image, side}, () => {
      this.element.click()
    })
  }
}
