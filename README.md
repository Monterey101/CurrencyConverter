# Currency Converter

![](https://img.shields.io/github/stars/monterey101/currencyconverter?style=social) ![](https://img.shields.io/github/forks/monterey101/currencyconverter?style=social) ![](https://img.shields.io/github/release/monterey101/currencyconverter?style=social) ![](https://img.shields.io/github/issues/monterey101/currencyconverter?style=social) ![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/monterey101/currencyconverter?style=social)

__THIS IS A SIMULATOR AND DOES NOT MAKE REAL TRANSACTIONS__

## How it works

#### Users

Normal users have access the the regular features of the program including:

- Exchanging currencies
- Viewing the Popular Exchange Table
- Generating Currency Report

The User Interface is built using [JavaFX](https://openjfx.io) modules, and reports are generated using [iText](https://itextpdf.com) modules via dependencies. Reports are PDF docuements that along with report information, contain a table in the format:
                    
Date of change | Rate A /B | Rate B/A
------------- | ------------- | ------------
`Date`  | `double` | `double`

#### Admin

In addition to the features allowed by normal users, the admin can also manage the converter through a management page. The admin can:

- Update currency exchanges
- Add new currencies
- Set the popular currencies

## How to run

To run the app you need to build it via the gradle wrapper. You can generate the wrapper using the `$ gradle wrapper` command for macOS and Linux, or `$ gradle.bat wrapper` for Windows.

Ensure that you running at least Java (22.0.2) and Gradle (8.10) or later:

```bash
$ ./gradlew clean build
```

Once you have built the aplication, you can use the gradle wrapper to run it:

```bash
$ ./gradlew run
```

## How to test

Once you have the gradle wrapper you can run the [Junit](https://junit.org/junit5/) tests by running:

```bash
$ ./gradlew test
```

## How to contribute

Contributions should be done by cloning the repo via the `$ git clone` and creating a branch. Within this branch you can make changes. Once you have completed the feature, you can create a PR. An admin will decide whether to accept your changes and/or decide to close your branch.

## Depreciated Features

In older versions of this application, currency exchange data was pulled via [ExchangeRate-API](https://www.exchangerate-api.com). Due to requirement changes this was removed, however the local JSON database still uses the format of the API and can easily be reconnected. The application starts with exchange rates correct as of 5 September 2024 00:00. If you would like to connect this application to live rates you can do so using the following:

__API Link__ | __API Key__  
------------- | -------------
https://www.exchangerate-api.com | f61de3dff24b4066809ff414

For more information on this API check out the [docs](https://www.exchangerate-api.com/docs).