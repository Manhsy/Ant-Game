# Ant-Game
A game that simulates a world in which includes, an ant, flags, spiders, and food stations. 

## Technologies/concepts used:
- CodeName1
- Java
- OOP

## How to play: 
- The game world starts with ant at the fist flag's location & spiders, flag, food station at a random location 
- Using the buttons, players can control the ant in various way 
- To win the game the player has to move the ant to the last possible flag (flag with highest sequence number) before the ant runs out of food or lose all health which happens when the ant collides with the spider

## Commands:
- accelerate: increases the ant's speed 
- brake: decrease the ant's speed
- left/right: turns the ant perspectively 
- collide with food station: increases the ant's food level 
- collide with flag: accepts a number between 1-9, if that number is exactly 1 larger than the current flag that the ant is on, ant have made a good move
- collide with spider: decreases ant's health by 1
- game tick: decreases ant's food level by food consumption rate & move all the movable objects 

## To play:
- download all files in src folder
- use eclipse to create a CodeName1 project 


