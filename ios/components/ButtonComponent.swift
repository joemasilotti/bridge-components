import HotwireNative
import UIKit

public final class ButtonComponent: BridgeComponent {
    override public nonisolated class var name: String { "button" }

    override public func onReceive(message: Message) {
        guard let event = Event(rawValue: message.event) else { return }

        switch event {
        case .left, .right:
            addButton(via: message, side: event)
        case .disconnect:
            removeButton()
        }
    }

    private func addButton(via message: Message, side: Event) {
        guard let data: MessageData = message.data() else { return }

        let image = UIImage(systemName: data.image ?? "")
        let action = UIAction { [weak self] _ in
            self?.reply(to: message.event)
        }
        let item = UIBarButtonItem(title: data.title, image: image, primaryAction: action)
        item.tintColor = Bridgework.color("Button", hex: data.colorCode)

        switch side {
        case .left:
            viewController?.navigationItem.leftItemsSupplementBackButton = true
            viewController?.navigationItem.leftBarButtonItem = item
        case .right:
            viewController?.navigationItem.rightBarButtonItem = item
        default:
            return
        }
    }
    
    private func removeButton() {
        guard let navItem = viewController?.navigationItem else { return }
        navItem.leftBarButtonItem = nil
        navItem.rightBarButtonItem = nil
    }
}

private extension ButtonComponent {
    enum Event: String {
        case left
        case right
        case disconnect
    }
}

private extension ButtonComponent {
    struct MessageData: Decodable {
        let title: String
        let image: String?
        let colorCode: String?

        enum CodingKeys: String, CodingKey {
            case title
            case image = "iosImage"
            case colorCode = "color"
        }
    }
}
