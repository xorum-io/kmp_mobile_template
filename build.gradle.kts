buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://dl.bintray.com/soywiz/soywiz")
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://dl.bintray.com/xorum-io/ReKamp")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.31")
        classpath("co.touchlab:kotlinxcodesync:0.1.2")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.google.com/")
        maven(url = "https://dl.bintray.com/xorum-io/ReKamp")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
