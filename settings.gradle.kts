rootProject.name = "kmp_mobile_template"

val buildAndroid = extra["org.gradle.project.buildAndroid"].toString().toBoolean()
if (buildAndroid) {
    include("app")
}

include("common")
