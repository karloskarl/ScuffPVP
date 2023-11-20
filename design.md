# Design Document
## General Design
 - FFA Deathmatch
    - Optimized for 4 players, but 1v1s should still be playable
    - Needs incentive not to camp
 - Basic round format
    - Go to player selection → select players
    - Go to map selection → random player gets to choose map
    - Players are loaded into map, fight at 5 lives each until one remains
    - Reset


## Damage System
 - Health system similar to vanilla,displayed in hearts, stored seperately
 - Critical hits custom coded
    - Swords retain vanilla behavior
 - Damage from arrows based on velocity (function TBD)

## Classes
 - Bancroft
    - Health: 30
    - Speed: 0.075
    - Passive: None
    - Primary: Sword (7 damage), recharge 1s
    - Secondary: Projectile Parry, 2 block radius, up for 0.2s, cooldown 2s, stops all movement
    - Ultimate: AOE (10 damage), recharge 15s
 - Hansa
    - Health: 20
    - Speed: 0.1
    - Passive: speed increases to 0.14 for 3 seconds whenever hit, 1 crossbow bolt every 6 seconds (2 max)
    - Primary: Crossbow (7 damage), vanilla recharge
    - Secondary: Sword (4 damage), recharge 0.5s
    - Ultimate: Bow (18 damage), recharge 20s
 - Mirabelle
    - Health: 18
    - Speed: 0.12
    - Passive: double jump
    - Primary: Sword (4 damage), recharge 0.1s
    - Secondary: AOE (8 damage, also gives levitation and invulnerability for 5 seconds)
    - Ultimate: Heals 2 hearts per hit achieved for 7 seconds, 15s cooldown
 - Suzuka
    - Health: 20
    - Speed: 0.1
    - Passive: turns invisible when less than 5 health for 5 seconds
    - Primary: Sword (4 damage), recharge 0.1s
    - Secondary: Smoke lasts 6 seconds
    - Ultimate: Sword (18 damage), recharge 15s, 3s pullout speed
 - Esteille
    - Health: 20
    - Speed: 0.1
    - Passive: Gains 1 stardust per 2s, cap at 10
    - Primary: Crossbow (5 damage) (costs 2 stardust per shot)
    - Secondary: Ranged molotov, 1 damage per second for 10 seconds in a 3 block radius (costs 4 stardust)
    - Secondary: AOE (7 damage) (costs 5 stardust)
    - Ultimate: Black hole, sucks people in a 3 block radius and does 2 damage per second for 10 seconds (costs 8 stardust)
- Glass
    - Health: 2
    - Speed: 0.15
    - Primary: Raycast gun, does 30 headshot damage and 5 body damage, 2 second cooldown
    - Secondary: Teleport rod, teleports you to where you are looking, 5 second cooldown

## Maps
 - Abandoned Castle
 - Chateau
    - needs modification (smaller?)

## Class Number Values:
- Bancroft : 1
- Hansa : 2
- Mirabelle : 3
- Suzuka : 4
- Esteille : 5
- Glass : 6