import AlertBridgeController from "../components/alert/alert_controller.js"
import BarcodeScannerBridgeController from "../components/barcode-scanner/barcode_scanner_controller.js"
import BiometricsLockBridgeController from "../components/biometrics-lock/biometrics_lock_controller.js"
import ButtonBridgeController from "../components/button/button_controller.js"
import DocumentScannerBridgeController from "../components/document-scanner/document_scanner_controller.js"
import FormBridgeController from "../components/form/form_controller.js"
import HapticBridgeController from "../components/haptic/haptic_controller.js"
import LocationBridgeController from "../components/location/location_controller.js"
import MenuBridgeController from "../components/menu/menu_controller.js"
import NotificationTokenBridgeController from "../components/notification-token/notification_token_controller.js"
import PermissionsBridgeController from "../components/permissions/permissions_controller.js"
import ReviewPromptBridgeController from "../components/review-prompt/review_prompt_controller.js"
import SearchBridgeController from "../components/search/search_controller.js"
import ShareBridgeController from "../components/share/share_controller.js"
import ThemeBridgeController from "../components/theme/theme_controller.js"
import ToastBridgeController from "../components/toast/toast_controller.js"

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
  { identifier: "bridge--notification-token",   controllerConstructor: NotificationTokenBridgeController },
  { identifier: "bridge--permissions", controllerConstructor: PermissionsBridgeController },
  { identifier: "bridge--review-prompt", controllerConstructor: ReviewPromptBridgeController },
  { identifier: "bridge--search", controllerConstructor: SearchBridgeController },
  { identifier: "bridge--share",  controllerConstructor: ShareBridgeController },
  { identifier: "bridge--theme",  controllerConstructor: ThemeBridgeController },
  { identifier: "bridge--toast",  controllerConstructor: ToastBridgeController }
]
