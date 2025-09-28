plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.my_movie_list"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.my_movie_list"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true // Keep if you use ViewBinding alongside Compose
        compose = true
    }
}

dependencies {

    // Specify the Compose BOM with a valid version
    val composeBom = platform("androidx.compose:compose-bom:2024.05.00")
    implementation(composeBom)
    // No, we don't implement the BOM for test and androidTest directly like this usually.
    // The test and androidTest dependencies should pick up versions from the BOM if they are Compose libraries.
    // implementation(testImplementation(composeBom)) // Incorrect
    // implementation(androidTestImplementation(composeBom)) // Incorrect

    // Corrected aliases based on your libs.versions.toml
    implementation(libs.ui) // Correct: from ui = { module = "androidx.compose.ui:ui" }
    implementation(libs.androidx.ui.tooling.preview) // Correct: from androidx-ui-tooling-preview
    implementation(libs.androidx.material3) // Correct: from androidx-material3
    implementation(libs.androidx.activity.compose) // Correct: from androidx-activity-compose - ENSURE VERSION IS IN TOML
    implementation(libs.androidx.lifecycle.viewmodel.compose) // Correct: from androidx-lifecycle-viewmodel-compose - ENSURE VERSION IS IN TOML
    debugImplementation(libs.androidx.ui.tooling) // Correct: from androidx-ui-tooling
    debugImplementation(libs.androidx.ui.test.manifest) // Correct: from androidx-ui-test-manifest

    implementation(libs.foundation) // Correct: from foundation = { module = "androidx.compose.foundation:foundation" }
    implementation(libs.coil.compose)

    // Test dependencies for Compose - ensure these pick versions from BOM or have explicit versions
    testImplementation(platform(composeBom)) // Include BOM for test scope
    testImplementation(libs.androidx.ui.test.junit4) // Correct: from androidx-ui-test-junit4

    androidTestImplementation(platform(composeBom)) // Include BOM for androidTest scope
    androidTestImplementation(libs.androidx.ui.test.junit4) // Often ui-test-junit4 is used here too, or a specific espresso-compose integration
    // androidTestImplementation(libs.androidx.ui.test) // This is also available if you need the core ui-test for instrumentation tests

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.adapter.rxjava3)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.converter.scalars) // You had this in TOML, so I assume it's used
    implementation(libs.logging.interceptor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
