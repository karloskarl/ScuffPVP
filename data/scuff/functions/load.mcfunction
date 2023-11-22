#Set up gamerules for the world
gamerule keepInventory true
gamerule doDayLightCycle false
gamerule doMobSpawning false
gamerule naturalRegeneration false
time set 1000

#Establish variables for picking character and map
scoreboard objectives add class dummy
scoreboard objectives add pendingItems dummy
scoreboard objectives add map dummy

#Cooldown timers for use as needed
scoreboard objectives add cooldownTimer1 dummy
scoreboard objectives add cooldownTimer2 dummy
scoreboard objectives add cooldownTimer3 dummy
scoreboard objectives add cooldownTimer4 dummy

#Variable for right click detection
scoreboard objectives add clickDetected minecraft.used:minecraft.carrot_on_a_stick

#Gameplay variables
scoreboard objectives add lives dummy
scoreboard objectives add deaths deathCount
scoreboard objectives add livingPlayers dummy