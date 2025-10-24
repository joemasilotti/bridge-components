import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "barcode-scanner"
  static targets = ["result"]

  scan(event) {
    event.stopImmediatePropagation()
    event.preventDefault()
    this.#scan()
  }

  #scan() {
    this.resultTarget.innerHTML = ""

    this.send("scan", {}, message => {
      const barcode = message.data.barcode
      const detail = {barcode}

      this.dispatch("scanned", {detail})

      if (this.hasResultTarget) {
        this.resultTarget.innerHTML = `You scanned <code>${barcode}</code>.`
      }
    })
  }
}
