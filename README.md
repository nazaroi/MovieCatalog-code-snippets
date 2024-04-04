# MovieCatalog (code snippets)

🟥 *The complete MovieCatalog app source code is located in a private repo.*

MovieCatalog is a sample app built using architecture components, **MVI** and best practices, allowing users to explore and discover movies from [The Movie Database (TMDb)](https://www.themoviedb.org/) API. It utilizes a **multi-module** architecture and caches movies locally using [Room](https://developer.android.com/jetpack/androidx/releases/room) & [RemoteMediator](https://developer.android.com/reference/kotlin/androidx/paging/RemoteMediator). 

<a href="https://play.google.com/store/apps/details?id=com.nazaroi.moviecatalog"><img src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" height="70"></a>

<p align="center">
  <img src="/screenshots/recording_home_screen.gif" width="30%"/>
  <img src="/screenshots/screenshot_movie_category.png" width="30%"/>
  <img src="/screenshots/screenshot_movie_trailer.png" width="30%"/>
</p>

<p align="center">
   <img src="/screenshots/screenshot_search_screen.png" width="30%"/>
  <img src="/screenshots/screenshot_empty_search_screen.png" width="30%"/>
  <img src="/screenshots/screenshot_wishlist_screen.png" width="30%"/>
</p>

## Description

* UI
    * [Material Design](https://material.io/design)
    * [View binding](https://developer.android.com/topic/libraries/view-binding)
 
* Tech
    * [Kotlin](https://kotlinlang.org/)
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
    * [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation)
    * [ViewModel](https://developer.android.com/jetpack/androidx/releases/lifecycle)
    * [Room](https://developer.android.com/jetpack/androidx/releases/room)
    * [Work](https://developer.android.com/jetpack/androidx/releases/work)
    * [Paging 3](https://developer.android.com/jetpack/androidx/releases/paging)
    * [Hilt](https://dagger.dev/hilt/)
    * [Retrofit](https://square.github.io/retrofit/)
    * [Glide](https://github.com/bumptech/glide)

* Architecture
    * Single-activity with [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
    * Complex MVI (includes state, intents, effects, also involved in navigation)
    * Multi-module
    * Navigation through deep links
    * Caching using [Room](https://developer.android.com/jetpack/androidx/releases/room) & [RemoteMediator](https://developer.android.com/reference/kotlin/androidx/paging/RemoteMediator)
    * Use cases
    * Dto to Entity & Entity to Domain mappings
    * Custom error handling classes/interfaces/functions

* Modules
    * app
    * base - base classes/interfaces for fragments, view models, adapters, daos, custom error handling, converters, mappers, use cases etc., ktx, utilities.
    * buildSrc - build configs, dependencies, versions, plugins constants.
    * common - shared resources, constants, deep links, base classes/interfaces and ktx specific to the app.
    * data - repository/mapper implementations, network requests, database, caching (mediators), di, dto/entity models, workers etc.
    * domain - models, repository interfaces, use cases
    * feature(s)

<p align="center">
  <img src="/multi-module-structure.png" width="100%" />
</p>
