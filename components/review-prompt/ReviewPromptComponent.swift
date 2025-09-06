import HotwireNative
import StoreKit

import struct HotwireNative.Message

public final class ReviewPromptComponent: BridgeComponent {
    override public class var name: String { "review-prompt" }

    private var viewController: UIViewController? {
        delegate?.destination as? UIViewController
    }

    override public func onReceive(message: Message) {
        guard let event = Event(rawValue: message.event) else { return }

        switch event {
        case .prompt:
            promptForReview()
        }
    }

    private func promptForReview() {
        if let scene = viewController?.view.window?.windowScene {
            AppStore.requestReview(in: scene)
        }
    }
}

private extension ReviewPromptComponent {
    enum Event: String {
        case prompt
    }
}
