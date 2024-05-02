# Sports Management System

## Overview
The Sports Management System is a Java-based application for managing players, sports, coaches, and their relationships. It provides functionalities for inserting, updating, and deleting players, sports, coaches, and player-sport relationships. Additionally, it offers features to retrieve statistics such as the number of sports participated by a player and the number of players participating in a sport.

## Getting Started
1. Ensure you have Java JDK installed on your system.
2. Install MySQL and create a database named "sports".
3. Execute the SQL script `imt2022521_scripts.sql` to create the necessary tables and populate them with sample data.
4. Compile and run the `imt2022521_sports.java` file to start the application.

## File Structure
1. `imt2022521_sports.java`: Java source code file containing the main application logic.
2. `imt2022521_scripts.sql`: SQL script for creating the database schema and inserting sample data.

## Usage
1. Upon running the application, follow the on-screen prompts to perform various operations such as inserting players, sports, coaches, and player-sport relationships.
2. Choose the operation you want to perform by entering the corresponding number from the menu.
3. Follow the instructions to input required information for each operation.
4. The application will execute the selected operation and provide feedback on the console.

# Database Schema

The database schema consists of the following tables:

1. **players**: Stores information about players.
2. **sports**: Stores information about sports.
3. **player_sports**: Maps players to the sports they participate in.
4. **coaches**: Stores information about coaches.
5. **sports_per_player**: Tracks the count of sports each player participates in.

## Dependencies
1. Java Development Kit (JDK)
2. MySQL Database

## Contributor
- Sarvesh Kumar . A

