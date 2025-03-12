import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "review-prompt"

  prompt(event) {
    event.stopImmediatePropagation()
    event.preventDefault()
    this.#promptForReview()
  }

  #promptForReview() {
    this.send("prompt")
  }
}
