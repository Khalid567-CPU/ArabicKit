plugins {
    id("com.android.application") version "9.3.0" apply false
    id("com.android.library") version "9.3.0" apply false
}

tasks.register("verifyFoundation") {
    group = "verification"
    description = "Runs ArabicKit unit tests, Android lint, and the sample debug build."
    dependsOn(
        ":arabickit:testDebugUnitTest",
        ":arabickit:lintDebug",
        ":sample:assembleDebug",
        ":sample:lintDebug",
    )
}
