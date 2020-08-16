/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apachehttp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
    public static void main(String [] args){
        try {
            httpclient = HttpClients.createDefault();
            create();
            httpclient.close();
        } catch (IOException ex) {
            Logger.getLogger(HttpCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Create
    public static void create() throws IOException{
            HttpPost httpPost = new HttpPost("https://reqres.in/api/users");
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("name", "Kabila Haile"));
            nvps.add(new BasicNameValuePair("job", "Software Engineer"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                System.out.println(response.getStatusLine());
                HttpEntity entity2 = response.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
                System.out.println(response.getStatusLine());
            } finally {
                response.close();
            }   
    }
    
}
