# android clean architecture java
Basic concept and the skeleton of the android application with a clean architecture written on Java. Additionaly we use patterns: Repository, MVVM and Router.

## Domain module
Independent layer, it contains **only** business logic written on java or kotlin - no dependencies from Android SDK, other modules or libs, except RxJava(Not RxAndroid!!!) and Injection because thay are very common libraries.
Here we have contracts(interfaces) that should be implemented in other modules(data etc.).

## Data module
It contains the functions for obtaining and storing data - network calls, database, files, shared preferences etc. This layer can depend on Android SDK and other libs(for exp: Gson, Retrofit). But we mustn't do any business logic here! The goal of this layer just retrieve and save data - nothing more!

## Presentation module
First of all this layer contains UI. All interactions with the user should be here. The Presentation layer communicates with the domain layer for receiving and sending data(entity). We don't have to communicate directly with the data or other layers. Additionaly it is the main module in the android app so we should set up all modules here (Providers for Injections and ect.).

**The key feature of the clean architecture is the Entity. Each module has own entities, it makes each layers independent as much as possible**
