# Geofence Tracker

An Android application built with modern development tools to create, manage, and track geofences. The app monitors user transitions into and out of defined geographical areas and logs the duration of each visit.

## 🚀 Features

- **Interactive Map:** View and create geofences directly on a Google Map interface.
- **Geofence Management:** Add, remove, and list geofences with custom names and radii.
- **Visit Tracking:** Automatically logs entry and exit times when the user enters or leaves a geofence.
- **Visit History:** View detailed logs of all visits, including duration and timestamps.
- **Background Monitoring:** Uses Android's Geofencing API for efficient background tracking.

## 🛠 Tech Stack

- **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) for a modern, declarative UI.
- **Language:** [Kotlin](https://kotlinlang.org/)
- **Architecture:** Clean Architecture with MVI/MVVM patterns.
- **Dependency Injection:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Database:** [Room](https://developer.android.com/training/data-storage/room) for local persistent storage.
- **Maps:** [Google Maps SDK for Android](https://developers.google.com/maps/documentation/android-sdk/overview) & [Maps Compose](https://github.com/googlemaps/android-maps-compose).
- **Location:** [Google Play Services Location](https://developers.google.com/android/guides/setup) (Geofencing API).
- **Navigation:** [Jetpack Compose Navigation](https://developer.android.com/jetpack/compose/navigation) with Type-safe routing.
- **Asynchronous Work:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html).

## 🏗 Project Structure

The project follows Clean Architecture principles:
- **`domain`**: Contains business logic, models (`Geofence`, `Visit`), and repository interfaces.
- **`data`**: Implementation of repositories, Room database, and local data sources.
- **`presentation`**: UI layer containing Compose screens, ViewModels, and navigation logic.
- **`di`**: Dependency injection modules.

## ⚙️ Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/kuntalavi/Geofence.git
   ```

2. **Google Maps API Key:**
   This project requires a Google Maps API Key.
   - Go to the [Google Cloud Console](https://console.cloud.google.com/).
   - Create a project and enable the **Maps SDK for Android**.
   - Create an API Key.
   - Add the key to your `local.properties` file:
     ```properties
     GOOGLE_MAPS_API_KEY=YOUR_API_KEY_HERE
     ```

3. **Build & Run:**
   Open the project in Android Studio and run it on a physical device (recommended for testing geofences) or an emulator with Play Services.

## 📸 Screenshots

| Geofence List | Map View | Visit History |
| :---: | :---: | :---: |
| _Placeholder_ | _Placeholder_ | _Placeholder_ |

## 🛡 Permissions

The app requires the following permissions to function correctly:
- `ACCESS_FINE_LOCATION`
- `ACCESS_COARSE_LOCATION`
- `ACCESS_BACKGROUND_LOCATION` (Required for tracking while the app is in the background)
- `POST_NOTIFICATIONS` (For entry/exit alerts)
