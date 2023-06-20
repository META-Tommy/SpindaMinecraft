![spinda](https://media.discordapp.net/attachments/1102448904001302608/1103018159319023756/image.png?width=475&height=559)
# Spinda In Minecraft
## How to use
 - Have Minecraft: Java Edition, [Fabric](https://fabricmc.net/), and [Carpet mod](https://www.curseforge.com/minecraft/mc-mods/carpet)
 - Create a file inside the world called `scripts` and one inside that called `shared`. The file directory should look like `.minecraft\saves\YOUR_WORLD\scripts\shared`
 - Download and place `model.sc` in `.minecraft\saves\YOUR_WORLD\scripts`
 - Put `model_info.txt` inside `.minecraft\saves\YOUR_WORLD\scripts\shared`
 - Open the your minecraft world and run the command `/script load model`
 - Now you can run `/model render x y z scale block`, where x, y, and z are the bottom center of where the model will be placed in the world, scale is how big the model is, and block is how big the text_displays will be. I found scale=1 and block=1 works perfect.

