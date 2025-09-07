import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "biometrics-lock"

  connect() {
    super.connect()
    this.send("reenable")
  }

  enable() {
    this.send("enable")
  }

  disable() {
    this.send("disable")
  }
}
