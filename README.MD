#  LOL(League of Legends) Android App

Show list of champions of the game of League of Legends game and detaill.

## 🏗️ Architecture Overview

LolChampions follows **Clean Architecture** principles with modern Android development practices:

- **MVVM Pattern** with Jetpack Compose for reactive UI
- **Hilt** for dependency injection
- **Repository Pattern** for data abstraction
- **Kotlin Coroutines + Flow** for asynchronous programming
- **Multi-module architecture** supporting Google Play and Huawei AppGallery

### 🔐 Authentication & User Management
- **Firebase Authentication
- **Multi-user type support** (google account, register account)

### Core Technologies
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern declarative UI toolkit
- **Android Architecture Components** - Lifecycle-aware components
- **Material Design 3** - Modern design system
- **Hilt** - Dependency injection framework

### Networking & Data
- **Retrofit** - HTTP client for API communication
- **OkHttp** - Network interceptor and logging
- **Moshi** - JSON serialization/deserialization
- **Kotlin Flows** - Reactive data streams
- **DataStore Preferences** - Local data persistence

### Media & UI
- **Coil** - Image loading and caching
- **QR Kit** - QR code generation and scanning

### Platform Services
- **Firebase** (Google variant) - Analytics and crash reporting


### API Endpoints

**Development Environment:**
- Main API: `https://ddragon.leagueoflegends.com/cdn/16.2.1/data/en_US/champion.json`
- Same endpoint structure as production
- Additional debugging and logging enabled
- Networking: `/` root endpoint
- Authentication: `/` root endpoint

## 🐛 Debugging & Development Tools

### Testing Strategy
- **Unit Tests** - Business logic and data transformation
- **Integration Tests** - API communication and data flow
- **UI Tests** - Compose UI components and user interactions
- **End-to-End Tests** - Complete user workflows

