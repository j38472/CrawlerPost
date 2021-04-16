package httpClientPost;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * @author 21 4 15  cyh
 */
public class PostOne {
    final static String URL = "https://fanyi.youdao.com/translate_o?smartresult=dict&smartresult=rule";
    final static PostOne POST_ONE = new PostOne();


    public static void main(String[] args) {
//        postOne.ClientGetHtmlPage(url,"https://fanyi.youdao.com");

        ;

//        postOne.getsalt("");

        PostOne p = new PostOne();
        p.ClientGetHtmlPage("https://fanyi.youdao.com/translate_o?smartresult=dict&smartresult=rule","https://fanyi.youdao.com/");
//        System.out.println(p.getSignMd5("", ""));


    }


    public void ClientGetHtmlPage(String url, String referer) {
        String strE = "cat";

        //    static HttpHost host = new HttpHost("115.226.138.33", 58979);
        RequestConfig requestConfig = RequestConfig.custom()
//                .setProxy(host)
                .setSocketTimeout(15000)
                .setConnectTimeout(15000)
                .setConnectionRequestTimeout(15000)
                .build();
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;

        System.out.println("获取页面数据-------URL为：：：：：" + url);
        //client 获取页面信息
        httpClient = HttpClients.createDefault();
        // 创建get请求
        HttpPost HttpPost = new HttpPost(url);
        // 设置头
//        Accept: application/json, text/javascript, */*; q=0.01

        HttpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        HttpPost.setHeader("Accept-Encoding","gzip, deflate, br");
        HttpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8");
        HttpPost.setHeader("Cache-Control","no-cache");
        HttpPost.setHeader("Connection","keep-alive");
//        HttpPost.setHeader("Content-Length","241"); //不知道这里为啥带上这个参数就请求不到数据  差点就崩溃了  草了啊  ？？？
        HttpPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        HttpPost.setHeader("Cookie","OUTFOX_SEARCH_USER_ID_NCOO=523494372.1483736; OUTFOX_SEARCH_USER_ID=\"501702399@10.108.160.17\"; _ga=GA1.2.180498389.1617545383; P_INFO=13691347124|1617849888|1|youdaonote|00&99|null&null&null#hen&410100#10#0|&0|null|13691347124; JSESSIONID=aaa7c7xKBQUHB6OJkGoJx; ___rl__test__cookies=1618474097803");
        HttpPost.setHeader("Host","fanyi.youdao.com");
        HttpPost.setHeader("Origin","https://fanyi.youdao.com");
        HttpPost.setHeader("Pragma","no-cache");
        HttpPost.setHeader("sec-ch-ua","\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        HttpPost.setHeader("sec-ch-ua-mobile","?0");
        HttpPost.setHeader("Sec-Fetch-Dest","empty");
        HttpPost.setHeader("Sec-Fetch-Mode","cors");
        HttpPost.setHeader("Sec-Fetch-Site","same-origin");
        HttpPost.setHeader("Referer", "https://fanyi.youdao.com");
        HttpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36X-Requested-With: XMLHttpRequest");



        HttpPost.setConfig(requestConfig);

        String lts = POST_ONE.getlts();
        String salt = POST_ONE.getsalt(lts);
        String sign = POST_ONE.getSignMd5(strE, salt);
        System.out.println(lts);
        System.out.println(salt);
        System.out.println(sign);

        //声明list 里面封装表单参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("i", strE));
        params.add(new BasicNameValuePair("from", "AUTO"));
        params.add(new BasicNameValuePair("to", "AUTO"));
        params.add(new BasicNameValuePair("smartresult", "dict"));
        params.add(new BasicNameValuePair("client", "fanyideskweb"));
        params.add(new BasicNameValuePair("salt", salt));
        params.add(new BasicNameValuePair("sign", sign));
        params.add(new BasicNameValuePair("lts", lts));
        params.add(new BasicNameValuePair("bv", "77f6f59a0018c726a082dbc8637b193e"));
        params.add(new BasicNameValuePair("doctype", "json"));
        params.add(new BasicNameValuePair("version", "2.1"));
        params.add(new BasicNameValuePair("keyfrom", "fanyi.web"));
        params.add(new BasicNameValuePair("action", "FY_BY_CLICKBUTTION"));





        // 执行请求
        System.out.println("执行请求-----------");
        String HtmlPage = "";
        try {

            // 创建表单Entity 对象
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"UTF-8");
            //设置 表单 Entity 对象到post 请求中
            HttpPost.setEntity(formEntity);



            response = httpClient.execute(HttpPost);
            entity = response.getEntity();
            HtmlPage = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.getStackTrace();
        }

        StatusLine statusLine = null;
        int statusCode = 0;
        try {
            statusLine = response.getStatusLine();
            statusCode = statusLine.getStatusCode();
        } catch (NullPointerException e) {
            System.out.println("post ,请求出问题了！！！！！！！！！！！");
        }
        System.out.println("状态码::::::" + statusCode);

        System.out.println("HtmlPage:::::::  " + HtmlPage);
    }


    public String getsalt(String str) {
        Random rand = new Random();
        int intSleep = rand.nextInt(10);
        return str + intSleep;
    }

    public String getlts() {
//        new Date().getTime();
        String s =Long.toString(System.currentTimeMillis());
        return s;
    }

    // 带秘钥加密
    public String getSignMd5(String e, String i) {
        // 加密后的字符串
        String md5str = DigestUtils.md5Hex("fanyideskweb" + e + i + "Tbh5E8=q6U3EXe+&L[4c@"); // 生成使用盐的 MD5 密文
//        System.out.println("MD5加密后的字符串为:" + md5str);
        return md5str;
    }

    // 不带秘钥加密
    public String md52(String text) {
        // 加密后的字符串
        String md5str = DigestUtils.md5Hex(text); // 生成不加盐的 MD5 密文
//        System.out.println("MD52加密后的字符串为:" + md5str + "\t长度：" + md5str.length());
        return md5str;
    }

}
