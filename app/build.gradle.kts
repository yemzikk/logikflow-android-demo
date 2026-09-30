plugins {
	alias(libs.plugins.android.application)
}

android {
	namespace = "com.logikflow.demo.recipes"
	compileSdk = 36

	defaultConfig {
		applicationId = "com.logikflow.demo.recipes"
		minSdk = 24
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"
	}

	buildFeatures {
		viewBinding = true
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
}

dependencies {
	implementation(libs.androidx.appcompat)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.recyclerview)
	implementation(libs.material)

	testImplementation(libs.junit)
}
