import HotwireNative
import UIKit

final class FormComponent: BridgeComponent {
    override class var name: String { "form" }

    private weak var submitBarButtonItem: UIBarButtonItem?
    private var viewController: UIViewController? {
        delegate.destination as? UIViewController
    }

    override func onReceive(message: Message) {
        guard let event = Event(rawValue: message.event) else { return }

        switch event {
        case .connect:
            addButton(via: message)
        case .enableSubmit:
            enableButton()
        case .disableSubmit:
            disableButton()
        }
    }

    private func addButton(via message: Message) {
        guard let data: MessageData = message.data() else { return }

        let action = UIAction { [unowned self] _ in
            reply(to: message.event)
        }

        let item = UIBarButtonItem(title: data.title, primaryAction: action)
        viewController?.navigationItem.rightBarButtonItem = item
        submitBarButtonItem = item
    }

    private func enableButton() {
        submitBarButtonItem?.isEnabled = true
    }

    private func disableButton() {
        submitBarButtonItem?.isEnabled = false
    }
}

private extension FormComponent {
    enum Event: String {
        case connect
        case enableSubmit
        case disableSubmit
    }
}

private extension FormComponent {
    struct MessageData: Decodable {
        let title: String
    }
}
