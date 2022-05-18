package com.example.demo.services;

import com.alibaba.fastjson.JSONObject;
import feign.*;
import feign.codec.Decoder;
import feign.codec.StringDecoder;
import feign.form.FormEncoder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FeignUtil {

    private static String ruleURL = "";
    private static String hisApiURL = "";
    private static String HdbsURL = "";
    private static String srcURL = "";
    private static Request.Options defaultOptions = new Request.Options(1, TimeUnit.SECONDS,3,TimeUnit.SECONDS,false);
    private static JsonDecode jsonDecode = new JsonDecode();
    private static StringDecoder stringDecoder = new StringDecoder();
    private static FormEncoder formEncoder = new FormEncoder();

    /**
     * rule请求模板
     * @param param 如：serviceId{=}sys_query_effective_time{,}dataPackType{=}2{,}dataDesZip{=}00{,}userId{=}admin{,}password{=}admin
     * @param data 标准入参
     * @return 原始返回值
     */
    public static String getRuleInvoke(String param,String data){
        RuleInvoke target = Feign.builder()
                .decoder(stringDecoder)
                .logger(new Logger.ErrorLogger())
                .options(defaultOptions)
                .logLevel(Logger.Level.FULL)
                .target(RuleInvoke.class, ruleURL);
        return target.queryRuleJsonValue(param,data);
    }

    /**
     * hisApi请求模板
     * @param param 如：serviceId{=}php_getSchByDepId{,}dataPackType{=}2{,}dataDesZip{=}00{,}userId{=}admin{,}password{=}admin
     * @param data 标准入参
     * @return 原始返回值
     */
    public static String getHisApiInvoke(String param,String data){
        HisApiInvoke target = Feign.builder()
                .decoder(stringDecoder)
                .logger(new Logger.ErrorLogger())
                .options(defaultOptions)
                .logLevel(Logger.Level.FULL)
                .target(HisApiInvoke.class, hisApiURL);
        return target.queryHisApiJsonValue(param,data);
    }

    /**
     * Hdbs请求模板
     * @param path 请求路径
     * @param param 标准入参
     * @return 原始返回值
     */
    public static String getHdbsInvoke(String path, Map param){
        HdbsInvoke target = Feign.builder()
                .decoder(stringDecoder)
                .encoder(formEncoder)
                .logger(new Logger.ErrorLogger())
                .options(defaultOptions)
                .logLevel(Logger.Level.FULL)
                .target(HdbsInvoke.class, HdbsURL);
        return target.queryHdbsJsonValue(path,param);
    }

    /**
     * src请求模板
     * @param path 请求路径
     * @param data 标准入参
     * @return 原始返回值
     */
    public static String getSrcInvoke(String path,String data){
        SrcInvoke target = Feign.builder()
                .decoder(stringDecoder)
                .logger(new Logger.ErrorLogger())
                .options(defaultOptions)
                .logLevel(Logger.Level.FULL)
                .target(SrcInvoke.class, srcURL);
        return target.querySrcJsonValue(path,data);
    }

    /**
     * 标准POST 请求模板 - 不带方法
     * @param url 请求地址
     * @param param form入参
     * @return 原始返回值
     */
    public static String postWithForm(String url,Map<String,?> param){
        return postWithForm(url,"",param);
    }

    /**
     * 标准POST 请求模板
     * @param url 请求地址
     * @param path 请求方法
     * @param param form入参
     * @return 原始返回值
     */
    public static String postWithForm(String url,String path,Map<String,?> param){
        StanderInvoke target = Feign.builder()
                .decoder(stringDecoder)
                .logger(new Logger.ErrorLogger())
                .options(defaultOptions)
                .logLevel(Logger.Level.FULL)
                .target(StanderInvoke.class, url);
        return target.postWithForm(path,param);
    }

    /**
     * 标准POST 请求模板 带超时时间
     * @param url 请求地址
     * @param path 请求方法
     * @param param form入参
     * @param connectTimeOut 连接超时时间 单位 毫秒
     * @param readTimeOut 读取超时时间 单位 毫秒
     * @return 原始返回值
     */
    public static String postWithForm(String url,String path,Map<String,?> param,long connectTimeOut,long readTimeOut){
        Request.Options options = new Request.Options(connectTimeOut,TimeUnit.MILLISECONDS,readTimeOut,TimeUnit.MILLISECONDS,true);
        StanderInvoke target = Feign.builder()
                .decoder(stringDecoder)
                .logger(new Logger.ErrorLogger())
                .options(options)
                .logLevel(Logger.Level.FULL)
                .target(StanderInvoke.class, url);
        return target.postWithForm(path,param);
    }

    /**
     * 标准POST json请求模板
     * @param url 请求地址
     * @param path 请求方法
     * @param body json字符串
     * @return 原始返回值
     */
    public static String postWithJson(String url,String path,String body){
        StanderInvoke target = Feign.builder()
                .decoder(stringDecoder)
                .logger(new Logger.ErrorLogger())
                .options(defaultOptions)
                .logLevel(Logger.Level.FULL)
                .target(StanderInvoke.class, url);
        return target.postWithJson(path,body);
    }

    public static String getWithMap(String url,String path,Map<String,?> param){
        StanderInvoke target = Feign.builder()
                .decoder(stringDecoder)
                .logger(new Logger.ErrorLogger())
                .options(defaultOptions)
                .logLevel(Logger.Level.FULL)
                .target(StanderInvoke.class, url);
        return target.getWithMap(path,param);
    }
    public static String getWithMap(String url,String path,Map<String,?> param,long connectTimeOut,long readTimeOut){
        Request.Options options = new Request.Options(connectTimeOut,TimeUnit.MILLISECONDS,readTimeOut,TimeUnit.MILLISECONDS,true);
        StanderInvoke target = Feign.builder()
                .decoder(stringDecoder)
                .logger(new Logger.ErrorLogger())
                .options(options)
                .logLevel(Logger.Level.FULL)
                .target(StanderInvoke.class, url);
        return target.getWithMap(path,param);
    }


    /**
     * 标准POST json请求模板 带超时时间
     * @param url 请求地址
     * @param path 请求方法
     * @param body json字符串
     * @param connectTimeOut 连接超时时间 单位 毫秒
     * @param readTimeOut 读取超时时间 单位 毫秒
     * @return 原始返回值
     */
    public static String postWithJson(String url,String path,String body,long connectTimeOut,long readTimeOut){
        Request.Options options = new Request.Options(connectTimeOut,TimeUnit.MILLISECONDS,readTimeOut,TimeUnit.MILLISECONDS,true);
        StanderInvoke target = Feign.builder()
                .decoder(stringDecoder)
                .logger(new Logger.ErrorLogger())
                .options(options)
                .logLevel(Logger.Level.FULL)
                .target(StanderInvoke.class, url);
        return target.postWithJson(path,body);
    }

    /**
     * 出参解析器 把响应结果转换成 com.alibaba.fastjson.JSONObject
     */
    static class JsonDecode implements Decoder{
        @Override
        public Object decode(Response response, Type type) throws IOException, FeignException {
            JSONObject result = new JSONObject();
            int status = response.status();
            if(status == 200){
                Response.Body body = response.body();
                String s = Util.toString(body.asReader(Util.UTF_8));
                return JSONObject.parseObject(s);

            }else {
                result.put("status",status);
                return result;
            }
        }
    }

    /**
     * rule 请求标准
     */
    private interface RuleInvoke {
        /**
         * rule Api接口
         * @param param
         * @param data
         * @return json对象
         */
        @RequestLine("POST ")
        @Body("param={param}&data={data}")
        String queryRuleJsonValue(@Param("param") String param, @Param("data") String data);
    }

    /**
     * hisApi 请求标准
     */
    private interface HisApiInvoke {
        /**
         * his Api接口
         * @param param
         * @param data
         * @return json对象
         */
        @RequestLine("POST ")
        @Body("param={param}&data={data}")
        String queryHisApiJsonValue(@Param("param") String param, @Param("data") String data);
    }

    /**
     * hdbs 请求标准
     */
    private interface HdbsInvoke {
        /**
         * HDBS Api接口
         * @param param
         * @return json对象
         */
        @RequestLine("POST {path}")
        @Headers("Content-Type: application/x-www-form-urlencoded")
        String queryHdbsJsonValue(@Param("path") String path, Map<String, ?> param);
    }

    /**
     * src 请求标准
     */
    private interface SrcInvoke {
        /**
         * src Api接口
         * @param data
         * @return json对象
         */
        @RequestLine("POST {path}")
        @Body("data={data}")
        String querySrcJsonValue(@Param("path") String path, @Param("data") String data);
    }

    /**
     * 通用请求
     */
    private interface StanderInvoke {
        @RequestLine("POST {path}")
        @Body("{body}")
        @Headers("Content-Type:{contentType}")
        String post(@Param("path") String path, @Param("body") String body, @Param("contentType") String contentType);

        @RequestLine("POST {path}")
        @Headers("Content-Type: application/x-www-form-urlencoded")
        String postWithForm(@Param("path") String path, Map<String, ?> param);

        @RequestLine("POST {path}")
        @Body("{body}")
        @Headers("Content-Type: application/json")
        String postWithJson(@Param("path") String path, @Param("body") String body);

        @RequestLine("GET {path}")
        String getWithMap(@Param("path") String path, @QueryMap Map<String, ?> param);
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("accountUserId", String.valueOf(222911797));
        map.put("depId", String.valueOf(200038613));
        map.put("cid", "2");
        String withMap = getWithMap("http://127.0.0.1:8082", "/get" ,map );
        System.out.println(withMap);
    }
}
