import HotwireNative
import UIKit

extension BridgeComponent {
    var viewController: UIViewController? {
        delegate?.destination as? UIViewController
    }
}
