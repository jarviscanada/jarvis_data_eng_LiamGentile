# Introduction

This project uses JDBC so that we can interact with and modify a PostgreSQL database using Java code. It is composed of a number of different Java classes that allow us to establish a connection with a database and execute various methods that modify the data contained in the database. These classes and interfaces will be described in the Implementation section below. The contents of the project are containerized using Docker and dependencies are handled by Maven, through a pom.xml file. 

Tools & technologies used: JDBC, PostgreSQL, Java, OOP, Maven, Docker, Git, IntellijIDE

# Implementation

## Project Setup

The first step of the project was to use Docker to pull the postgres image, make a directory, and run a docker container locally using this image. Then, using PostgreSQL commands, I executed a number of SQL scripts to create a database, called `hplussport`, and populate that database with 5 tables and sample data to fill those tables. 

## ER Diagram

![hplussport_database](https://user-images.githubusercontent.com/80293145/144295342-d1ec5ce3-968b-4aab-a11b-1e8c3d038109.png)

This is a diagram that shows the tables contained in the database and the relationships between them. The relationships aren't especially important in this project, as I was seeking more to demonstrate implementing a JDBC. My app only implements a Data Access Object on the `customer` table. 

## `DataTransferObject` and `DataAccessObject`

These Objects are the base layer of our application that allow us to implement more specific classes that act on individual databases. 

The Data Transfer Object is an interface which serves as a boiler plate for our Data Access Object, defining the methods that will be used. 

The Data Access Object is a class that extends the Data Transfer Object and serves as a parent class for specific table DAOs. 

## `DatabaseConnectionManager`

The Database Connection Manager is a class that does just what it implies, it manages the connection to the database, we employ it to connect to the database.

## `Customer` and `CustomerDAO`

The Customer class essentially acts as a parallel to the Customer table in the database. It contians private variables associated with each column in the table, as well as getter and setter methods. 

The Customer DAO class extends DataAccessObject on Customer and is where our methods are fully written out - `findById()`, `findAll()`, `update()`, `create()`, and `delete()`. These allow us to modify the Customer table in various ways. 

## `JDBCExecutor`

The JDBC Executor is a class that I used to test the DatabaseConnectionManager and CustomerDAO methods. 

## Design Patterns

The design pattern of this project is structural. By using JDBC and a Data Access Object, we are isolating the business application layer from the persistence layer (the database). The API allows these layers to change and evolve separately, without one affecting the other. This independence allows for a lot of flexibility and more stability. 

# Test

I tested the functioning of the JDBC classes through the `JDBCExecutor` class. This is where I ran the `CustomerDAO` methods using sample data. Then, I connected to the PostgreSQL database from the terminal, through psql, and verified that our Java code modified the data by querying the Customer table from there. 

# Improvements

1. Explore more effective Java exception handling.
2. Implement useful DAOs for the other tables in our sample database. 
3. Test methods more comprehensively. 
