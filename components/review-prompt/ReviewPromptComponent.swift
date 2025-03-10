import HotwireNative
import StoreKit

import struct HotwireNative.Message

class ReviewPromptComponent: BridgeComponent {
    override class var name: String { "review-prompt" }

    override func onReceive(message: Message) {
        if let scene = viewController?.view.window?.windowScene {
            AppStore.requestReview(in: scene)
        }
    }

    private var viewController: UIViewController? {
        delegate.destination as? UIViewController
    }
}
