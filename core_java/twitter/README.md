# Introduction

# Quick Start

`docker pull liamgentile/twitter`

- `docker run --rm`
- `-e consumerKey=yourConsumerKey`
- `-e consumerSecret=yourConsumerSecret`
- `-e accessToken=yourAccessToken`
- `-e tokenSecret=yourTokenSecret`
- `liamgentile/twitter post|get|delete [options]`

# Design

## UML Diagram

![twitter_uml](https://user-images.githubusercontent.com/80293145/146214683-49547761-8d79-47a7-9a2d-2e722135ffc6.png)


### TwitterCLIApp

- Initializes the application components (classes) and their dependencies 
- Parses command line arguments and prints tweets returned from the controller class methods

`printTweet` method
- converts `Tweet` object to Json string and prints the output
- utility method that allows us to write cleaner code in our run method

`run` method
- takes in command line arguments and runs the app using `Controller` methods`
- uses switch case to handle post|show|delete functionality
- allows for cleaner code in the main method (a simple `app.run(args)`)

### TwitterController

- This layer contains methods that take in the command line arguments and call the corresponding service layer method
- No business logic, just taking user input and "sending" to service layer
- Implements the Controller interface

`postTweet` method
- handles illegal command line argument exceptions 
- returns `postTweet` method from service layer

`showTweet` method
- handles illegal command line argument exceptions
- returns `showTweet` method from the service layer

`deleteTweet` method
- handles illegal command line argument exceptions
- returns `deleteTweet` method from the service layer 


### TwitterService

- This layer handles the application's business logic - using `validatePostTweet` and `validateId` methods:

          - Check if the tweet exceeds 140 characters and if lat/lon is out of range
          - Check if tweet ids are correctly formatted (for searching)
           
- Once the business logic has been validated, each method returns a corresponding DAO method
- This class implements the Service Interface


### TwitterDao

- This layer makes Twitter REST API URIs and makes HTTP calls using HttpHelper methods
- Implements the CrdDao Interface

`parseReponseBody` 
- takes HTTP response and converts to Json String and then Tweet object

`create`, `findById`, and `deleteById` methods
- Use HttpHelper with method specific URIs to return corresponding parseResponseBody response

### TwitterHttpHelper

- This layer is a helper layer that executes HTTP with a given URI and authorizes requests using Twitter API keys and tokens

`executeHttpRequest` method
- takes in either get or post method and signs HTTP request accordingly

`httpPost` and `httpGet` methods
- run the `executeHttpRequest` method with corresponding POST|GET method 

### Models

### Spring

# Test

## Deployment

# Improvements

- Improvement 1
- Improvement 2
- Improvement 3
