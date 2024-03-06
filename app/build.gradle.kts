
import org.jetbrains.kotlin.gradle.plugin.* // kaptExtension

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    //id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.apitvshowmvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.apitvshowmvvm"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildFeatures{

        dataBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //RETROFIT AND GSON
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //PICASSO
    implementation ("com.squareup.picasso:picasso:2.8")
    //LifeCycle Extensions
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")





    //ROOM & RxJava Support
    implementation ("androidx.room:room-runtime:2.2.5")
    annotationProcessor("androidx.room:room-compiler:2.2.5")
    implementation("androidx.room:room-rxjava2:2.2.5")

    // kapt("androidx.room:room-compiler:2.2.5") // Anotaciones de Room
    //ksp("androidx.room:room-compiler:2.6.1")



    //RxJava
    implementation("io.reactivex.rxjava2:rxandroid:2.0.1")

    //Scalable Size Units
    implementation("com.intuit.sdp:sdp-android:1.0.6")
    implementation("com.intuit.ssp:ssp-android:1.0.6")

    //Rounded Image View
    implementation ("com.makeramen:roundedimageview:2.3.0")

    // para by vieModels()
    implementation ("androidx.activity:activity-ktx:1.3.1")
    // para corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.6")

}

