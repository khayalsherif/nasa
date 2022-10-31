# Nasa
This is a sample nasa list android application built to demonstrate use of *Clean Architecture* tools.

## - How to build on your environment
Add your API key in **local.properties** file.
```xml
nasa_api_key = YOUR_API_KEY
```
If you don't have nasa api key you can create new key from [here](https://api.nasa.gov/).

## - Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [Flow](https://developer.android.com/kotlin/flow) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Koin ](https://insert-koin.io/) - Dependency Injection Framework
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [OkHttp](http://square.github.io/okhttp/) - HTTP client that's efficient by default: HTTP/2 support allows all requests to the same host to share a socket
- [Coil](https://coil-kt.github.io/coil/) - image loading framework for Android
- [Gson](https://github.com/google/gson) - used to convert Java Objects into their JSON representation and vice versa.
- [Mockito](http://site.mockito.org/) - Most popular mocking framework for Java/kotlin.
- [Junit](https://developer.android.com/training/testing/local-tests) - A unit test verifies the behavior of a small section of code, the unit under test. It does so by executing that code and checking the result.


## - Clean Architecture

<center><img width="200" height="200" src="https://koenig-media.raywenderlich.com/uploads/2019/06/Android-Clean-Architecture.png"><p>- photo by: <a href="https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started">raywenderlich</a></p></center>

### What is clean architecture?
Architecture means the overall design of the project. It's the organization of the code into classes or files or components or modules. And it's how all these groups of code relate to each other. The architecture defines where the application performs its core functionality and how that functionality interacts with things like the database and the user interface.

### Layers
- **Domain** - Would execute business logic which is independent of any layer and is just a pure kotlin/java package with no android specific dependency.
- **Data** - Would dispense the required data for the application to the domain layer by implementing interface exposed by the domain.
- **Presentation / framework** - Would include both domain and data layer and is android specific which executes the UI logic.

## - Modules of App
  ### App 
  It uses the all the components and class releated to Android Framework. It gets the data from presentation layer and shows on UI.

  ### Data 
  The Data layer is our access point to external data layers and is used to fetch data from multiple sources (the cache and remote in our case).

  ### Domain
  The domain layer responsibility is to simply contain the UseCase instance used to retrieve data from the Data layer and pass it onto the Presentation layer. 
  
  ### Presentation
  This layer's responsibility is to handle the presentation of the User Interface, but at the same time knows nothing about the user interface itself. This layer has no dependence on the Android Framework, it is a pure Kotlin module. Each ViewModel class that is created implements the ViewModel class found within the Architecture components library. This ViewModel can then be used by the UI layer to communicate with UseCases and retrieve data.

## - Current App's Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

### - Contact
- [Github](https://github.com/khayalsherif)
- [Linkedin](https://www.linkedin.com/in/khayal-sharifli-822015213/)

## - License

```
MIT License

Copyright (c) 2022 Khayal Sharifli

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.```
