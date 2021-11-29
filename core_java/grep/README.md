# Introduction

The Java Grep App is a Java application that takes in three inputs from the user: `regex`, `rootPath`, and `setOutFile`, in order to look through a directory, list its files, look for lines in each file that match the given `regex` pattern, and write those matching lines to a new file. 

The App makes use of a `JavaGrepImp` class with methods and setters that work together to accomplish the tasks through a broad `process()` method. This class in turn is implemented from an interface called `JavaGrep`. The process of creating the app is discussed in more detail in the Implementation section of this `README.md`.

In this project I made use of the following tools and technologies: Java, OOP, Intellij IDE, Maven, Docker, Git, Bash. 

# Quick Start

`docker pull liamgentile/grep`

`docker run --rm \
-v `pwd`/data:/data -v `pwd`/log:/log \
liamgentile/grep regex rootPath outFile`


# Implementation

## Pseudocode

The app basically seeks to do the following:

`linesMatching = []`
`for file in listFiles(rootDir)`
	`for file in readLines(file)`
		`if containsPattern(line)`
			`linesMatching.add(line)`
`writeToFile(linesMatching)`

This pseudocode is ultimately what the `process()` method will seek to do, using other methods from the `JavaGrepImp` class. 

## `JavaGrep` Interface

The `JavaGrep` Interface acts as a boiler plate for our `JavaGrepImp` class. 

In total we tell the Class that implements `JavaGrep` to create 5 methods and 6 getters and setters. 

## `JavaGrepImp` Class


This class implements the `JavaGrep` Interface. It contains all the necessary variables and methods to run our application.

*Private Variables*
- `regex`: the regex pattern to search for
- `rootPath`: the root directory to look for files in
- `outFile`: the path and name of the output file

*Getters and Setters*

They are self-explanatory. Each one of our private variables has a getter and setter associated with it. 

*Methods*

- `readLines()`: takes in an `inputFile`, gets the `filePath`, and returns a list of all the lines in the file

- `containsPattern()`: takes in a `String line` and returns a `boolean` indicating whether or not that line matches the `regex` private variable

- `writeToFile()`: takes in a `List<String>`, and writes each line to a file (based on the `outFile` private variable)

- `process()`: this is a void method that combines our other three methods in order to, for each file in the path, and for each line in the file, add the line to a new list if it matches the regex pattern, and finally write that new list of lines to a new file

*`main` Class and App Implementation*

The `main` class is where we run our application. It is still contained within our `JavaGrepImp` class. First,  we use an `if` statement to ensure that 3 arguments are provided when running the app (corresponding to `regex`, `rootPath`, and `setOutFile`. We then instantiate the class and use our setters to assign the command line argument (`arg[]`) to their respective private variable. Finally we simply call the `process()` method.
   
# Performance Issue 

This app is not optimized for memory efficiency. For processing larger files, the JVM may not be able to allocate an object due to insufficient space in the Java heap. A way to get around this potential issue would be to employ the `BufferedReader` class. Using this class reads text (characters) in chunks and stores them in a buffer so that the reader can read from there instead of from the entire text, saving memory.

# Test

For this application I tested code along the way, using breakpoints and the debugger in Intellij IDE. 

To test the functioning of the app itself once it ran without errors, I used a sample `txt` file of a shakespeare play. Manually testing the app, I gave it a pattern to check if Romeo and Juliet was on a line, and then sent the output to a new file. The app seemed to successfully return only lines containing Romeo and Juliet. 

# Deployment

I dockerized the application and deployed it via DockerHub at https://hub.docker.com/repository/docker/liamgentile/grep. Dependencies are handled by maven through a `pom.xml` file.   

# Improvements

1. Better planning of code before writing it.  
2. Optimizing the application for better heap memory performance. 
3. Testing the application with more sample `.txt` files. 
