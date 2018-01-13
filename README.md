# ForgeWorldGen
ForgeWorldGen is a simple, lightweight Forge mod that lets you generate areas of a world without traveling there. Currently available for 1.7.10 (and maybe earlier 1.7s? No idea), with an experimental release for 1.11.2. 

## Commands
`/fwg worldID lowX lowY highX highX`  
`worldID`: Which world to operate on, specified by dimension. `0` for the Overworld, `-1` for the Nether, and `1` for the End.  
`lowX`, `lowY`: The chunk coordinates of the northwestern corner  
`highX`, `highY`: The chunk coordinates of the southeastern corner  
The arguments use chunk coordinates, not block coordinates. To get chunk coordinates from block coordinates, just divide by 16.

## Bugs
* There's no limit to how many separate generation tasks you can have running at once. If you have a fast server machine, this might be a feature. ;)
* There's no way to turn off the generation feedback spam. Either run generation jobs from the server console, reconnect, or deal with it.
* There's no reasonable way to cancel a generation task. Restart your server to abort if you really need this. 
* I don't think there are limits on who can run the `fwg` command. I think it's limited to ops by default, but you might want to check this. 

## Download
Downloads are available on the project's [releases page](https://github.com/AGSPhoenix/ForgeWorldGen/releases).