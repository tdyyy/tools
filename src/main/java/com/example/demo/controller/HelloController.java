package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.services.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
public class HelloController {
    @Autowired
    private BaseService baseService;
    @RequestMapping("/hello/{str}")
    public String hello(@PathVariable("str") String str){
        return baseService.hello(str);
    }
    @RequestMapping("/get")
    public String get(HttpServletRequest request) throws Exception{
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.error("get count++");
        int[] a= {2};
        return String.valueOf(a[10]);
    }
    @RequestMapping("/hellox/{str}")
    public String hellox(@PathVariable("str") String str){
        return baseService.outerHello(str);
    }
    @RequestMapping("/taskAdd/{str}")
    public String taskAdd(@PathVariable("str") String str){
        return baseService.redisTest(str);
    }
    @RequestMapping("/src/test")
    public String srcTest(){
        FeignUntil.SrcInvoke srcInvoke = FeignUntil.getSrcInvoke();
        JSONObject jsonObject1 = srcInvoke.querySrcJsonValue("/sch_queryTodaySrc", "{\"unit_id\": \"111\",\"dep_id\": \"3892\",\"doc_id\": \"19718\",\"real_time\": \"1\",\"his\": \"1\",\"sch_id_list\": [\"62426eac9f37ff05ae30641f\", \"62426ea89f37ff05ae30638d\"]}");
        return jsonObject1.toJSONString();
    }
}
