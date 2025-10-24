import AlertBridgeController from "./alert_controller.js"
import BarcodeScannerBridgeController from "./barcode_scanner_controller.js"
import BiometricsLockBridgeController from "./biometrics_lock_controller.js"
import ButtonBridgeController from "./button_controller.js"
import DocumentScannerBridgeController from "./document_scanner_controller.js"
import FormBridgeController from "./form_controller.js"
import HapticBridgeController from "./haptic_controller.js"
import LocationBridgeController from "./location_controller.js"
import MenuBridgeController from "./menu_controller.js"
import NFCBridgeController from "./nfc_controller.js"
import NotificationTokenBridgeController from "./notification_token_controller.js"
import PermissionsBridgeController from "./permissions_controller.js"
import ReviewPromptBridgeController from "./review_prompt_controller.js"
import SearchBridgeController from "./search_controller.js"
import ShareBridgeController from "./share_controller.js"
import ThemeBridgeController from "./theme_controller.js"
import ToastBridgeController from "./toast_controller.js"

export {
  AlertBridgeController,
  BarcodeScannerBridgeController,
  BiometricsLockBridgeController,
  ButtonBridgeController,
  DocumentScannerBridgeController,
  FormBridgeController,
  HapticBridgeController,
  LocationBridgeController,
  MenuBridgeController,
  NFCBridgeController,
  NotificationTokenBridgeController,
  PermissionsBridgeController,
  ReviewPromptBridgeController,
  SearchBridgeController,
  ShareBridgeController,
  ThemeBridgeController,
  ToastBridgeController
}

/** @type {import("@hotwired/stimulus").Definition[]} */
export const controllers = [
  { identifier: "bridge--alert",  controllerConstructor: AlertBridgeController },
  { identifier: "bridge--barcode-scanner",  controllerConstructor: BarcodeScannerBridgeController },
  { identifier: "bridge--biometrics-lock", controllerConstructor: BiometricsLockBridgeController },
  { identifier: "bridge--button", controllerConstructor: ButtonBridgeController },
  { identifier: "bridge--document-scanner", controllerConstructor: DocumentScannerBridgeController },
  { identifier: "bridge--form",   controllerConstructor: FormBridgeController },
  { identifier: "bridge--haptic", controllerConstructor: HapticBridgeController },
  { identifier: "bridge--location", controllerConstructor: LocationBridgeController },
  { identifier: "bridge--menu",   controllerConstructor: MenuBridgeController },
  { identifier: "bridge--nfc",   controllerConstructor: NFCBridgeController },
  { identifier: "bridge--notification-token",   controllerConstructor: NotificationTokenBridgeController },
  { identifier: "bridge--permissions", controllerConstructor: PermissionsBridgeController },
  { identifier: "bridge--review-prompt", controllerConstructor: ReviewPromptBridgeController },
  { identifier: "bridge--search", controllerConstructor: SearchBridgeController },
  { identifier: "bridge--share",  controllerConstructor: ShareBridgeController },
  { identifier: "bridge--theme",  controllerConstructor: ThemeBridgeController },
  { identifier: "bridge--toast",  controllerConstructor: ToastBridgeController }
]
