package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.services.BaseService;
import de.christophkraemer.rhino.javascript.RhinoScriptEngine;
import de.christophkraemer.rhino.javascript.RhinoScriptEngineFactory;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private BaseService service;
    @Autowired
    private JedisSentinelPool pool;

    @Test
    void contextLoads() {
    }
    @Test
    void RedisTest(){
        Jedis jedis = pool.getResource();
        while (true) {
            Long task_queue_test = jedis.lpush("TASK_QUEUE_TEST", "{\"createTime\":1651746209172,\"unit_id\":\"262\",\"his_dep_id\":\"401003\",\"start_date\":\"2022-05-07\",\"end_date\":\"2022-05-07\",\"taskType\":2}");
//            Long task_queue_test = jedis.lpush("TASK_QUEUE_TEST", "{\"createTime\":1651746209172,\"unit_id\":\"200029701\",\"his_dep_id\":\"10010015\",\"start_date\":\"2022-06-07\",\"end_date\":\"2022-06-07\",\"taskType\":2}");
            System.out.println(task_queue_test);
        }
    }

    public static void main(String[] args) throws ScriptException {
        String script = "var start = Date.now();" +
                " var state1=result.get('state');\n" +
                "if(state1== '1'){\n" +
                "    result.put('state','0');\n" +
                "\n" +
                "}else if(state1=='0'){\n" +
                "    result.put('state','1');\n" +
                "\n" +
                "} \n" +
                "var list = result.get('data');\n" +
                "for (var i = 0; i < list.size(); i++) {\n" +
                "    var data = list.get(i);\n" +
                "    /*从此开始编写脚本*/\n" +
                "\n" +
                "    //转换午别\n" +
                "    var timetype = data.get('time_type');\n" +
                "    if (timetype == '1') {\n" +
                "        data.put('time_type', 'am');\n" +
                "\n" +
                "    } else if (timetype == '3') {\n" +
                "        data.put('time_type', 'pm');\n" +
                "    }\n" +
                "\n" +
                "    //区分班次\n" +
                "    var his_sch_id = data.get('his_sch_id');\n" +
                "    var sch_type = timetype + \"_\" + his_sch_id;\n" +
                " " +
                "    data.put('his_sch_id1', timetype + \"_\" + his_sch_id);\n" +
                "    data.put('his_sch_id2', sch_type);\n" +
                "\n" +
                "    //判断职称是否为空，如果是空值则给个“无”\n" +
                "    var level_name = data.get('level_name') + '';\n" +
                "    if (level_name == 'null' || level_name == '') {\n" +
                "        data.put('level_name', '无');\n" +
                "    }\n" +
                "\n" +
                "    var begin_time = data.get('begin_time') + '';\n" +
                "    /*如果接口中未传开始时间与结束时间，说明接口未放号，将可约数置零*/\n" +
                "    if (null == begin_time || 'null' == begin_time || '' == begin_time) {\n" +
                "        data.put('src_max', '0');\n" +
                "    }\n" +
                "    var status = data.get('status');\n" +
                "    if(status=='4'){\n" +
                "    data.put('status','停诊');\n" +
                "    data.put('src_max','0');\n" +
                "    data.put('src_num','0');\n" +
                "    }else{\n" +
                "    data.put('status','可约');\n" +
                "    }\n" +
                "}" +
                "var end = Date.now();" +
                "result.put('spend',end-start)";
        String objStr = "{\"schext_social_security\":\"0\",\"begin_time\":\"10:30:00\",\"his_src_id\":\"1\",\"status\":\"1\",\"doc_name\":\"九价第二针(接种)\",\"sch_date_time\":\"00:00:00\",\"schext_self_paying\":\"0\",\"time_type\":\"1\",\"schext_registration\":\"0\",\"amt\":\"0\",\"sch_date\":\"2022-05-06\",\"level_name\":\"义诊\",\"his_dep_id\":\"401003\",\"his_sch_id\":\"2205069394\",\"src_max\":\"1\",\"his_doc_id\":\"九价第二针(接种)\",\"end_time\":\"10:59:59\",\"dep_name\":\"预防接种门诊\",\"src_num\":\"1\"}";
        HashMap map = JSONObject.parseObject(objStr, HashMap.class);
        List<Map> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(map);
        }

        long start = System.currentTimeMillis();
//        ScriptEngineManager manager = new ScriptEngineManager();
//        ScriptEngine engine = manager.getEngineByName("js");
        ScriptEngine engine = new NashornScriptEngineFactory().getScriptEngine();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",list);
        resultMap.put("state","0");
        // 将reusltMap传进去后可以通过JS对出参结果进行任意的处理 很方便
        engine.put("result", resultMap);
        long scriptStart = System.currentTimeMillis();
//		engine.eval(" var a = 1");
//        for (int i = 0; i < 10; i++) {
            engine.eval(script);
//        }
        long end = System.currentTimeMillis();
        System.out.println("总共耗时 spend :"+(end-start));
        System.out.println("处理时间 spend :"+(scriptStart-start));
        System.out.println("调用耗时 spend :"+(end-scriptStart));
        System.out.println("脚本耗时 spend :"+resultMap.get("spend"));
    }

}
