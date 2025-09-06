import AlertBridgeController from "../components/alert/alert_controller.js"
import ButtonBridgeController from "../components/button/button_controller.js"
import FormBridgeController from "../components/form/form_controller.js"
import HapticBridgeController from "../components/haptic/haptic_controller.js"
import MenuBridgeController from "../components/menu/menu_controller.js"
import SearchBridgeController from "../components/search/search_controller.js"
import ShareBridgeController from "../components/share/share_controller.js"
import ThemeBridgeController from "../components/theme/theme_controller.js"
import ToastBridgeController from "../components/toast/toast_controller.js"

export {
  AlertBridgeController,
  ButtonBridgeController,
  FormBridgeController,
  HapticBridgeController,
  MenuBridgeController,
  SearchBridgeController,
  ShareBridgeController,
  ThemeBridgeController,
  ToastBridgeController
}

/** @type {import("@hotwired/stimulus").Definition[]} */
export const controllers = [
  { identifier: "bridge--alert",  controllerConstructor: AlertBridgeController },
  { identifier: "bridge--button", controllerConstructor: ButtonBridgeController },
  { identifier: "bridge--form",   controllerConstructor: FormBridgeController },
  { identifier: "bridge--haptic", controllerConstructor: HapticBridgeController },
  { identifier: "bridge--menu",   controllerConstructor: MenuBridgeController },
  { identifier: "bridge--search", controllerConstructor: SearchBridgeController },
  { identifier: "bridge--share",  controllerConstructor: ShareBridgeController },
  { identifier: "bridge--theme",  controllerConstructor: ThemeBridgeController },
  { identifier: "bridge--toast",  controllerConstructor: ToastBridgeController }
]
