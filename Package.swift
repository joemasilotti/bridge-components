// swift-tools-version: 5.9

import PackageDescription

let package = Package(
    name: "BridgeComponents",
    defaultLocalization: "en",
    platforms: [
        .iOS(.v16)
    ],
    products: [
        .library(
            name: "BridgeComponents",
            targets: ["BridgeComponents"]
        )
    ],
    dependencies: [
        .package(
            url: "https://github.com/hotwired/hotwire-native-ios.git",
            .upToNextMinor(from: "1.2.0")
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
            path: "components",
            exclude: [
                "../examples/android",
                "../examples/rails",
                "../resources",
            ]
        )
    ]
)
