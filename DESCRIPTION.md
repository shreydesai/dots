# Description

## Overview

This game is a one player, arcade shooter game. The player is represented by a black circle that must face three enemies throughout the game, characterized by their colors: red, green, and blue. 

The red enemies are static and cannot move, the green enemies move up and down at a medium pace, and the blue enemies move up and down at a faster pace. In order to defeat these enemies, the player can point his/her cursor at the enemies and shoot them. If the bullet touches the enemy, then the enemy will vanish.

The game will be restricted to about a minute and a half of playing time. As the player kills the enemies, he/she will receive points; 5 for red, 10 for green, and 15 for blue. 

This project uses the Java programming language along with HTML and CSS as accessories for design purposes. We are not using GridWorld to construct our graphical user interface, but JavaFX instead. 

## Project Structure

### File/Folder Tree
```

|–– model
	|–– Animation.java
	|–– Bullet.java
	|–– Sprite.java
	|–– Player.java
	|–– Enemy.java
	|–– RedEnemy.java
	|–– GreenEnemy.java
	|–– BlueEnemy.java
|–– view
	|–– Game.java
	|–– DialogBox.java
	|–– InfoBox.java
	|–– EndBox.java
	|–– style.css
	|–– content.txt
|–– controller
	|–- Background.java
	|–– Frame.java
	|–– Main.java
|–– sound
	|–– shot.wav
	|–– hit.wav
```

### Model

#### `Animation.java`

Interface to represent an animation on the canvas. The `Bullet` class will probably implement this interface because it is represented as a line with different `x` and `y` positions as time changes.

#### `Sprite.java`

Interface to represent a sprite, or computer graphic, on the canvas. It will include a couple methods to move the object around the game and access its `x` and `y` coordinates for manipulation and strategic placing.

#### `Player.java`

Represents the player in the game - a black circle that is capable of moving around with `W`/`A`/`S`/`D` and `Up`/`Down`/`Left`/`Right` keys. It can also shoot bullets when the mouse is pressed on some point on the canvas. It will implement the `Sprite` interface.

#### `Enemy.java`

Abstract class to represent an `Enemy` object. Some things will need to be implemented by the child classes, like the amount of points a particular `Enemy` is worth. Other things will be constant, like methods to find `x` and `y` coordiantes or to move the `Enemy`.

#### `RedEnemy.java`

Child class of `Enemy`, and represents a red circle in the game. This enemy will not be able to move, making it the easiest of enemies to defeat.

#### `GreenEnemy.java`

Child class of `Enemy`, and represents a green circle in the game. This enemy will be able to move at a medium pace, making it a medium enemy to defeat.

#### `BlueEnemy.java`

Child class of `Enemy`, and represents a blue circle in the game. This enemy will be able to move at a fast pace, making it a hard enemy to defeat.

### View

#### `Game.java`

Represents the overall canvas of the game and defines which elements will be included in the main frame. Some elements to include will be a text label to represent time elapsed and another text label to represent the number of points the player has accumulated.

#### `DialogBox.java`

Interface to represent an alert or dialog box. It has some methods that any dialog box would need, such as setting the title, header, and contents of the window.

#### `InfoBox.java`

Represents an information box displayed in the beginning of the game which will give instructions for how to play the game, and an "Ok" button to start the game. Implements the `DialogBox` interface.

#### `EndBox.java`

Displays how many points the player has at the end of the game and asks the player whether he/she wants to exit the program or play the game again. Implements the `DialogBox` interface.

#### `style.css`

Styling for the enemies in the game. The Red, Green, and Blue enemies will have pointers to this file, which represents a color code. 

#### `context.txt`

This text file will have instruction text for the information box displayed in the beginning of the game. The `InfoBox` class will read with in the text with a `Scanner` object and set the dialog box's content accordingly.

### Controller

#### `Background.java`

Contains all of the background threads that will be running in each instance of the game. The threads will be created by the `Timer` class. The three timers will be 1) tracking the points 2) tracking how much time has elapsed and 3) tracking the different stages in the game.

#### `Frame.java`

This class will contain the bread and butter of the game. It will create the player and enemy objects, start the timers, and control whether the game is over or not.

#### `Main.java`

Driver class. This creates an instance of the `Frame` class and starts the application.

### Sound

#### `shot.wav`

Sound file for the bullet shot by the player.

#### `hit.wav`

Sound file for when the bullet hits an enemy.

## GUI Appearance

### Info Box
![](http://i.imgur.com/GYUhYd2.jpg)

### Main Canvas
![](http://i.imgur.com/jFaXcxd.jpg)

### End Box
![](http://i.imgur.com/5FGEzly.jpg)
