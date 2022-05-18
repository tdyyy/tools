package com.example.demo.entity;

import com.google.gson.*;

import java.util.Collection;

public class GsonObject {
    private JsonObject jsonObject;
    private static Gson gson = new Gson();

    public GsonObject parseObject(Object obj){
        this.jsonObject = gson.fromJson(gson.toJson(obj),JsonObject.class);
        return this;
    }

    public GsonObject(){
        this.jsonObject = new JsonObject();
    }

    public void put(String key,Number number){
        this.jsonObject.addProperty(key,number);
    }
    public void put(String key,String value){
        this.jsonObject.addProperty(key,value);
    }
    public void put(String key,Boolean value){
        this.jsonObject.addProperty(key,value);
    }
    public void put(String key,Character value){
        this.jsonObject.addProperty(key,value);
    }
    public void put(String key,Object value){
        if(value instanceof Collection){
            this.jsonObject.add(key, gson.fromJson(gson.toJson(value), JsonArray.class));
        }else {
            this.jsonObject.add(key, gson.fromJson(gson.toJson(value), JsonObject.class));
        }
    }

    public String getString(String key){
        JsonElement jsonElement = jsonObject.get(key);
        return gson.toJson(jsonElement);
    }

    public JsonObject getJsonObject(String key){
        JsonElement jsonElement = jsonObject.get(key);
        if(jsonElement instanceof JsonPrimitive){
            return gson.fromJson(jsonElement,JsonObject.class);
        }else {
            return (JsonObject)jsonElement;
        }
    }

    public JsonArray getJsonArray(String key){
        JsonElement jsonElement = jsonObject.get(key);
        if(jsonElement instanceof JsonPrimitive){
            return gson.fromJson(jsonElement,JsonArray.class);
        }else {
            return (JsonArray)jsonElement;
        }
    }

    public String toJsonString(){
        return gson.toJson(jsonObject);
    }

    @Override
    public String toString() {
        return toJsonString();
    }

    @Override
    public boolean equals(Object obj) {
        return jsonObject.equals(obj);
    }

    @Override
    protected Object clone() {
        return jsonObject.deepCopy();
    }

    @Override
    public int hashCode() {
        return jsonObject.hashCode();
    }
}
