# DevPortal KMP

This library provides a module-style UI for Android and iOS app developers. It is useful for adding
development tools to your app in development builds.

Built with Kotlin Multiplatform and Compose Multiplatform, targeting Android and iOS.

# Quick Start

Add maven repository.

```kotlin
repositories {
    maven {
        url = uri("https://mokelab.github.io/DevPoralKMP/repo")
    }
}
```

or copy `docs/repo` directory to your project and add maven repository.

```kotlin
repositories {
    maven {
        url = uri("path/to/repo")
    }
}
```

Add dependency. This library uses Koin for dependency injection.

```kotlin
dependencies {
    debugImplementation("com.mokelab.devportal.kmp:devportal:$version")
}
```

### Android

Add Activity to your `AndroidManifest.xml`. It is recommended to add `src/debug/AndroidManifest.xml`
and add this Activity only for debug build.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:tools="http://schemas.android.com/tools"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <application tools:ignore="MissingApplicationIcon">
        <activity android:name="com.mokelab.devportal.kmp.DevPortalActivity"
            android:exported="true" android:label="DevPortal" android:taskAffinity=".devportal">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

Set `android:exported="true"` and `android:taskAffinity=".devportal"` to run DevPortalActivity in a
separate task. It is useful to keep DevPortalActivity alive when the main app is running.

Start Koin in your Application class and include `devPortalModule`.

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(devPortalModule, appKoinModule)
        }
    }
}
```

### iOS

Call `DevPortalViewController()` from Swift to present the DevPortal UI.

```swift
import LibDevportal

let vc = DevPortalViewController()
present(vc, animated: true)
```

Start Koin before presenting the view controller.

```kotlin
// In your KMP shared code (e.g. AppKoinModule.kt)
fun initKoin() {
    startKoin {
        modules(devPortalModule, appKoinModule)
    }
}
```

# Usage

`devportal` module requires one or more `DevPortalFeature` to show in the UI.

You can create your own feature by implementing the `DevPortalFeature` interface and registering it
with Koin.

# How to add your own DevPortalFeature

Add a KMP library module (or shared module) to your project.

Add `com.mokelab.devportal.kmp:api:$version` to dependencies.

```kotlin
dependencies {
    implementation("com.mokelab.devportal.kmp:api:$version")
}
```

Implement `DevPortalFeature`.

```kotlin
object MyRoute

class MyFeature : DevPortalFeature {
    override val name: String = "My Feature"
    override val root: Any = MyRoute
    override val installer: EntryProviderScope<Any>.() -> Unit = {
        entry<MyRoute> {
            MyScreen()
        }
    }
}
```

Then implement `MyScreen` as a Composable function.

Register the feature with Koin.

```kotlin
val appKoinModule = module {
    single<DevPortalFeature> { MyFeature() }
}
```

Koin collects all `DevPortalFeature` instances automatically, so you don't need any additional wiring
after registering the module.

If your current work is done and you don't need to show this feature in DevPortal, you can remove
its Koin module. The feature will automatically disappear from DevPortal.

# License

Apache License Version 2.0
