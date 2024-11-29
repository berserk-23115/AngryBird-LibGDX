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


