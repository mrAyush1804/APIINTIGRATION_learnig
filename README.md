# Chucker Integration in Android

## Introduction
Chucker is an open-source HTTP inspector library for Android. It helps in debugging API requests and responses directly within the app, without the need for external tools like Postman or Charles Proxy.

## Features
- View API requests and responses in-app.
- Inspect headers, bodies, and status codes.
- Monitor network performance.
- Debugging without writing extra logs.
- Works in development mode and can be disabled in production.

## Installation
Add the following dependencies in your `build.gradle` (Module: app):

```gradle
dependencies {
    // Chucker library for debug build
    implementation("com.github.chuckerteam.chucker:library:4.1.0")
    
    // Chucker for release build (no-op version to disable it in production)
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.1.0")
}
```

## How to Use
### Step 1: Add Chucker Interceptor to OkHttpClient
Modify your `OkHttpClient` setup to include Chucker Interceptor:

```kotlin
val client = OkHttpClient.Builder()
    .addInterceptor(ChuckerInterceptor.Builder(context).build())
    .build()
```

### Step 2: Inject OkHttpClient into Retrofit
If you're using Retrofit for API calls:

```kotlin
val retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
```

### Step 3: Run the App
Once integrated, make API calls from your app, and Chucker will log all network requests and responses. You can view them in a notification or within the app.

## Advantages
✅ Helps debug network requests easily.
✅ No need for external tools like Postman.
✅ Provides structured API response logs.
✅ Safe for production (no-op version available).

## Disadvantages
❌ Adds extra overhead in debug builds.
❌ Not useful for WebSocket-based real-time communication.
❌ Limited to Android devices only.

## Conclusion
Chucker is a powerful tool for debugging network requests in Android apps. It simplifies the debugging process and enhances productivity by providing a clear and structured API log directly within the app.


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


# Debugging Libraries for Android

## Overview
This document provides an overview of various debugging libraries available for Android development. It includes their features, use cases, advantages, disadvantages, and official documentation links.

| Library Name | Features | Use Case | Advantages | Disadvantages | Documentation Link |
|-------------|----------|----------|------------|---------------|------------------|
| **Chucker** | API request/response logs, JSON formatting, error tracking | API debugging, response monitoring | Simple UI, In-app debugging, No need for logcat | Increases app size (Exclude in release) | [Chucker GitHub](https://github.com/ChuckerTeam/chucker) |
| **Stetho (by Facebook)** | Network inspection, SQLite DB inspection, View hierarchy debugging | Deep app debugging with Chrome DevTools | Works with Chrome DevTools, Powerful | Setup is a bit complex | [Stetho GitHub](https://github.com/facebook/stetho) |
| **Flipper (by Meta/Facebook)** | Network logs, Database, Shared Preferences, Layout Inspection | Large scale apps, Facebook-based projects | Highly customizable, Supports plugins | Slightly heavy in size | [Flipper GitHub](https://github.com/facebook/flipper) |
| **Timber** | Advanced logging, Debugging logs | Custom logging solution for Android | Lightweight, Easy to integrate | No UI debugging, Just logs | [Timber GitHub](https://github.com/JakeWharton/timber) |
| **LeakCanary** | Memory leak detection, Heap analysis | Detect memory leaks in Android apps | Automated detection, Detailed leak reports | Only for memory leaks, Not network logs | [LeakCanary GitHub](https://square.github.io/leakcanary/) |
| **Hyperion** | UI debugging, Network logs, Shared preferences viewer | UI debugging, App testing | Supports multiple plugins, Developer friendly | Can slow down app if misused | [Hyperion GitHub](https://github.com/willowtreeapps/Hyperion-Android) |
| **OkHttp Logging Interceptor** | Logs API requests and responses | Simple API request/response logging | Lightweight, Directly integrates with OkHttp | No UI, Just logs in Logcat | [OkHttp GitHub](https://square.github.io/okhttp/) |

## Installation & Usage Guide

### **1. Chucker**
```gradle
// Add dependency
implementation 'com.github.chuckerteam.chucker:library:4.1.0'
releaseImplementation 'com.github.chuckerteam.chucker:library-no-op:4.1.0'
```
Usage: Chucker automatically logs all API requests and responses. Open the app and navigate to Chucker UI to view logs.

### **2. Stetho**
```gradle
// Add dependency
implementation 'com.facebook.stetho:stetho:1.6.0'
```
Setup: Follow the [official setup guide](https://github.com/facebook/stetho) to integrate it with Chrome DevTools.

### **3. Flipper**
```gradle
// Add dependencies
implementation 'com.facebook.flipper:flipper:0.125.0'
implementation 'com.facebook.soloader:soloader:0.10.5'
```
Setup: Follow the [official guide](https://fbflipper.com/docs/getting-started/android/) to configure it.

### **4. Timber**
```gradle
// Add dependency
implementation 'com.jakewharton.timber:timber:5.0.1'
```
Usage:
```kotlin
Timber.plant(Timber.DebugTree())
Timber.d("This is a debug log")
```

### **5. LeakCanary**
```gradle
// Add dependency
implementation 'com.squareup.leakcanary:leakcanary-android:2.10'
```
Usage: It automatically detects memory leaks and provides a notification with leak details.

### **6. Hyperion** (Use in Debug Mode Only)
```gradle
debugImplementation 'com.willowtreeapps.hyperion:hyperion-core:0.9.34'
debugImplementation 'com.willowtreeapps.hyperion:hyperion-shared-preferences:0.9.34'
```
Usage: Access debugging tools directly in the app UI.

### **7. OkHttp Logging Interceptor**
```gradle
// Add dependency
implementation 'com.squareup.okhttp3:logging-interceptor:4.10.0'
```
Usage:
```kotlin
val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()
```

## Conclusion
These debugging tools help in different aspects of Android development. Choose the best one based on your use case:
- **For API Debugging:** Chucker, OkHttp Logging Interceptor
- **For UI Debugging:** Hyperion
- **For Memory Leak Detection:** LeakCanary
- **For Network & Database Inspection:** Stetho, Flipper
- **For Logging:** Timber

For large-scale applications, **Flipper** is highly recommended due to its plugin support and extensive debugging features.



