import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "nfc"
  static targets = ["value", "result"]

  read(event) {
    event.stopImmediatePropagation()
    event.preventDefault()
    this.#read()
  }

  write(event) {
    event.stopImmediatePropagation()
    event.preventDefault()
    this.#write()
  }

  #read() {
    this.resultTarget.innerHTML = ""

    this.send("read", {}, message => {
      const type = message.data.type
      const value = message.data.value
      this.resultTarget.innerHTML = `You scanned a <code>${type}</code> value:<br><code>${value}</code>`
    })
  }

  #write() {
    const value = this.valueTarget.value
    this.send("write", {value})
  }
}
