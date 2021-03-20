plugins {
    id("kotlin-multiplatform")
    id("com.android.application")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "io.xorum.android"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        create("release") {
            keyAlias = "app"
            keyPassword = "password"
            storeFile = file("app.keystore")
            storePassword = "password"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
        }
        getByName("debug") {
            // matchingFallbacks = ["release"]
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
    }
    lintOptions {
        isCheckReleaseBuilds = false
        isAbortOnError = false
    }
    packagingOptions {
        exclude("**/*.kotlin_module")
        exclude("META-INF/*.kotlin_module")
    }
    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
            res.srcDir("src/main/res")
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common"))
            }
        }

        /*androidMain {

        }*/
    }

    android {

    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}")

    // Material design
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.google.android.material:material:1.3.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")

    // Ktor
    implementation("io.ktor:ktor-client-json-jvm:${Versions.ktor}")
    implementation("io.ktor:ktor-client-android:${Versions.ktor}")

    // Tests
    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}
