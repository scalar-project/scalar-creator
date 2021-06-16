package com.noobian.scalarcreator;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

public class SubModel {
	String jsonName;
	JsonObject json;
	Map<String,ByteBuffer> textures = new HashMap<String, ByteBuffer>();
}
