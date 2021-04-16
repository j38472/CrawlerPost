package httpClientPost;


import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 58 同城  字体加密
 */
public class myBase64 {

    public static final String UTF_8 = "UTF-8";
    public static Base64.Encoder encoder;
    //即为安全的编码方式，替换“+” “/” “-”为“_”
    public static Base64.Encoder urlEncoder;
    public static Base64.Decoder decoder;
    public static Base64.Decoder urlDecoder;

    static {
        encoder = Base64.getEncoder();
        urlEncoder = Base64.getUrlEncoder();
        decoder = Base64.getDecoder();
        urlDecoder = Base64.getUrlDecoder();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

//        Base64.getEncoder();
        myBase64 myBase64 = new myBase64();
        myBase64.getHtml("https://sz.58.com/searchjob/?pts=1618477782376&param8516=1");


//         decoder = Base64.getDecoder();
//         encoder = Base64.getEncoder();
//         String text = "字串文字";
//         byte[] textByte = text.getBytes("UTF-8");
////编码
//         String encodedText = encoder.encodeToString(textByte);
//        System.out.println(encodedText);
////解码
//        System.out.println(new String(decoder.decode(encodedText), "UTF-8"));


    }

    public String getDecoder(String str) {
        String decoderStr = "";
        try {
            byte[] base64decodedBytes = Base64.getDecoder().decode(str);
            System.out.println("----------------------------------");
            //  ASCII  ISO8859-1  GB2312  GBK  GB18030  UTF-16  UTF-8  UNICODE  UnicodeLittle UnicodeBig
            // BIG5  Shift_JIS  ISO-8859-2
            decoderStr = new String(decoder.decode(str.getBytes()), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decoderStr;
    }


    public String getHtml(String url) {
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
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpGet.setHeader("Cache-Control", "no-cache");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpGet.setHeader("Cookie", "f=n; 58home=hk; id58=c5/nfGB4AqNXD4sUAxP1Ag==; city=hk; wmda_uuid=11e3f7b6e303184d23d9c83025be1612; wmda_new_uuid=1; 58tj_uuid=c0044b19-b44c-4bce-a0a5-743932b8c560; new_uv=1; als=0; f=n; sessionid=707aa4fd-fc3d-4706-a8ab-1300d2e0d2ce; param8616=0; param8716kop=1; wmda_visited_projects=%3B1731916484865%3B10104579731767; xxzl_deviceid=fAXSNsTqJrOMwTagus1g3nG%2BHVgABa9m5kDCCG20gYLy%2FR0Tk8wfv%2F%2BPOis6%2FI3n; www58com=\"UserID=61872910779402&UserName=wzhkbwg3h\"; 58cooper=\"userid=61872910779402&username=wzhkbwg3h\"; 58uname=wzhkbwg3h; Hm_lvt_a3013634de7e7a5d307653e15a0584cf=1618477813; ljrzfc=1; isSmartSortTipShowed=true; PPU=\"UID=61872910779402&UN=wzhkbwg3h&TT=28e5c94c6e3d37919c32efc16ab03332&PBODY=hQszr9I9yOwctfFsJqk6YdDbhnm6YF-dSCpSUfjmKXOUA0zLS0rDrsIdxWychMrTGaangXUMS3yTjNSsJqknBuZqu3EpavGN4C6oUbprDYQWF_N1tloq4pOl87SvqbNecRbTzXYkNjBz51hM6DFUIClD3Eli9LcjwKfKl0rWYFM&VER=1\"; JSESSIONID=A0B4D1824C62788B389625764793E5DE; Hm_lpvt_a3013634de7e7a5d307653e15a0584cf=1618478524; xxzl_cid=3ec8343d7af2497cbb208323b585a189; xzuid=9c86fb00-51a1-456e-828d-dd03e5b11bab");
        httpGet.setHeader("Host", "sz.58.com");
        httpGet.setHeader("Pragma", "no-cache");
        httpGet.setHeader("sec-ch-ua", "\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        httpGet.setHeader("sec-ch-ua-mobile", "?0");
        httpGet.setHeader("Sec-Fetch-Dest", "empty");
        httpGet.setHeader("Sec-Fetch-Mode", "cors");
        httpGet.setHeader("Sec-Fetch-Site", "same-origin");
        httpGet.setHeader("Referer", "https://sz.58.com/searchjob/?pts=1618477782376&param8516=1&param8427=1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36X-Requested-With: XMLHttpRequest");

        httpGet.setConfig(requestConfig);
        System.out.println("执行请求-----------");
        String HtmlPage = "";
        try {
            response = httpClient.execute(httpGet);
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
            System.out.println("get ,请求出问题了！！！！！！！！！！！");
        }
        System.out.println("状态码::::::" + statusCode);
        System.out.println(HtmlPage);

        /**
         * 正则匹配出base64的加密 数据
         */
        String strPat = "base64,(.*?)\\)";
        Pattern pattern = Pattern.compile(strPat);
        // 创建匹配给定输入与此模式的匹配器。
        Matcher matcher = null;
        try {
            matcher = pattern.matcher(HtmlPage);
        } catch (NullPointerException e) {
            System.out.println("这个匹配的字符串是空的");
        }
        String strJG = null;
        if (matcher != null) {
            while (matcher.find()) {
                //查找到符合的即输出
                strJG = matcher.group();
            }
        }
        System.out.println("打印一下strJG:::" + strJG);
        strJG = strJG.substring(7, strJG.length() - 1);
        System.out.println("最后打印一下strJG:::" + strJG);

        System.out.println(getDecoder(strJG));




        return "";
    }


}
