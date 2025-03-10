import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "review-prompt"

  prompt(event) {
    this.send("prompt")
  }
}
