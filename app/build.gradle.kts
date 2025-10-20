plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.appgrupo9"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.appgrupo9"
        minSdk = 30
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
        compose = true
    }
}

dependencies {
    //Guia 10 Navegacion Estructura visual
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc02")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.compose.material3:material3:<última versión>")
    implementation("androidx.compose.material:material-icons-extended:<última versión>")
    implementation("com.google.android.gms:play-services-location:21.0.1")



    implementation("androidx.compose.material3:material3:<última versión>")
    implementation("androidx.compose.material:material-icons-extended:<última versión>")



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    /*implementation(libs.androidx.navigation.runtime.android) no toma */
    implementation(libs.androidx.compose.material3)

    // LiveData en Compose
    implementation("androidx.compose.runtime:runtime-livedata")
    // Adaptive layouts (opcional, tablets/foldables)
    implementation("androidx.compose.material3.adaptive:adaptive")
    // RxJava en Compose (opcional)
    implementation("androidx.compose.runtime:runtime-rxjava2")
    implementation(libs.androidx.compose.material3.window.size.class1)
    implementation(libs.androidx.compose.foundation.layout)
    implementation("androidx.compose.material3:material3:1.4.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // implementacion de  recursos nativos
    // ubicacion
    implementation("com.google.android.gms:play-services-location:21.0.1")
    // importacion
    implementation("androidx.compose.material:material-icons-extended:1.5.0")



}