#Handles lives system
scoreboard players operation @s lives -= @s deaths
scoreboard players set @a deaths 0
execute as @a[scores={lives=0},gamemode=!spectator] run gamemode spectator @s

#Game end condition
scoreboard players set livingPlayers livingPlayers 0
execute as @a[gamemode=!spectator] run scoreboard players add livingPlayers livingPlayers 1
execute if score livingPlayers livingPlayers matches ..1 run function scuff:endGame

