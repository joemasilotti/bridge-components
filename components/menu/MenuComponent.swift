import HotwireNative
import UIKit

class MenuComponent: BridgeComponent {
    override class var name: String { "menu" }

    private var viewController: UIViewController? {
        delegate.destination as? UIViewController
    }

    override func onReceive(message: Message) {
        guard let event = Event(rawValue: message.event) else { return }

        switch event {
        case .connect:
            addMenuButton(via: message)
        }
    }

    private func addMenuButton(via message: Message) {
        guard let data: MessageData = message.data() else { return }

        var actions = [UIAction]()
        for (index, item) in data.items.enumerated() {
            let image = UIImage(systemName: item.image ?? "")
            let action = UIAction(title: item.title, image: image) { [unowned self] _ in
                reply(to: message.event, with: SelectionMessageData(index: index))
            }
            actions.append(action)
        }

        let button = UIBarButtonItem(
            title: "Menu",
            image: UIImage(systemName: "line.3.horizontal"),
            menu: UIMenu(children: actions)
        )

        if data.side == "right" {
            viewController?.navigationItem.rightBarButtonItem = button
        } else if data.side == "left" {
            // Only add button on first screen so it doesn't conflict with the back button.
            if viewController?.navigationController?.viewControllers.count == 1 {
                viewController?.navigationItem.leftBarButtonItem = button
            }
        }
    }
}

private extension MenuComponent {
    enum Event: String {
        case connect
    }
}

private extension MenuComponent {
    struct MessageData: Decodable {
        let items: [Item]
        let side: String
    }

    struct Item: Decodable {
        let title: String
        let image: String?

        enum CodingKeys: String, CodingKey {
            case title
            case image = "iosImage"
        }
    }

    struct SelectionMessageData: Encodable {
        let index: Int
    }
}
