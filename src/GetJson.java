import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GetJson {

    public static void main(String[] args) throws Exception
    {
        URL aux_url = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnits?fields=:all&filter=level:eq:1");
       URLConnection uc = aux_url.openConnection();
        String userpass = "vmurciano" + ":" + "Vict0r2017#";
        String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
        uc.setRequestProperty ("Authorization", basicAuth);
        InputStream in = uc.getInputStream();



        String LastUpdate2;

        try (InputStream is = in;
             JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();

            JsonArray hola = obj.getJsonArray("organisationUnits");
            JsonObject hola2 = hola.getJsonObject(0);
            System.out.println(hola2.getString("lastUpdated"));



            /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss");
            Date date2 = formatter.parse(obj.getString("lastUpdated").replaceAll("Z$", "+0000"));
            System.out.println(date2);
            //Date LastUpdate3 = formatt;
            System.out.printf("Fecha obtenida del SVN: ");
            */
            System.out.println("-----------");
        } catch (IOException e) {
            e.printStackTrace();
        }


        URLConnection uc2 = aux_url.openConnection();
        String basicAuth2 = "Basic " + new String(new Base64().encode(userpass.getBytes()));
        uc2.setRequestProperty ("Authorization", basicAuth2);
        InputStream in2 = uc2.getInputStream();

        OutputStream outputStream = new FileOutputStream("C:/Users/Victor/Desktop/f/hola.json"); // path y nombre del nuevo fichero creado
        byte[] b = new byte[2048];
        int longitud;

        while ((longitud = in2.read(b)) != -1) {
            outputStream.write(b, 0, longitud);
        }



    }
}


