# ForgeWorldGen
ForgeWorldGen is a simple, lightweight Forge mod that lets you generate areas of a world without traveling there.

## Commands
`/fwg worldID lowX lowY highX highX`  
`worldID`: Which world to operate on, specified by dimension. `0` for the Overworld, `-1` for the Nether, and `1` for the End.  
`lowX`, `lowY`: The chunk coordinates of the northwestern corner  
`highX`, `highY`: The chunk coordinates of the southeastern corner  
The arguments use chunk coordinates, not block coordinates. To get chunk coordinates from block coordinates, just divide by 16.

## Bugs
oh god where do i start  
Basically, don't run this on a production server. A one time generation is probably fine, but malicious players could probably abuse this to take your server down. 

## Download
Downloads are available on the project's [releases page](https://github.com/AGSPhoenix/ForgeWorldGen/releases).