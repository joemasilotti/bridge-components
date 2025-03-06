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

        let image = UIImage(systemName: data.image ?? "")
        let action = UIAction { [unowned self] _ in
            self.reply(to: "connect")
        }
        let item = UIBarButtonItem(title: data.title, image: image, primaryAction: action)
        viewController.navigationItem.rightBarButtonItem = item
    }
}

private extension ButtonComponent {
    struct MessageData: Decodable {
        let title: String
        let image: String?

        enum CodingKeys: String, CodingKey {
            case title
            case image = "iosImage"
        }
    }
}
