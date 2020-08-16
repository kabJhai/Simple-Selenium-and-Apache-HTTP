/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apachehttp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author kabil
 */
public class HttpCRUD {

    static CloseableHttpClient httpclient;

    public static void main(String[] args) throws URISyntaxException {
        try {
            httpclient = HttpClients.createDefault();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("CREATE:");
            create("Kabila Haile","Software Engineer");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("READ:");
            read("1");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("UPDATE:");
            update("3","Kabila Haile","Software Engineer");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("DELETE:");
            delete("2");
            httpclient.close();
        } catch (IOException ex) {
            Logger.getLogger(HttpCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Create
    public static void create(String name,String job) throws IOException {
        HttpPost httpPost = new HttpPost("https://reqres.in/api/users");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("name", name));
        nvps.add(new BasicNameValuePair("job", job));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine());
            System.out.println("RESPONSE JSON");
            entity.writeTo(System.out);
            System.out.println("");
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
    }

    //Read
    public static void read(String page) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder("https://reqres.in/api/users/");
        builder.setParameter("page", page);
        HttpGet httpGet = new HttpGet(builder.build());
        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            HttpEntity entity = response.getEntity();
            System.out.println("RESPONSE JSON");
            entity.writeTo(System.out);
            System.out.println("");
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

    }

    //Update
    public static void update(String id,String name,String job) throws IOException {
        HttpPatch httpPatch = new HttpPatch("https://reqres.in/api/users/" + id);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("name",name));
        nvps.add(new BasicNameValuePair("job", job));
        httpPatch.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = httpclient.execute(httpPatch);

        try {
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine());
            System.out.println("RESPONSE JSON");
            entity.writeTo(System.out);
            System.out.println("");
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
    }

    //Update
    public static void delete(String id) throws IOException {
        HttpDelete httpDelete = new HttpDelete("https://reqres.in/api/users/" + id);
        CloseableHttpResponse response = httpclient.execute(httpDelete);
        try {
            System.out.println(response.getStatusLine());
            if (response.getStatusLine().getStatusCode() == 204) {
                System.out.println("Deleted Succesfully");
            } else {
                System.out.println("Deleted not Succesful");
            }
        } finally {
            response.close();
        }
    }

}
