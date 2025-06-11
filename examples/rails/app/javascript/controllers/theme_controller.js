import { Controller } from "@hotwired/stimulus"

export default class extends Controller {
  connect() {
    const theme = this.#getCookie("theme")

    if (theme) {
      document.body.setAttribute("data-bs-theme", theme)
      this.element.setAttribute("data-bridge--theme-theme-value", theme)
    }
  }

  dark(event) {
    event.preventDefault()
    event.stopImmediatePropagation()

    this.#setCookie("theme", "dark")
    document.body.setAttribute("data-bs-theme", "dark")
    this.element.setAttribute("data-bridge--theme-theme-value", "dark")
  }

  light(event) {
    event.preventDefault()
    event.stopImmediatePropagation()

    this.#setCookie("theme", "light")
    document.body.setAttribute("data-bs-theme", "light")
    this.element.setAttribute("data-bridge--theme-theme-value", "light")
  }

  #setCookie(name, value) {
    const expires = new Date(Date.now() + 30 * 864e5).toUTCString()
    document.cookie = `${name}=${value}; path=/; expires=${expires}`
  }

  #getCookie(name) {
    return document.cookie
      .split("; ")
      .find(row => row.startsWith(name + "="))
      ?.split("=")[1]
  }
}
