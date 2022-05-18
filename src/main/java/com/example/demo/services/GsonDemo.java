package com.example.demo.services;

import com.example.demo.entity.Employee;
import com.example.demo.entity.GsonObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.*;

public class GsonDemo {
    public void test1(){
        JsonObject j = new JsonObject();
        Employee employee = new Employee();
        Gson gson = new Gson();

        employee.setName("tdyyy");
        employee.setAge(20);
        HashMap<String, String> map = new HashMap<>();
        map.put("xx","yy");
        employee.setWork(map);

        String jsonStr = "{\"a\":\"xx\"}";
//        String jsonStr = "xxx";
        JsonObject x = gson.fromJson(jsonStr, JsonObject.class);

        Object o = new Object();
        j.addProperty("a", "999");
        j.addProperty("b",3D);
        j.add("obj",gson.fromJson(gson.toJson(o),JsonObject.class));
        j.add("map",gson.fromJson(gson.toJson(map),JsonObject.class));
        String s = gson.toJson(employee);
        j.getAsJsonObject("obj").addProperty("key","value");
//        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        System.out.println(j.getAsJsonObject("obj").get("key"));

    }

    public static void main(String[] args) {
        GsonObject gsonObject = new GsonObject();
        Gson gson = new Gson();

        String str = "{\"serviceId\":\"queryIfDepsDocsHasSch\",\"unitId\":\"200020406\",\"doctorIds\":{\"200187202\":[],\"200222754\":[],\"200217318\":[],\"200217007\":[],\"200265063\":[],\"200269023\":[]},\"token\":\"3\",\"beginDate\":\"2022-02-24\",\"endDate\":\"2022-03-28\"}";
//        JsonObject json = gson.fromJson(str, JsonObject.class);
        JsonObject json = new JsonObject();
        Map map = gson.fromJson(str, Map.class);
        json.addProperty("serviceId",map.get("serviceId").toString());
        json.addProperty("serviceId2","queryIfDepsDocsHasSch");
        String serviceId = json.get("serviceId").getAsString();
        System.out.println(json.has("serviceId"));
    }

}
