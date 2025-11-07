import HotwireNative
import UIKit

public final class ShareComponent: BridgeComponent {
    override public nonisolated class var name: String { "share" }

    override public func onReceive(message: Message) {
        guard let event = Event(rawValue: message.event) else { return }

        switch event {
        case .connect:
            addButton(via: message)
        }
    }

    private func addButton(via message: Message) {
        guard let data: MessageData = message.data(), let url = data.url else { return }

        let action = UIAction { [weak self] _ in
            self?.share(url)
        }

        let button = UIBarButtonItem(
            title: "Share",
            image: UIImage(systemName: "square.and.arrow.up"),
            primaryAction: action
        )
        button.tintColor = Bridgework.color("Share", hex: data.colorCode)
        
        viewController?.navigationItem.rightBarButtonItem = button
    }

    private func share(_ url: URL) {
        let activityViewController = UIActivityViewController(
            activityItems: [url],
            applicationActivities: nil
        )
        viewController?.present(activityViewController, animated: true)
    }
}

private extension ShareComponent {
    enum Event: String {
        case connect
    }
}

private extension ShareComponent {
    struct MessageData: Decodable {
        let urlString: String
        let colorCode: String?

        var url: URL? {
            URL(string: urlString)
        }

        enum CodingKeys: String, CodingKey {
            case urlString = "url"
            case colorCode = "color"
        }
    }
}
