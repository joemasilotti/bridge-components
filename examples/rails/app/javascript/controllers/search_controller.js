import { Controller } from "@hotwired/stimulus"

export default class extends Controller {
  static targets = ["results"]

  static values = {
    items: Array
  }

  search(event) {
    const query = event.detail.query
    const items = this.itemsValue.filter(item =>
      item.toLowerCase().includes(query.toLowerCase())
    )
    this.#populateList(items)
  }

  #populateList(items) {
    this.resultsTarget.innerHTML = ""

    items.forEach(item => {
      const link = document.createElement("a")
      link.href = `/components/searches/${encodeURIComponent(item)}`
      link.className = "list-group-item list-group-item-action";
      link.textContent = item;
      this.resultsTarget.appendChild(link);
    })
  }
}
