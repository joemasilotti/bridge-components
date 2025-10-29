import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "notification-token"
  static targets = ["token"]

  connect() {
    super.connect()
    this.send("connect")
  }

  get(event) {
    event.stopImmediatePropagation()
    event.preventDefault()
    this.#getToken()
  }

  #getToken() {
    this.send("get", {}, message => {
      const token = message.data.token
      const detail = {token}

      this.dispatch("retrieved", {detail})

      if (this.hasTokenTarget) {
        this.tokenTarget.innerText = `Your notification token is: ${token}.`
      }
    })
  }
}
