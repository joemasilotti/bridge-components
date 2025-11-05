import UIKit

extension UIColor {
    convenience init?(hex: String?) {
        guard let hex else { return nil }

        var hexString = hex.trimmingCharacters(in: .whitespacesAndNewlines)
        if hexString.hasPrefix("#") {
            hexString.removeFirst()
        }

        var hexNumber: UInt64 = 0
        guard Scanner(string: hexString).scanHexInt64(&hexNumber) else { return nil }

        let r, g, b, a: CGFloat
        switch hexString.count {
        case 6:
            r = CGFloat((hexNumber & 0xFF0000) >> 16) / 255
            g = CGFloat((hexNumber & 0x00FF00) >> 8) / 255
            b = CGFloat(hexNumber & 0x0000FF) / 255
            a = 1.0
        case 8:
            r = CGFloat((hexNumber & 0xFF000000) >> 24) / 255
            g = CGFloat((hexNumber & 0x00FF0000) >> 16) / 255
            b = CGFloat((hexNumber & 0x0000FF00) >> 8) / 255
            a = CGFloat(hexNumber & 0x000000FF) / 255
        default:
            return nil
        }

        self.init(red: r, green: g, blue: b, alpha: a)
    }
}
