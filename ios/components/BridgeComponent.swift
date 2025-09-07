import HotwireNative
import UIKit

extension BridgeComponent {
    public static var all = [
        AlertComponent.self,
        ButtonComponent.self,
        FormComponent.self,
        HapticComponent.self,
        MenuComponent.self,
        ReviewPromptComponent.self,
        SearchComponent.self,
        ShareComponent.self,
        ThemeComponent.self,
        ToastComponent.self
    ]

    var viewController: UIViewController? {
        delegate?.destination as? UIViewController
    }
}
