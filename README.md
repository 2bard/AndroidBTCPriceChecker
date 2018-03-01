# AndroidBTCPriceChecker

I was looking for a simple application to write as my first Kotlin Android app. I decided on a currency checker and found the simple Coinbase API, all I needed to get started.

# Architecture

I have implemented a simple MVVM pattern. For handling configuration changes I'm using a retained fragment. I avoided using the Android Architecture components as I wanted to keep things simple and not write too much boilerplate.

## Features

The app periodically queries the Coinbase API and returns the current Bitcoin value. Simple!

## Testing

I've included a couple of instrumentation tests powered by [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver), [Dagger 2](https://google.github.io/dagger/), [Espresso](https://developer.android.com/training/testing/espresso/index.html), and [Spoon](https://github.com/square/spoon). I've also included an example of a classic unit test using [Robolectric](http://robolectric.org/).

