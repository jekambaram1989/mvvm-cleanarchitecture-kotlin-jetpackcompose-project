plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.app.news.feature.news"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:network"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    //Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material)
    //Navigation
    implementation(libs.androidx.navigation)
    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)
    //Coroutine
    implementation(libs.kotlinx.coroutines.android)
    //Serializable
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    testImplementation(libs.org.mockito)
    testImplementation(libs.org.jetbrains.kotlinx)
    testImplementation(libs.androidx.arch.core)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}