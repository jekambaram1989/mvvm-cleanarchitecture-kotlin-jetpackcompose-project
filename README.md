# MVVM-Clean Architecture-Kotlin-Jetpack Compose-Project

### About the Project
mvvm-clean-architecture-kotlin-jetpack-compose project that presents a modern approach to Android app development. The project tries to combine popular Android tools and to demonstrate best development practices by utilizing up-to-date tech stacks like Compose, Kotlin, Dagger, Navigation and Retrofit. The app layers its presentation through the MVVM and Clean Architecture presentation pattern. 

### Application Screenshots
![Image](https://github.com/user-attachments/assets/a8fa6b2d-39b0-4006-93ea-02e5108c3750)
![Image](https://github.com/user-attachments/assets/59f9016c-e88e-4193-b93f-8e292b6688a9)
![Image](https://github.com/user-attachments/assets/fbfeaf1d-6731-42e9-9877-ab5b86bae470)

## MVVM (Model-View-ViewModel) Clean architecture 
Clean architecture pattern has been used in the development of this application. The development language of the application is Kotlin. Clean Architecture is a design approach that separates concerns, promoting maintainability and testability. Combining MVVM with Clean Architecture helps organize an Android app into layers like 
   * Presentation Layer 
   * Domain Layer  
   * Data Layer
     
which making it easier to develop and test.

## Modularization 
Modularization in Android refers to the process of dividing an application into distinct, self-contained, and reusable modules. Each module represents a specific feature, layer, or functionality of the app. This approach improves code organization, scalability, and maintainability while enabling collaborative development and faster build times.

## Unit Testing
#### JUnit
JUnit is used for unit testing of UI components. Tests are written to verify the behavior and interactions of UI elements and view models. Mocking frameworks may also be used to isolate components for testing.

#### Mockito
Mockito is used for unit testing the API and network-related components. It allows you to create mock objects and simulate the behavior of external dependencies to ensure that your API interactions are tested independently.

## Tech Stacks
- [Kotlin](https://developer.android.com/kotlin) - The futuristic programming language that can run on JVM! It's the officially supported programming language for Android Studio and the community is
  moving rapidly from Java to Kotlin.
- [Android KTX](https://developer.android.com/kotlin/ktx.html) - Concise and idiomatic Kotlin library to Jetpack and Android platform APIs. It's like magic!
- [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) -The ViewModel class is like a superhero that stores and manages UI-related data in a lifecycle conscious way!
- [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) - A concurrency design pattern that simplifies async code execution on Android. It's like a secret weapon!
- [Retrofit](https://square.github.io/retrofit) - The superhero REST Client for Java and Android by Square inc under Apache 2.0 license. It's a simple network library used for network transactions and
  capturing JSON response from web services.
- [GSON](https://github.com/square/gson) - The superhero JSON Parser that understands Kotlin non-nullable and default parameters. It's like a genius!
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - A dependency injection library for Android that reduces the boilerplate of manual dependency injection. It's like a
  magic wand!
- [Navigation Components](https://developer.android.com/guide/navigation/navigation-getting-started) - The superhero that helps you implement navigation from simple button clicks to more complex
  patterns. It's like a GPS for your app!
- [Logging Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - The superhero interceptor that logs HTTP request and response data. It's like a CCTV for
  network transactions!
- [Mockito Kotlin](https://github.com/mockito/mockito-kotlin) - Feeling tired of writing boring and tedious unit tests? Fear not, for Mockito Kotlin is here to save the day! This powerful library
  allows you to mock objects and functions in a breeze, so you can focus on writing awesome code that blows everyone's minds.
- [OkHttp3](https://github.com/square/okhttp) - Looking for a reliable and efficient HTTP client for your app? Look no further than OkHttp3! This battle-tested library is used by some of the largest
  apps out there, and for good reason: it's fast, reliable, and easy to use.
- [Coil](https://github.com/coil-kt/coil) - Tired of dealing with slow and clunky image loading libraries? Say goodbye to your troubles and hello to Coil! This sleek and powerful library makes
  loading images a breeze, with an intuitive API and impressive performance.
- [Material Compose](https://github.com/material-components/material-components-android-compose) - Want to make your app look and feel like a
  million bucks? Material Compose is the answer! This library provides a range of customizable components and widgets that will make your app look sleek and polished, with an intuitive API that makes it
  easy to get started. Whether you're building a small app or a massive enterprise system, Material Compose has everything you need to take your UI to the next level.
