import { BridgeComponent } from "@hotwired/hotwire-native-bridge"
import { BridgeElement } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "menu"
  static targets = ["item"]

  connect() {
    const side = this.bridgeElement.bridgeAttribute("side") || "left"

    const items = this.itemTargets.map(target => {
      const element = new BridgeElement(target)
      return {
        title: element.title,
        iosImage: element.bridgeAttribute("ios-image"),
        androidImage: element.bridgeAttribute("android-image"),
      }
    })

    this.send("connect", {side, items}, message => {
      const item = this.itemTargets[message.data.index]
      new BridgeElement(item).click()
    })
  }
}
