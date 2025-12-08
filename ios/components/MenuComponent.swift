import HotwireNative
import UIKit

public final class MenuComponent: BridgeComponent {
    override public nonisolated class var name: String { "menu" }

    override public func onReceive(message: Message) {
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
            let attributes: UIMenuElement.Attributes = item.isDestructive ? .destructive : []
            let action = UIAction(title: item.title, image: image, attributes: attributes) { [weak self] _ in
                self?.reply(to: message.event, with: SelectionMessageData(index: index))
            }
            actions.append(action)
        }

        let button = UIBarButtonItem(
            title: "Menu",
            image: UIImage(systemName: "ellipsis"),
            menu: UIMenu(children: actions)
        )
        button.tintColor = Bridgework.color("Menu", hex: data.colorCode)

        viewController?.navigationItem.rightBarButtonItem = button
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
        let colorCode: String?

        enum CodingKeys: String, CodingKey {
            case items
            case colorCode = "color"
        }
    }

    struct Item: Decodable {
        let title: String
        let image: String?
        var isDestructive: Bool { destructive ?? false }

        private let destructive: Bool?

        enum CodingKeys: String, CodingKey {
            case title
            case image = "iosImage"
            case destructive
        }
    }

    struct SelectionMessageData: Encodable {
        let index: Int
    }
}
