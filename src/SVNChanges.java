import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.*;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



public class SVNChanges {

    private ArrayList<String> Paths;
    private ArrayList<String> SVNCredentials;
    private String[][] Sets;
    private ArrayList<String> Groups = null;
    private boolean creado;


    public static void main(String[] argv) throws IOException, SVNException {

        //Set up connection protocols support:
        SVNChanges prueba = new SVNChanges();
        prueba.CheckoutOrUpdate();
        //prueba.UpdateIndicatorsGroupsSets();
        prueba.UpdateProgramIndicatorGroups();
        prueba.UpdateProgramIndicators();
       // prueba.UpdateIndicators();
       // prueba.UpdateIndicatorGroups();
       /* prueba.UpdateOrgGroupSetsStructure();
        prueba.UpdateAllOrgGroups();
        prueba.UpdateOrgUniteStructure();
        prueba.UpdateOrgUnitLevel1();
        prueba.UpdateOrgUnitLevel2();
        prueba.UpdateOrgUnitLevel3();
        prueba.UpdateOrgUnitLevel4();
        prueba.UpdateOrgUnitLevel5();
        prueba.UpdateOrgUnitLevel6();
        prueba.UpdateOrgUnitLevel7();
        prueba.UpdateOrgUnitLevel8();
        prueba.UpdateAllDataElementGroups();
        */
        prueba.Commit();
    }




