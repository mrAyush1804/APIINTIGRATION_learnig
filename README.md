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

