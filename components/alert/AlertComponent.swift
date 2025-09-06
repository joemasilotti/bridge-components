import HotwireNative
import UIKit

public final class AlertComponent: BridgeComponent {
    override public class var name: String { "alert" }

    private var viewController: UIViewController? {
        delegate?.destination as? UIViewController
    }

    override public func onReceive(message: Message) {
        guard let event = Event(rawValue: message.event) else { return }

        switch event {
        case .show:
            presentAlert(via: message)
        }
    }

    private func presentAlert(via message: Message) {
        guard let data: MessageData = message.data() else { return }

        let alert = UIAlertController(
            title: data.title,
            message: data.description,
            preferredStyle: .alert
        )

        alert.addAction(
            UIAlertAction(
                title: data.confirm,
                style: data.confirmActionStyle
            ) { [unowned self] _ in
                reply(to: message.event)
            }
        )

        alert.addAction(
            UIAlertAction(
                title: data.dismiss,
                style: .cancel
            ) { _ in }
        )

        viewController?.present(alert, animated: true)
    }
}

private extension AlertComponent {
    enum Event: String {
        case show
    }
}

private extension AlertComponent {
    struct MessageData: Decodable {
        let title: String
        let description: String?
        let destructive: Bool
        let confirm: String
        let dismiss: String

        var confirmActionStyle: UIAlertAction.Style {
            destructive ? .destructive : .default
        }
    }
}
