import HotwireNative
import UIKit

public final class ButtonComponent: BridgeComponent {
    override public class var name: String { "button" }

    private var viewController: UIViewController? {
        delegate?.destination as? UIViewController
    }

    override public func onReceive(message: Message) {
        guard let event = Event(rawValue: message.event) else { return }

        switch event {
        case .connect:
            addButton(via: message)
        }
    }

    private func addButton(via message: Message) {
        guard let data: MessageData = message.data() else { return }

        let image = UIImage(systemName: data.image ?? "")
        let action = UIAction { [unowned self] _ in
            self.reply(to: message.event)
        }
        let item = UIBarButtonItem(title: data.title, image: image, primaryAction: action)
        viewController?.navigationItem.rightBarButtonItem = item
    }
}

private extension ButtonComponent {
    enum Event: String {
        case connect
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
