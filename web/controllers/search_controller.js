import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "search"

  static values = {
    items: Array
  }

  connect() {
    super.connect()

    this.#populateList(this.itemsValue)

    this.send("connect", {}, (message) => {
      const query = message.data.query
      const items = this.itemsValue.filter(item =>
        item.toLowerCase().includes(query.toLowerCase())
      )
      this.#populateList(items)
    })
  }

  // An example search function.
  // Replace with your own implementation or call out to an external API.
  #populateList(items) {
    this.element.innerHTML = ""

    items.forEach(item => {
      const link = document.createElement("a")
      link.href = `/components/searches/${encodeURIComponent(item)}`
      link.className = "list-group-item list-group-item-action";
      link.textContent = item;
      this.element.appendChild(link);
    })
  }
}
