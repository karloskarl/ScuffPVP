#Initialize class
execute if score map map matches 1.. as @a[scores={class=1,pendingItems=0}] run attribute @s minecraft:generic.max_health base set 30
execute if score map map matches 1.. as @a[scores={class=1,pendingItems=0}] run attribute @s minecraft:generic.movement_speed base set 0.77
execute if score map map matches 1.. as @a[scores={class=1,pendingItems=0}] run item replace entity @p hotbar.0 with iron_sword{display:{Name:'{"text":"Bancroft\'s Sword"}'},HideFlags:38,Unbreakable:1b,DamageDealt:7,Recharge:1} 1
execute if score map map matches 1.. as @a[scores={class=1,pendingItems=0}] run item replace entity @p hotbar.1 with carrot_on_a_stick{display:{Name:'{"text":"Parry"}'},HideFlags:38,Unbreakable:1b} 1
execute if score map map matches 1.. as @a[scores={class=1,pendingItems=0}] run item replace entity @s armor.head with minecraft:player_head{display:{Name:"{\"text\":\"Bancroft's Head\"}"},SkullOwner:{Id:[I;-1247624699,-1019197244,-1464218590,-838808271],Properties:{textures:[{Value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWUzNjJjZjcyZDY0YjcwOWFmMGNlZDliMGY4OWQyOTI3NGI5ZWUxZDMxZGUwZjgzZmQzMmE2MzlmNTg0NTc3YiJ9fX0="}]}},Unbreakable:1} 1
execute if score map map matches 1.. as @a[scores={class=1,pendingItems=0}] run item replace entity @s armor.chest with minecraft:leather_chestplate{display:{color:12895428},HideFlags:127,Unbreakable:1}
execute if score map map matches 1.. as @a[scores={class=1,pendingItems=0}] run item replace entity @s armor.legs with minecraft:leather_leggings{display:{color:12895428},HideFlags:127,Unbreakable:1}
execute if score map map matches 1.. as @a[scores={class=1,pendingItems=0}] run item replace entity @s armor.feet with minecraft:leather_boots{display:{color:12895428},HideFlags:127,Unbreakable:1}
execute if score map map matches 1.. as @a[scores={class=1,pendingItems=0}] run scoreboard players set @s pendingItems 1

#Secondary
execute as @a[scores{clickDetected=1..}] run
execute if score map map matches 1.. as @a[scores={class=1}] run scoreboard players add @s cooldownTimer1 1