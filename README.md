# AngryBirds-LibGDX
## Introduction
This project is developed for an evaluation component of our Advanced Programming (CSE 201) Course\
The project aims to develop a Simple 2D Game replica of the renowned Title of Angry Birds by Rovio Entertainment\
using a gradle-based Game Library LibGDX.It will help us to understand the basics of Game Development and the
significance of the OOPS programming paradigm in Real-World Applications. This project runs on JAVA-17 version.

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an ApplicationAdapter extension that draws libGDX logo.

### Status 
The project is completed by the deadline for the Functioning Game. We have created All the necessary UI components for the game using LibGDX's Image Button Library
### DEMO LINK : 
```
https://drive.google.com/file/d/1savt5ucvaKi4Ia05_m18zZjhiDvSO1Rr/view?usp=drive_link
```

## Game Screens Preview
![My Image](Group24.png)

## Explanation & Summary of the Project


### Summary
This project is a Java-based game developed using the LibGDX framework. It features an Angry Birds-style gameplay where players interact with various game elements such as birds, blocks, and pigs. The project employs Object-Oriented Programming (OOP) concepts including classes, objects, inheritance, encapsulation, polymorphism, and abstraction. Key classes include LevelSelector, angryBirds, easyLevel001, medLevel001, hardLevel001, blocks, pigs, and bird. The Screen interface is implemented by different screen classes to manage game screens polymorphically. The angryBirds class initializes the game and sets the initial screen. The LevelSelector class handles the level selection screen, including UI elements and their interactions. The project also includes unit tests to verify the functionality of game components.


In this project, several Object-Oriented Programming (OOP) concepts are utilized. Here are the key OOP concepts and their applications:

1. *Classes and Objects*:
   - *Classes*: The project defines several classes such as LevelSelector, angryBirds, easyLevel001, medLevel001, hardLevel001, blocks, pigs, and bird.
   - *Objects*: Instances of these classes are created and used to represent various entities in the game, such as levels, birds, blocks, and pigs.

2. *Inheritance*:
   - The LevelSelector class implements the Screen interface, which is a form of inheritance. This allows LevelSelector to be used polymorphically as a Screen.

3. *Encapsulation*:
   - The project encapsulates data within classes. For example, the blocks, pigs, and bird classes encapsulate their properties like health and power, providing public methods to access and modify these properties.

4. *Polymorphism*:
   - Polymorphism is used through the implementation of the Screen interface. Different screen classes (e.g., LevelSelector, easyLevel001, medLevel001, hardLevel001) can be used interchangeably as Screen objects.

5. *Abstraction*:
   - The project abstracts the complexity of game elements by defining clear interfaces and classes. For example, the Screen interface abstracts the behavior of different game screens.

## Design Pattern and Serialisation
In this project, several design patterns are utilized to structure the code and manage the game's functionality. Here are the key design patterns and their applications:

1. **Singleton Pattern**:
   - The `angryBirds` class follows the Singleton pattern by ensuring that only one instance of the game exists. This is achieved by extending the `Game` class from the LibGDX framework, which inherently supports a single instance of the game.


2. **Observer Pattern**:
   - The Observer pattern is used in the project to handle events and user interactions. For example, the `ClickListener` in the `LevelSelector` class listens for button clicks and responds by changing the game screen or performing other actions.

3. **Strategy Pattern**:
   - The Strategy pattern is used to define a family of algorithms, encapsulate each one, and make them interchangeable. This can be seen in the way different game screens (`LevelSelector`, `easyLevel001`, `medLevel001`, `hardLevel001`) implement the `Screen` interface, allowing the game to switch between screens dynamically.



These design patterns help in organizing the code, making it more modular, maintainable, and scalable.

### Serialisation

Serialization in this project is implemented using the Gson library to convert game objects to and from JSON format. This allows the game to save and load the state of levels, including blocks, pigs, and birds.

#### LevelData.java
- Defines the structure of the level data, including lists of blocks, pigs, and birds.
- Contains nested static classes (`Block`, `Pig`, `Bird`) to represent individual game objects.
- Uses Gson annotations to map JSON fields to class fields.

#### LevelSerializer.java
- Provides a method `saveLevel` to serialize `LevelData` objects to a JSON file.
- Uses `Gson` and `GsonBuilder` to create a JSON representation of the level data and writes it to a file.

#### LevelDeserializer.java
- Provides a method `loadLevel` to deserialize JSON data from a file into `LevelData` objects.
- Uses `Gson` to read the JSON file and convert it back into `LevelData` objects.

This approach ensures that the game can persist and restore the state of its levels, making it more robust and user-friendly.



## Project Info
#### Technologies Used
- *Programming Language:* Java
- *Game Library:* LibGDX
- *Build Tool:* Gradle
- *Java Version:* 23
- *IDE Support:* IntelliJ IDEA, Eclipse

#### Project Structure
- *Core Module:* Contains the main game logic, including game screens, and game objects.
- *Assets Module:* Includes all the game assets such as images, sounds, and fonts.
- *Desktop Module:* Contains the launcher for the desktop version of the game.

#### Key Components
- *Game Screens:* Different screens like the main menu, game screen, and settings screen.
- *Game Objects:* Birds, pigs, and structures with their respective behaviors and interactions.
- *User Interface:* Includes buttons, score displays, and other UI elements.
- *Assets:* Images for birds, pigs, and backgrounds; sounds for effects and background music; fonts for text rendering.

#### Features
- *Multiple Levels :* Various levels with increasing difficulty.
- *Sound Effects :* Includes sound effects for bird launches, collisions, and background music.
- *Random Level Generation :* Generates every level of distinguished difficulties , with new combination of birds and block & pig placement

## How to Run?
### Pre-requisites
JAVA-23 OPENJDK
```bash
sudo apt install java23-openjdk
```

INTELLIJ / ECLIPSE IDE

```bash
https://www.jetbrains.com/idea/
https://eclipseide.org/
```

### Steps to run the Game
```bash
git clone https://github.com/AnshG12/angry_birds.git
cd AngryBird-LibGDX/lwjgl3/src/main/java/game/dev/lwjgl3
java lwjgl3Launcher.java
```



## Platforms

- core: Main module with the application logic shared by all platforms.
- lwjgl3: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using gradlew.bat or ./gradlew commands.
Useful Gradle tasks and flags:

- --continue: when using this flag, errors will not stop the tasks from running.
- --daemon: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- --offline: when using this flag, cached dependency archives will be used.
- --refresh-dependencies: this flag forces validation of all dependencies. Useful for snapshot versions.
- build: builds sources and archives of every project.
- cleanEclipse: removes Eclipse project data.
- cleanIdea: removes IntelliJ project data.
- clean: removes build folders, which store compiled classes and built archives.
- eclipse: generates Eclipse project data.
- idea: generates IntelliJ project data.
- lwjgl3:jar: builds application's runnable jar, which can be found at lwjgl3/build/libs.
- lwjgl3:run: starts the application.
- test: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with name: prefix, where the name should be replaced with the ID of a specific project.
For example, core:clean removes build folder only from the core project.

## Authors
 Anushk Kumar  2023115\
 Ansh Goel     2023099
