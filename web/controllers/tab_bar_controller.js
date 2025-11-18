import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "tab-bar"
  static values = {
    tabs: Array
  }

  connect() {
    super.connect()

    const tabs = this.tabsValue
    this.send("connect", {tabs})
  }
}
