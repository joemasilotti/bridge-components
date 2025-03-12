import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "menu"
  static targets = ["item"]

  connect() {
    super.connect()
    this.#addMenuButton()
  }

  disconnect() {
    super.disconnect()
    this.#removeMenuButton()
  }

  #addMenuButton() {
    const items = this.itemTargets.map(target => {
      const element = new BridgeElement(target)
      return {
        title: element.title,
        iosImage: element.bridgeAttribute("ios-image"),
        androidImage: element.bridgeAttribute("android-image"),
      }
    })

    this.send("connect", {items}, message => {
      const item = this.itemTargets[message.data.index]
      new BridgeElement(item).click()
    })
  }

  #removeMenuButton() {
    this.send("disconnect")
  }
}
