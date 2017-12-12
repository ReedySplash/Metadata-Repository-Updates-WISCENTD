import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class GetJson {

    public static void main(String[] args) throws Exception
    {
        URL url = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        URLConnection uc = url.openConnection();
        String userpass = "vmurciano" + ":" + "Vict0r2017#";
        String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
        uc.setRequestProperty ("Authorization", basicAuth);
        InputStream in = uc.getInputStream();

        try (InputStream is = in;
        JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            System.out.println(obj.getString("id"));
            System.out.println("-----------");
            System.out.println(obj.getString("lastUpdated"));
            System.out.println("-----------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
