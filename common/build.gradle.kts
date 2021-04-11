plugins {
    id("kotlin-multiplatform")
    id("kotlinx-serialization")
    id("org.jetbrains.kotlin.native.cocoapods")
    id("co.touchlab.kotlinxcodesync")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}")
                implementation("io.ktor:ktor-client-core:${Versions.ktor}")
                implementation("io.ktor:ktor-client-serialization:${Versions.ktor}")
                implementation("io.ktor:ktor-client-logging:${Versions.ktor}")

                api("io.xorum:ReKamp:${Versions.reKamp}")
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

        val jvmMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
                implementation("io.ktor:ktor-client-logging-jvm:${Versions.ktor}")
                api("io.xorum:ReKamp-jvm:${Versions.reKamp}")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}")
                implementation("org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}")
                implementation("junit:junit:4.13.1")
            }
        }
    }

    targets {
        val buildForDevice = project.findProperty("kotlin.native.cocoapods.target") == "ios_arm"
        if (buildForDevice) {
            iosArm64("iOS64")
            iosArm32("iOS32")

            sourceSets["iOS64Main"].dependsOn(sourceSets["iOSMain"])
            sourceSets["iOS64Main"].dependencies { api("io.xorum:ReKamp-iosArm64:${Versions.reKamp}") }

            sourceSets["iOS32Main"].dependsOn(sourceSets["iOSMain"])
            sourceSets["iOS32Main"].dependencies { api("io.xorum:ReKamp-iosArm32:${Versions.reKamp}") }
        } else {
            iosX64("iosX")

            sourceSets["iosXMain"].dependsOn(sourceSets["iOSMain"])
            sourceSets["iosXMain"].dependencies { api("io.xorum:ReKamp-iosX64:${Versions.reKamp}") }
        }
    }

    cocoapods {
        summary = "KMP Mobile Template common module"
        homepage = "https://github.com/xorum-io/kmp_mobile_template"
    }
}

xcode {
    projectPath = "../ios/KMP Mobile Template.xcodeproj"
    target = "KMP Mobile Template"
}
