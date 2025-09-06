import { BridgeComponent } from "@hotwired/hotwire-native-bridge"

export default class extends BridgeComponent {
  static component = "document-scanner"
  static targets = ["result"]

  scan(event) {
    event.stopImmediatePropagation()
    event.preventDefault()
    this.#scan()
  }

  #scan() {
    this.resultTarget.innerHTML = ""

    this.send("scan", {}, message => {
      const imageData = message.data.imageData
      const blob = this.#base64ToBlob(imageData.trim(), "image/jpeg")
      const blobUrl = URL.createObjectURL(blob)

      const img = document.createElement("img")
      img.src = blobUrl
      this.resultTarget.appendChild(img)

      // Free up memory.
      img.onload = () => URL.revokeObjectURL(blobUrl)
    })
  }

  // Base64-encoded images can be too big for the web view. Blobs are smaller.
  #base64ToBlob(base64, mimeType) {
    const byteCharacters = atob(base64)
    const byteNumbers = new Array(byteCharacters.length)
    for (let i = 0; i < byteCharacters.length; i++) {
      byteNumbers[i] = byteCharacters.charCodeAt(i)
    }

    const byteArray = new Uint8Array(byteNumbers)
    return new Blob([byteArray], {type: mimeType})
  }
}
