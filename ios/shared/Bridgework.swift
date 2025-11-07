import HotwireNative
import UIKit

public enum Bridgework {}

public extension Bridgework {
    static let coreComponents = [
        AlertComponent.self,
        ButtonComponent.self,
        FormComponent.self,
        HapticComponent.self,
        MenuComponent.self,
        ReviewPromptComponent.self,
        SearchComponent.self,
        ShareComponent.self,
        ThemeComponent.self,
        ToastComponent.self,
    ]

    static func color(_ named: String, hex: String? = nil) -> UIColor {
        UIColor(hex: hex) ??
            UIColor(named: "Bridgework\(named)Color") ??
            UIColor(named: "BridgeworkColor") ??
            UIColor.tintColor
    }
}
