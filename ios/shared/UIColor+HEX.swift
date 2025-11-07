import UIKit

extension UIColor {
    convenience init?(hex: String?) {
        guard let hex else { return nil }

        var hexString = hex.trimmingCharacters(in: .whitespacesAndNewlines)
        if hexString.hasPrefix("#") {
            hexString.removeFirst()
        }

        var hexNumber: UInt64 = 0
        guard
            Scanner(string: hexString).scanHexInt64(&hexNumber),
            hexString.count == 6
        else { return nil }

        let r = CGFloat((hexNumber & 0xFF0000) >> 16) / 255
        let g = CGFloat((hexNumber & 0x00FF00) >> 8) / 255
        let b = CGFloat(hexNumber & 0x0000FF) / 255
        self.init(red: r, green: g, blue: b, alpha: 1.0)
    }
}
