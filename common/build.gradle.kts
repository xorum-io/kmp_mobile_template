plugins {
    id("kotlin-multiplatform")
    id("kotlinx-serialization")
    id("org.jetbrains.kotlin.native.cocoapods")
    id("co.touchlab.kotlinxcodesync")
    id("com.android.library")
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}")
                implementation("io.ktor:ktor-client-core:${Versions.ktor}")
                implementation("io.ktor:ktor-client-serialization:${Versions.ktor}")
                implementation("io.ktor:ktor-client-logging:${Versions.ktor}")

                api("io.xorum:ReKamp-kotlinMultiplatform:${Versions.reKamp}")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}")
            }
        }

        val iOSMain by creating {
            dependencies {
                implementation("io.ktor:ktor-client-ios:${Versions.ktor}")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
                implementation("io.ktor:ktor-client-logging-jvm:${Versions.ktor}")
            }
        }

        val androidTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}")
                implementation("org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}")
                implementation("junit:junit:4.13.2")
            }
        }
    }

    targets {
        val buildForDevice = project.findProperty("kotlin.native.cocoapods.target") == "ios_arm"
        if (buildForDevice) {
            iosArm32("iOS32")
            sourceSets["iOS32Main"].dependsOn(sourceSets["iOSMain"])

            iosArm64("iOS64")
            sourceSets["iOS64Main"].dependsOn(sourceSets["iOSMain"])
        } else {
            iosX64("iosX")
            sourceSets["iosXMain"].dependsOn(sourceSets["iOSMain"])
        }
    }

    cocoapods {
        summary = "KMP Mobile Template common module"
        homepage = "https://github.com/xorum-io/kmp_mobile_template"
    }
}

android {
    compileSdkVersion(31)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(31)
    }
}

xcode {
    projectPath = "../ios/KMP Mobile Template.xcodeproj"
    target = "KMP Mobile Template"
}
