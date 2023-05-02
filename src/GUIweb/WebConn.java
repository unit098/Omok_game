package GUIweb;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import javax.swing.JOptionPane;

import org.json.*;

public class WebConn {
    OmokuiWeb ui;
    
    public JSONObject info(String url){
        try {
        var infourl = new URL(url+"/info/");
            URLConnection conn = infourl.openConnection();
            conn.setConnectTimeout(7000);

            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            if (HttpURLConnection.HTTP_OK == httpConn.getResponseCode()) {
                var is = httpConn.getInputStream();
                var in = new BufferedReader(new InputStreamReader(is));
                JSONObject info = new JSONObject(in.readLine());
                in.close();
                return info;
            }
                return new JSONObject("");
        } catch(UnknownHostException e1){JOptionPane.showMessageDialog(null, "Bad internet");}
        catch (Exception e1) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Bad URL");
            ui.url=null;
            ui.b2.removeAllItems();
            ui.b2.update(ui.b2.getGraphics());
        }
        return new JSONObject("");

    }
    public JSONObject play (String url, String pid, int[] cords){
        try {
            URL purl = new URL(url+ "/play/?pid="+pid+"&x="+cords[0]+"&y="+cords[1]);
                        URLConnection conn = purl.openConnection();

                        HttpURLConnection httpConn = (HttpURLConnection) conn;
                        httpConn.setAllowUserInteraction(false);
                        httpConn.setInstanceFollowRedirects(true);
                        httpConn.setRequestMethod("GET");
                        httpConn.connect();
                        if (HttpURLConnection.HTTP_OK == httpConn.getResponseCode()) {
                            var is = httpConn.getInputStream();
                            var in = new BufferedReader(new InputStreamReader(is));
                            JSONObject info = new JSONObject(in.readLine());
                            return info;
                        }
        } catch (JSONException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new JSONObject("");
    }

public JSONObject Game(String url){
    try {
        URL gsurl = new URL(url+ "/new/?strategy="+ui.b2.getSelectedItem());
        URLConnection conn = gsurl.openConnection();

        HttpURLConnection httpConn = (HttpURLConnection) conn;
        httpConn.setAllowUserInteraction(false);
        httpConn.setInstanceFollowRedirects(true);
        httpConn.setRequestMethod("GET");
        httpConn.connect();
        if (HttpURLConnection.HTTP_OK == httpConn.getResponseCode()) {
            var is = httpConn.getInputStream();
            var in = new BufferedReader(new InputStreamReader(is));
            JSONObject info = new JSONObject(in.readLine());
            return info;
        }

    } catch (MalformedURLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (ProtocolException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} catch (JSONException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
return new JSONObject("");
}

    public WebConn(OmokuiWeb ui) {
        this.ui = ui;
    }
}
