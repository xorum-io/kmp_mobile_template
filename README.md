## KMP Mobile Template

This is a template for the development of native iOS and Android apps with shared business-logic using [KMP](https://kotlinlang.org/lp/mobile/).

[Redux architecture](https://medium.com/xorum-io/redux-for-android-with-kotlin-in-practice-part-1-initial-setup-8f1a417d466e) is used to facilitate interactions between **Android / iOS** user-facing and **common** modules.

[XcodeGen](https://github.com/yonaskolb/XcodeGen) is used to generate `.xcodeproj` file from source files and `project.yml` file.

## To Do

Once cloned do the following do adapt template to your needs.

### Android (app and common folders)

1. Update `applicationId` in `build.gradle`.
2. Update `BACKEND_LINK` in `common/src/commonMain/kotlin/io/xorum/network/HttpClientFactory.kt`.
3. Rename package `io.xorum` in `app` and `common` modules.
4. Rename `cocoapods` and `xcode` in `common/build.gradle`.

### iOS (ios folder)

1. Install XcodeGen (`brew install xcodegen`).
2. Change all 3 folders prefixes from `KMP Mobile Template` to your project name.
3. Change all occurrences of `KMP Mobile Template` in `Podfile` to your project name.
4. Update `PRODUCT_BUNDLE_IDENTIFIER` in `project.yml`.
5. Give executable permission to `init.sh` (`chmod+x init.sh`).
6. Execute `./init.sh` every time you need updated `common` in iOS app.

## Release

1. Generate `app/app.keystore` with "app" key alias, and secure passwords (update `signingConfigs`).
2. Generate provisioning profiles, and include them in `project.yml`.