    //Org Unit Groups
    public void UpdateOrgGroupSetsStructure() throws IOException, SVNException {
        GetPaths("_GS", "_GS");
        File Repository = new File(Paths.get(1));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3), Repository, url_aux, "/" + Paths.get(0) + ".json");
    }

    public void UpdateAllOrgGroups() throws IOException, SVNException {
        GetPaths("_GS", "_GS");
        GetAllSets(new URL(Paths.get(4) + Paths.get(2)), Paths.get(0));
        for (int i = 0; i < Sets.length; ++i) {
            CreateDirectory(true, i);
            int j = 1;
            while (Sets[i][j] != null){
                File Repository = new File(Paths.get(1) + "/" + Sets[i][0]);
                GetPaths("_G", "_G");
                URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + Sets[i][j]);
                UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3) + "/" + Sets[i][0], Repository, url_aux, "/" + Sets[i][j] + "-",i,false,false);
                ++j;
            }
        }
        Paths.add("Others");
        CreateDirectory(false, 0);
        GetPaths("_G", "_G");
        GetAllGroups(new URL(Paths.get(4) + Paths.get(2)));
        for (int i = 0; i < Groups.size(); ++i) {
            File Repository = new File(Paths.get(1) + "/Others");
            URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + Groups.get(i));
            UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3) + "/Others",Repository,url_aux,"/" + Groups.get(i) + "-",0,true,false);
        }
    }

    //Data Elements
    public void UpdateDataElementGroupsSetsStructure() throws IOException, SVNException {
        GetPaths("_DGS", "_DGS");
        File Repository = new File(Paths.get(1));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3), Repository, url_aux, "/" + Paths.get(0) + ".json");
    }

    public void UpdateAllDataElementGroups() throws IOException, SVNException {
        Groups = null;
        //GetPaths("_DGS", "_DGS");
        //GetAllSets(new URL(Paths.get(4) + Paths.get(2)), Paths.get(0));
       /* for (int i = 0; i < Sets.length; ++i) {
            CreateDirectory(true, i);
            int j = 1;
            while (Sets[i][j] != null){
                File Repository = new File(Paths.get(1) + "/" + Sets[i][0]);
                GetPaths("_DG", "_DG");
                URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + Sets[i][j]);
                UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3) + "/" + Sets[i][0], Repository, url_aux, "/" + Sets[i][j] + "-",i,false);
                ++j;
            }
        }*/

        GetPaths("_DG", "_DG");
        Paths.add("Others");
        CreateDirectory(false, 0);
        GetPaths("_DG", "_DG");
        GetAllGroups(new URL(Paths.get(4) + Paths.get(2)));
        for (int i = 0; i < Groups.size(); ++i) {
            File Repository = new File(Paths.get(1) + "/Others");
            URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + Groups.get(i));
            UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3) + "/Others",Repository,url_aux,"/" + Groups.get(i) + "-",0,true,false);
        }
    }



    //Indicators
    public void UpdateIndicators() throws IOException, SVNException {
        Groups = null;
        GetPaths("_I", "_I");
        CreateDirectory(false, -1);
        GetAllGroups(new URL(Paths.get(4) + Paths.get(2)));
        for (int i = 0; i < Groups.size(); ++i) {
            File Repository = new File(Paths.get(1));
            URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + Groups.get(i));
            UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3),Repository,url_aux,"/" + Groups.get(i) + "-",0,false,false);
        }
    }

    public void UpdateIndicatorsGroupsSets() throws IOException, SVNException {
        GetPaths("_IGS", "_IGS");
        CreateDirectory(false,-1);
        File Repository = new File(Paths.get(1));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3), Repository, url_aux, "/" + Paths.get(0) + ".json");
    }

    public void UpdateIndicatorGroups() throws IOException, SVNException {
        GetPaths("_IGS", "_IGS");
        GetAllSets(new URL(Paths.get(4) + Paths.get(2)), Paths.get(0));
        GetPaths("_IG", "_IG");
        for (int i = 0; i < Sets.length; ++i) {
            creado = false;
            CreateDirectory(true, i);
            int j = 1;
            while (Sets[i][j] != null){
                File Repository = new File(Paths.get(1) + "/" + Sets[i][0]);
                GetPaths("_IG", "_IG");
                URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + Sets[i][j]);
                UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3) + "/" + Sets[i][0], Repository, url_aux, "/" + Sets[i][j] + "-",i,false,false);
                ++j;
            }
        }
        GetPaths("_IG", "_IG");
        Paths.add("Others");
        CreateDirectory(false, 0);
        GetPaths("_IG", "_IG");
        GetAllGroups(new URL(Paths.get(4) + Paths.get(2)));
        for (int i = 0; i < Groups.size(); ++i) {
            File Repository = new File(Paths.get(1) + "/Others");
            URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + Groups.get(i));
            UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3) + "/Others",Repository,url_aux,"/" + Groups.get(i) + "-",0,true,false);
        }
    }




    //ProgramIndicatorGroups

    public void UpdateProgramIndicatorGroups() throws IOException, SVNException {
        ProgramIndicators("_PIG");
    }

    public void UpdateProgramIndicators() throws IOException, SVNException {
        ProgramIndicators("_PI");
    }

    private void ProgramIndicators(String Type) throws IOException, SVNException {
        Groups = null;
        GetPaths(Type, Type);
        CreateDirectory(false, -1);
        GetAllGroups(new URL(Paths.get(4) + Paths.get(2)));
        for (int i = 0; i < Groups.size(); ++i) {
            File Repository = new File(Paths.get(1));
            URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + Groups.get(i));
            UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3),Repository,url_aux,"/" + Groups.get(i) + "-",0,true,false);
        }
    }


    //UPDATE ORG_UNIT_LVLs STRUCTURE
    public void UpdateOrgUniteStructure() throws IOException, SVNException, FileNotFoundException {
        GetPaths("_S", "_S");
        File Repository = new File(Paths.get(1));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3), Repository, url_aux, "/org-unit-levels.json");

    }


    //UPDATE FOR ORG_UNIT_LVLs
    public void UpdateOrgUnitLevel1() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_1", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 1));
        File Repository = new File(Paths.get(1) +"/"+ Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/1-who-global.json");
    }

    public void UpdateOrgUnitLevel2() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_2", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 2));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/2-who-regions.json");
    }

    public void UpdateOrgUnitLevel3() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_3", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 3));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL((Paths.get(4) + Paths.get(2)));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/3-who-member-states.json");
    }

    public void UpdateOrgUnitLevel4() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_4", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 4));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/4-level1.json");
    }

    public void UpdateOrgUnitLevel5() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_5", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 5));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/5-level2.json");
    }

    public void UpdateOrgUnitLevel6() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_6", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 6));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/6-level3.json");
    }

    public void UpdateOrgUnitLevel7() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_7", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 7));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/7-level4.json");
    }

    public void UpdateOrgUnitLevel8() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_8", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 8));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/8-level8.json");
    }


    private void CreateDirectory(boolean Different, int i) throws IOException {
        boolean success;
        if (Different) success = (new File(Paths.get(1) + "/" + Sets[i][0])).mkdirs();
        else if (i == -1 && !Different) success = new File(Paths.get(1)).mkdirs();
        else success = new File(Paths.get(1) +"/"+ Paths.get(6)).mkdirs();
        if (success) {
            creado = true;
            BufferedWriter out = null;
            try {
                FileWriter fstream = new FileWriter("UpdatePath.txt", true); //true tells to append data.
                out = new BufferedWriter(fstream);
                if (Different) out.write(Paths.get(1) + "/" + Sets[i][0]);
                else if (i == -1 && !Different) out.write(Paths.get(1));
                else out.write(Paths.get(1) + "/" + Paths.get(6));
                out.newLine();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }

    private String GetOrgUnitLevelName(URL DHIS2url, int Level) throws IOException {
        URLConnection uc = initConnectionToDHIS2(DHIS2url);
        InputStream in = uc.getInputStream();
        boolean actualizar = false;
        try (InputStream is = in; JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            JsonArray OrgLevels = obj.getJsonArray("organisationUnitLevels");
            for (int i = 0; i < OrgLevels.size(); ++i) {
                JsonObject level = OrgLevels.getJsonObject(i);
                if (Objects.equals(level.getInt("level"), Level)) return level.getString("name");
            }
        }
        System.out.println("Level not founded");
        System.exit(1);
        return null;
    }

    private void GetAllGroups(URL DHIS2url) throws IOException {
        if (Groups == null) Groups = new ArrayList<>();
        URLConnection uc = initConnectionToDHIS2(DHIS2url);
        InputStream in = uc.getInputStream();
        try (InputStream is = in; JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            JsonArray OrgGroups = obj.getJsonArray(Paths.get(0)+"s");
            for (int i = 0; i < OrgGroups.size(); ++i) {
                JsonObject Group = OrgGroups.getJsonObject(i);
                String id = Group.getString("id");
                if (Groups != null && Groups.contains(id)) {
                    Groups.remove(id);
                }
                else {
                    Groups.add(id);
                }
            }
        }
    }

    private void GetPaths(String Type, String Type2) throws IOException {
        //Initialize Properties file
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream("./config.properties");
            prop.load(is);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        this.SVNCredentials = new ArrayList<>(2);
        this.SVNCredentials.add(prop.getProperty("UserName"));
        this.SVNCredentials.add(prop.getProperty("password"));
        this.Paths = new ArrayList<>(5);
        this.Paths.add(0, prop.getProperty("NameInJs" + Type2));
        this.Paths.add(1, prop.getProperty("PathFile" + Type2));
        this.Paths.add(2, prop.getProperty("UrlDHIS2" + Type));
        this.Paths.add(3, prop.getProperty("UrlSvnRe" + Type2));
        this.Paths.add(4, prop.getProperty("BaseDHIS2url"));
        this.Paths.add(5, prop.getProperty("BaseUrlSvnRe"));
    }

    private void GetAllSets(URL DHIS2url, String name) throws IOException {
        Groups = new ArrayList<>();
        URLConnection uc = initConnectionToDHIS2(DHIS2url);
        InputStream in = uc.getInputStream();
        try (InputStream is = in; JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            JsonArray OrgGroups = obj.getJsonArray(name);
            Sets = new String[OrgGroups.size()][50];
            for (int i = 0; i < OrgGroups.size(); ++i) {
                JsonObject Set = OrgGroups.getJsonObject(i);
                Sets[i][0] = Set.getString("name");
                JsonArray SetsID = Set.getJsonArray(Paths.get(0).substring(0,Paths.get(0).length()-4)+"s");
                for (int j = 0; j < SetsID.size(); ++j) {
                    JsonObject id = SetsID.getJsonObject(j);
                    Sets[i][j + 1] = id.getString("id");
                    Groups.add(id.getString("id"));
                }
            }
        }
    }




    private URLConnection initConnectionToDHIS2(URL DHIS2url) throws IOException {
        URLConnection uc = DHIS2url.openConnection();
        String userpass = "vmurciano" + ":" + "Vict0r2017#";
        String basicAuth = "Basic " + Base64.encode(userpass.getBytes());
        uc.setRequestProperty("Authorization", basicAuth);
        return uc;
    }

    private SVNRepository initConnectionToSVN(String SVN) {
        SVNRepositoryFactoryImpl.setup();
        SVNRepository repository = null;
        long startRevision = 0;
        long endRevision = -1; //HEAD (the latest) revision

        try {
            SVNRepositoryFactoryImpl.setup();
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(SVN));
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(this.SVNCredentials.get(0), this.SVNCredentials.get(1));
            repository.setAuthenticationManager(authManager);
        } catch (SVNException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return repository;
    }

    private void CheckoutOrUpdate() throws IOException, SVNException {
        GetPaths("_GE", "_GE");
        SVNRepository repository = initConnectionToSVN(Paths.get(5) + Paths.get(3));
        SVNClientManager ourClientManager = SVNClientManager.newInstance(null, repository.getAuthenticationManager());
        SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
        File f = new File(Paths.get(1)+Paths.get(3));
        if (!f.exists()) {
            updateClient.doCheckout(SVNURL.parseURIEncoded(Paths.get(5)+Paths.get(3)), f, SVNRevision.HEAD, SVNRevision.HEAD, true);
        }
        else {
            updateClient.doUpdate(f,SVNRevision.HEAD,true,false);
        }
    }

    private void UpdateGeneralSVN(String SVN, File Repository, URL DHIS2url, String json_name) throws SVNException, IOException {

        //CONEXION A SVN
        SVNRepository repository = initConnectionToSVN(SVN);
        Date lastUpdate = GetLastUpdateDate_SVN(SVN,Repository,repository);

        //PARTE DE JSON
        URLConnection uc = initConnectionToDHIS2(DHIS2url);
        String userpass = "vmurciano" + ":" + "Vict0r2017#";
        String basicAuth = "Basic " + Base64.encode(userpass.getBytes());
        InputStream in = uc.getInputStream();
        boolean actualizar = false;
        try (InputStream is = in;
             JsonReader rdr = Json.createReader(is)) {
            TimeZone.setDefault(new SimpleTimeZone(60 * 60 * 1000, "CET"));
            JsonObject obj = rdr.readObject();
            JsonArray hola = obj.getJsonArray(Paths.get(0));
            for (int i = 0; i < hola.size(); ++i) {
                JsonObject hola2 = hola.getJsonObject(i);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss");
                Date date2 = formatter.parse(hola2.getString("lastUpdated").replaceAll("Z$", "+0000"));
                if (lastUpdate == null || lastUpdate.before(date2)) {
                    actualizar = true;
                    break;
                }
            }
            if (actualizar) {
                    Actualizar(Repository,DHIS2url,json_name);
            }
            System.out.printf("Fecha obtenida del SVN: ");
            System.out.println(lastUpdate);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void UpdateGeneralSVN_WithoutForinJson(String SVN, File Repository, URL DHIS2url, String json_name, int i, boolean others, boolean DifferentDirectory) throws SVNException, IOException {

        //CONEXION A SVN
        SVNRepository repository = initConnectionToSVN(SVN);
        Date lastUpdate = GetLastUpdateDate_SVN(SVN,Repository,repository);
        //PARTE DE JSON

        String userpass = "vmurciano" + ":" + "Vict0r2017#";
        String basicAuth = "Basic " + Base64.encode(userpass.getBytes());
        URLConnection uc = initConnectionToDHIS2(DHIS2url);
        InputStream in = uc.getInputStream();

        boolean actualizar = false;
        try (InputStream is = in; JsonReader rdr = Json.createReader(is)) {
            TimeZone.setDefault(new SimpleTimeZone(60 * 60 * 1000, "CET"));            JsonObject obj = rdr.readObject();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss",Locale.ENGLISH);
            Date date2 = formatter.parse(obj.getString("lastUpdated").replaceAll("Z$", "+0000"));
            System.out.println("FECHA DEL JSON: "+date2);
            String id = json_name.substring(1,11);
            json_name = "/" +obj.getString("name").replace("/","-")+"-";
            json_name += id + ".json";
            json_name = json_name.replace(":","=");
            json_name = json_name.replaceAll("<","LessThan");
            json_name = json_name.replaceAll(">","MoreThan");

            if (lastUpdate == null || lastUpdate.before(date2)) {
                actualizar = true;
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        if (!DifferentDirectory) {
            Actualizar(Repository,DHIS2url,json_name);
        }

        else if (actualizar && !others) {
            Paths.set(3,Paths.get(3)+"/"+Sets[i][0]);
            Actualizar(Repository,DHIS2url,json_name);
            Paths.set(3,Paths.get(3).substring(0,Paths.get(3).length()-(2+Sets[i][0].length())));
        }
        else if(actualizar && others){
            Paths.set(3,Paths.get(3)+"/Others");
            Actualizar(Repository,DHIS2url,json_name);
            Paths.set(3,Paths.get(3).substring(0,Paths.get(3).length()-(7)));
        }

        System.out.printf("Fecha obtenida del SVN: ");
        System.out.println(lastUpdate);
    }

    private Date GetLastUpdateDate_SVN(String SVN, File Repository, SVNRepository repository) throws SVNException {
        long startRevision = 0;
        long endRevision = -1; //HEAD (the latest) revision
        Date lastUpdate = new Date();
        Collection logEntries = null;

        if(Repository.exists() && !creado) {
            logEntries = repository.log(new String[]{""}, null, startRevision, endRevision, true, true);
            for (Iterator entries = logEntries.iterator(); entries.hasNext(); ) {
                SVNLogEntry logEntry = (SVNLogEntry) entries.next();
                lastUpdate = logEntry.getDate();
            }
            return lastUpdate;
        }
        return null;
    }

    private void Actualizar(File Repository, URL DHIS2url, String json_name) throws IOException {
        URLConnection uc = initConnectionToDHIS2(DHIS2url);
        InputStream in2 = uc.getInputStream();
        String Repo_aux = Repository.toString();
        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter("UpdatePath.txt", true); //true tells to append data.
            out = new BufferedWriter(fstream);
            out.write(Repo_aux + json_name);
            out.newLine();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
        Repo_aux = Repo_aux + json_name;
        OutputStream outputStream = new FileOutputStream(Repo_aux); // path y nombre del nuevo fichero creado
        byte[] b = new byte[1024];
        int longitud;
        while ((longitud = in2.read(b)) != -1) {
            outputStream.write(b, 0, longitud);
        }
    }

    public void Commit() throws SVNException, IOException {
        GetPaths("_GE", "_GE");

        SVNRepository repository = initConnectionToSVN(Paths.get(5)+Paths.get(3));

        SVNClientManager ourClientManager = SVNClientManager.newInstance(null, repository.getAuthenticationManager());
        File UpdateFile = new File("UpdatePath.txt");
        if (!UpdateFile.exists()) {
            System.out.println("There is nothing to update");
            System.exit(1);
        }
        BufferedReader br = new BufferedReader(new FileReader("UpdatePath.txt"));
        SVNWCClient CClient = ourClientManager.getWCClient();
        //Leemos Paths para actualizar
        try {
            String line = br.readLine();
            ArrayList<String> paths = new ArrayList<>();
            while (line != "" && line != null) {
                paths.add(line);
                line = br.readLine();
            }
            File[] repos = new File[paths.size()];
            for (int i = 0; i < repos.length; ++i) {
                repos[i] = new File(paths.get(i));
            }

            CClient.doAdd(repos,true,false,false,SVNDepth.INFINITY,false,false,true);
            final SVNCommitClient commitClient = ourClientManager.getCommitClient();
            SVNCommitPacket packet = commitClient.doCollectCommitItems(repos,true,false,SVNDepth.INFINITY,null);
            commitClient.doCommit(packet, true, false, "Commit",null);

        } finally {
            br.close();
        }

        File file = new File("UpdatePath.txt");
        file.delete();
    }

}






