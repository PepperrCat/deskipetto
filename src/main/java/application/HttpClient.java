package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class HttpClient {

    private static final String USER_AGENT = "Mozilla/5.0";

//    public static void main(String[] args) throws Exception {
//
//        HttpClient http = new HttpClient();
//
//        System.out.println("Testing 1 - Send Http GET request");
//        Scanner in=new Scanner(System.in);
//        String str,out;
//        while (! (str=in.nextLine()).equals("quit")){
//            out= http.sendGet(str);
//            System.out.println(out);
//        }
//
////        System.out.println("\nTesting 2 - Send Http POST request");
////        http.sendPost();
//
//    }

    // HTTP GET请求
    public static String sendGet(String str) throws Exception {

//        String url = "http://www.google.com/search?q=developer";
        String url = "https://api.ownthink.com/bot?spoken="+str;

        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        //添加请求头
        request.addHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " +
//                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        JSONObject jsonObject=new JSONObject(result.toString());

//        System.out.println(jsonObject.getJSONObject("data").getJSONObject("info").getString("text"));
        return jsonObject.getJSONObject("data").getJSONObject("info").getString("text");

    }

    // HTTP POST请求
//    private void sendPost() throws Exception {
//
//        String url = "https://selfsolve.apple.com/wcResults.do";
//
//        HttpClient client = new DefaultHttpClient();
//        HttpPost post = new HttpPost(url);
//
//        //添加请求头
//        post.setHeader("User-Agent", USER_AGENT);
//
//        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//        urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
//        urlParameters.add(new BasicNameValuePair("cn", ""));
//        urlParameters.add(new BasicNameValuePair("locale", ""));
//        urlParameters.add(new BasicNameValuePair("caller", ""));
//        urlParameters.add(new BasicNameValuePair("num", "12345"));
//
//        post.setEntity(new UrlEncodedFormEntity(urlParameters));
//
//        HttpResponse response = client.execute(post);
//        System.out.println("\nSending 'POST' request to URL : " + url);
//        System.out.println("Post parameters : " + post.getEntity());
//        System.out.println("Response Code : " +
//                response.getStatusLine().getStatusCode());
//
//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent()));
//
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//
//        System.out.println(result.toString());
//
//    }

//    private void sendGet(String str) {
////        String url = "http://www.google.com/search?q=developer";
//        String url = "https://api.ownthink.com/bot?appid=xiaosi&spoken="+str;
//
//        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet(url);
//
//        //添加请求头
//        request.addHeader("User-Agent", USER_AGENT);
//
//        HttpResponse response = client.execute(request);
//
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " +
//                response.getStatusLine().getStatusCode());
//
//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent()));
//
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//
//        System.out.println(result.toString());
//
//    }

}