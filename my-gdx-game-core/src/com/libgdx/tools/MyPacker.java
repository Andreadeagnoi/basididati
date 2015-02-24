package com.libgdx.tools;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

//To run to pack tiles from eclipse
public class MyPacker {
    public static void main (String[] args) throws Exception {
        TexturePacker.process("assets/tmpPacker", "assets/data", "jetTiles");
    }
}