import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "theme"

  static values = {
    theme: String
  }

  connect() {
    super.connect()
    this.#setTheme()
  }

  themeValueChanged() {
    this.#setTheme()
  }

  #setTheme() {
    const theme = this.themeValue
    this.send("connect", { theme })
  }
}
