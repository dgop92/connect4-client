# Connect 4 Client

The well-known game connect 4 implemented with Java and SocketIO

## Instructions

- In the main menu you must provide an username and a room name. If the room doesn't exit, it is created. 

- In order to start the game, two players are required. The creator of the room is in charge of starting the game.

## Project setup

It is recommended to run the project on VS code with Java 11 or greater
Main class: src/app/Connect4.java

So far the server url is hardcode

```java
// located in src\core\SocketManager.java
public static final String serverUrl = "https://boiling-gorge-60187.herokuapp.com";
```
Note: The API is hosted on the free plan of Heroku, so you will have to wait until the container wake up

You can replace it with ws://localhost:8080, if you want to use the localhost server

To setup the server [Connect 4 Server](https://github.com/dgop92/connect4)
