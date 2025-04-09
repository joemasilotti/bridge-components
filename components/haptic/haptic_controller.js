import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "haptic"

  vibrate() {
    const element = this.bridgeElement
    const feedback = element.bridgeAttribute("feedback") || "success"
    this.send("vibrate", {feedback})
  }
}
