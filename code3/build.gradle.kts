// 单模块 Gradle Android 工程：根工程即 app 模块。
// 注意：AGP 9 已内置 Kotlin 支持，无需再单独应用 org.jetbrains.kotlin.android 插件。
plugins {
    id("com.android.application") version "9.3.2"
}

android {
    namespace = "com.example.code3"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.code3"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }

    // 开启 DataBinding（activity_main5.xml 里的 <layout> / <variable> / @{} 需要它）
    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.8.0")
    implementation("androidx.core:core-ktx:1.19.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // ViewModel / LiveData / viewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // ---- 网络请求：Retrofit + OkHttp + Gson ----
    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    implementation("com.squareup.retrofit2:converter-gson:2.10.0")
    // RxJava2 适配器（课程中「RxJava 版本」的接口返回 RxJava 的 Observable）
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.10.0")
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
}
