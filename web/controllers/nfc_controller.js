import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "nfc"
  static targets = ["result"]

  scan(event) {
    event.stopImmediatePropagation()
    event.preventDefault()
    this.#scan()
  }

  #scan() {
    this.resultTarget.innerHTML = ""

    this.send("scan", {}, message => {
      const type = message.data.type
      const value = message.data.value
      this.resultTarget.innerHTML = `You scanned a <code>${type}</code> value:<br><code>${value}</code>`
    })
  }
}
