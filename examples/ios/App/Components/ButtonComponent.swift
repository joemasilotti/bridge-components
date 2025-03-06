import HotwireNative
import UIKit

final class ButtonComponent: BridgeComponent {
    override class var name: String { "button" }

    override func onReceive(message: Message) {
        guard let viewController else { return }
        addButton(via: message, to: viewController)
    }

    private var viewController: UIViewController? {
        delegate.destination as? UIViewController
    }

    private func addButton(via message: Message, to viewController: UIViewController) {
        guard let data: MessageData = message.data() else { return }

        let image = UIImage(systemName: data.image)
        let action = UIAction { [unowned self] _ in
            self.reply(to: "connect")
        }
        let item = UIBarButtonItem(title: data.title, image: image, primaryAction: action)

        if data.side == "right" {
            viewController.navigationItem.rightBarButtonItem = item
        } else {
            viewController.navigationItem.leftBarButtonItem = item
        }
    }
}

private extension ButtonComponent {
    struct MessageData: Decodable {
        let title: String
        let image: String?
        let side: String
    }
}

private extension UIImage {
    convenience init?(systemName: String?) {
        if let systemName {
            self.init(systemName: systemName)
        }
        return nil
    }
}
