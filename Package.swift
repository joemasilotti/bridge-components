// swift-tools-version: 5.9

import PackageDescription

let package = Package(
    name: "BridgeComponents",
    defaultLocalization: "en",
    platforms: [
        .iOS(.v16)
    ],
    products: [
        .library(name: "BridgeComponents", targets: ["BridgeComponents"])
    ],
    dependencies: [
        .package(
            url: "https://github.com/hotwired/hotwire-native-ios.git",
            revision: "e1026cbef049bb3e1bd296e13dc5a965746c9e82"
        )
    ],
    targets: [
        .target(
            name: "BridgeComponents",
            dependencies: [
                .product(
                    name: "HotwireNative",
                    package: "hotwire-native-ios"
                )
            ],
            path: "ios"
        )
    ]
)
