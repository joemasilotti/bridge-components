import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "search"

  connect() {
    super.connect()

    this.send("connect", {}, (message) => {
      const query = message.data.query
      const detail = {query}

      this.dispatch("queried", {detail})
    })
  }
}
