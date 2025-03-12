import HotwireNative
import UIKit

final class ToastComponent: BridgeComponent {
    override class var name: String { "toast" }

    private var viewController: UIViewController? {
        delegate.destination as? UIViewController
    }

    override func onReceive(message: Message) {
        guard let event = Event(rawValue: message.event) else { return }

        switch event {
        case .show:
            showToast(via: message)
        }
    }

    private func showToast(via message: Message) {
        guard let data: MessageData = message.data(), let viewController else { return }

        let toast = makeLabel(text: data.message)
        viewController.view.addSubview(toast)
        constrainToast(toast, in: viewController.view)
        animateToastInAndOut(toast)
    }

    private func makeLabel(text: String) -> UILabel {
        let label = UILabel()
        label.text = text
        label.textAlignment = .center
        label.backgroundColor = UIColor.black.withAlphaComponent(0.7)
        label.textColor = .white
        label.font = UIFont.systemFont(ofSize: 14)
        label.layer.cornerRadius = 10
        label.clipsToBounds = true
        label.numberOfLines = 0
        label.alpha = 0
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }

    private func constrainToast(_ toast: UILabel, in view: UIView) {
        NSLayoutConstraint.activate([
            toast.leadingAnchor.constraint(greaterThanOrEqualTo: view.leadingAnchor, constant: 16),
            toast.trailingAnchor.constraint(lessThanOrEqualTo: view.trailingAnchor, constant: -16),
            toast.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            toast.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: -50),
            toast.widthAnchor.constraint(equalTo: view.widthAnchor, multiplier: 0.9),
            toast.heightAnchor.constraint(greaterThanOrEqualToConstant: 40)
        ])
    }

    private func animateToastInAndOut(_ toast: UILabel) {
        UIView.animate(withDuration: 0.5, animations: {
            toast.alpha = 1
        }) { _ in
            UIView.animate(withDuration: 0.5, delay: 2, options: .curveEaseOut, animations: {
                toast.alpha = 0
            }) { _ in
                toast.removeFromSuperview()
            }
        }
    }
}

private extension ToastComponent {
    enum Event: String {
        case show
    }
}

private extension ToastComponent {
    struct MessageData: Decodable {
        let message: String
    }
}
