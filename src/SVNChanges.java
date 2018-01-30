import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
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
    private ArrayList<String> Groups;


    public static void main(String[] argv) throws IOException, SVNException {

        //Set up connection protocols support:
        SVNChanges prueba = new SVNChanges();
        prueba.UpdateOrgGroupSetsStructure();
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
        /*
        prueba.UpdateAttributes();
        prueba.UpdateCategoryCombinations();
        prueba.UpdateCommonDataElements();
        prueba.UpdateDashboards();
        prueba.UpdateDataSets();
        prueba.UpdateIndictors();
        prueba.UpdateProgramsWoRegistration();
        prueba.UpdateProgramsWRegistration();
        prueba.UpdateShapeFilesDetails();
        prueba.UpdateShapeOptimized();
        prueba.UpdateUserGroups();
        prueba.UpdateUserRoles();
        prueba.UpdateValidationRules();*/
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
        /*for (int i = 0; i < Sets.length; ++i) {
            CreateDirectory(true, i);
            int j = 1;
            while (Sets[i][j] != null){
                File Repository = new File(Paths.get(1) + "/" + Sets[i][0]);
                GetPaths("_G", "_G");
                URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + Sets[i][j]);
                UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3) + "/" + Sets[i][0], Repository, url_aux, "/" + Sets[i][j] + ".json",i,false);
                ++j;
            }
        }*/
        Paths.add("/Others");
        CreateDirectory(false, 0);
        GetPaths("_G", "_G");
        GetAllGroups(new URL(Paths.get(4) + Paths.get(2)));
        for (int i = 0; i < Groups.size(); ++i) {
            File Repository = new File(Paths.get(1) + "/Others");
            URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + Groups.get(i));
            UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3) + "/Others",Repository,url_aux,"/" + Groups.get(i) + ".json",0,true);
        }


    }

    //Shape Files Update
    public void UpdateShapeFilesDetails() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/shape-files/optimized");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/shape-files/optimized", Repository, url_aux, null);
    }

    public void UpdateShapeOptimized() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/shape-files/detailed");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/shape-files/detailed", Repository, url_aux, null);
    }


    //Common Data Elements
    public void UpdateCommonDataElements() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/common-data-elements");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/common-data-elements", Repository, url_aux, null);
    }


    //Dashoard
    public void UpdateDashboards() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/dashboards");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/dashboards", Repository, url_aux, null);
    }


    //Indicators
    public void UpdateIndictors() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/indicators");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/indicators", Repository, url_aux, null);
    }


    //Data Collection Forms
    public void UpdateDataSets() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/data-collection-forms/datasets");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/data-collection-forms/datasets", Repository, url_aux, null);
    }

    public void UpdateProgramsWoRegistration() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/data-collection-forms/programs-wo-registration");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/data-collection-forms/programs-wo-registration", Repository, url_aux, null);
    }

    public void UpdateProgramsWRegistration() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/data-collection-forms/programs-w-registration");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/data-collection-forms/programs-w-registration", Repository, url_aux, null);
    }


    //General
    public void UpdateAttributes() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/general/attributes");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/general/attributes", Repository, url_aux, null);
    }

    public void UpdateCategoryCombinations() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/general/category-combinations");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/general/category-combinations", Repository, url_aux, null);
    }

    public void UpdateValidationRules() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/general/validation-rules");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/general/validation-rules", Repository, url_aux, null);
    }


    //User Management
    public void UpdateUserGroups() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/user-management/user-groups");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/user-management/user-groups", Repository, url_aux, null);
    }

    public void UpdateUserRoles() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/user-management/user-roles");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/user-management/user-roles", Repository, url_aux, null);
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
        File Repository = new File(Paths.get(1) + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/1-who-global.json");
    }

    public void UpdateOrgUnitLevel2() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_2", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 2));
        File Repository = new File(Paths.get(1) + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/2-who-regions.json");
    }

    public void UpdateOrgUnitLevel3() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_3", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 3));
        File Repository = new File(Paths.get(1) + Paths.get(6));
        URL url_aux = new URL((Paths.get(4) + Paths.get(2)));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/3-who-member-states.json");
    }

    public void UpdateOrgUnitLevel4() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_4", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 4));
        File Repository = new File(Paths.get(1) + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/4-level1.json");
    }

    public void UpdateOrgUnitLevel5() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_5", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 5));
        File Repository = new File(Paths.get(1) + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/5-level2.json");
    }

    public void UpdateOrgUnitLevel6() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_6", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 6));
        File Repository = new File(Paths.get(1) + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/6-level3.json");
    }

    public void UpdateOrgUnitLevel7() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_7", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 7));
        File Repository = new File(Paths.get(1) + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/7-level4.json");
    }

    public void UpdateOrgUnitLevel8() throws IOException, SVNException {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_8", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 8));
        File Repository = new File(Paths.get(1) + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/8-level8.json");
    }


    private void CreateDirectory(boolean Different, int i) throws IOException {
        boolean success;
        if (Different) success = (new File(Paths.get(1) + "/" + Sets[i][0])).mkdirs();
        else success = new File(Paths.get(1) + Paths.get(6)).mkdirs();
        if (success) {
            BufferedWriter out = null;
            try {
                FileWriter fstream = new FileWriter("UpdatePath.txt", true); //true tells to append data.
                out = new BufferedWriter(fstream);
                if (Different) out.write(Paths.get(3) + "/" + Sets[i][0]);
                else out.write(Paths.get(3) + "/" + Paths.get(6));
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
        URLConnection uc = DHIS2url.openConnection();
        String userpass = "vmurciano" + ":" + "Vict0r2017#";
        String basicAuth = "Basic " + Base64.encode(userpass.getBytes());
        uc.setRequestProperty("Authorization", basicAuth);
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
        URLConnection uc = DHIS2url.openConnection();
        String userpass = "vmurciano" + ":" + "Vict0r2017#";
        String basicAuth = "Basic " + Base64.encode(userpass.getBytes());
        uc.setRequestProperty("Authorization", basicAuth);
        InputStream in = uc.getInputStream();
        try (InputStream is = in; JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            JsonArray OrgGroups = obj.getJsonArray("organisationUnitGroups");
            for (int i = 0; i < OrgGroups.size(); ++i) {
                JsonObject Group = OrgGroups.getJsonObject(i);
                String id = Group.getString("id");
                if (Groups.contains(id)) {
                    Groups.remove(id);
                }
                else Groups.add(id);
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
        URLConnection uc = DHIS2url.openConnection();
        String userpass = "vmurciano" + ":" + "Vict0r2017#";
        String basicAuth = "Basic " + Base64.encode(userpass.getBytes());
        uc.setRequestProperty("Authorization", basicAuth);
        InputStream in = uc.getInputStream();
        try (InputStream is = in; JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            JsonArray OrgGroups = obj.getJsonArray(name);
            Sets = new String[OrgGroups.size()][50];
            for (int i = 0; i < OrgGroups.size(); ++i) {
                JsonObject Set = OrgGroups.getJsonObject(i);
                Sets[i][0] = Set.getString("name");
                JsonArray SetsID = Set.getJsonArray("organisationUnitGroups");
                for (int j = 0; j < SetsID.size(); ++j) {
                    JsonObject id = SetsID.getJsonObject(j);
                    Sets[i][j + 1] = id.getString("id")+"-"+id.getString("displayName");
                    Groups.add(id.getString("id")+"-"+id.getString("displayName"));
                }
            }
        }
    }

    public void Commit() throws SVNException, IOException {
        GetPaths(null, null);
        SVNRepositoryFactoryImpl.setup();
        SVNRepository repository = null;
        long startRevision = 0;
        long endRevision = -1; //HEAD (the latest) revision


        try {
            SVNRepositoryFactoryImpl.setup();
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded("svn://who-dev.essi.upc.edu/metadata-repository"));
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(this.SVNCredentials.get(0), this.SVNCredentials.get(1));
            repository.setAuthenticationManager(authManager);
        } catch (SVNException e) {
            e.printStackTrace();
            System.exit(1);
        }
        SVNClientManager ourClientManager = SVNClientManager.newInstance(null, repository.getAuthenticationManager());
        BufferedReader br = new BufferedReader(new FileReader("UpdatePath.txt"));

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
                repos[i] = new File("C:/Users/Victor/Desktop" + paths.get(i));
                //commitClient.doImport(repos[i],SVNURL.parseURIEncoded("svn://who-dev.essi.upc.edu"+paths.get(i)),null,null,false,false,null);
            }
            SVNCommitClient commitClient = ourClientManager.getCommitClient();
            SVNCommitPacket packet = commitClient.doCollectCommitItems(repos, true, true, true);
            commitClient.doCommit(packet, true, "Update");
        } finally {
            br.close();
        }

        File file = new File("UpdatePath.txt");
        file.delete();
    }






    private void UpdateGeneralSVN(String SVN, File Repository, URL DHIS2url, String json_name) throws SVNException, IOException {

        //CONEXION A SVN
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

        SVNClientManager ourClientManager = SVNClientManager.newInstance(null, repository.getAuthenticationManager());
        SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
        updateClient.setIgnoreExternals(false);
        updateClient.doCheckout(SVNURL.parseURIEncoded(SVN), Repository, SVNRevision.HEAD, SVNRevision.HEAD, true);

        Date lastUpdate = new Date();
        Collection logEntries = null;
        logEntries = repository.log(new String[]{""}, null, startRevision, endRevision, true, true);
        for (Iterator entries = logEntries.iterator(); entries.hasNext(); ) {
            SVNLogEntry logEntry = (SVNLogEntry) entries.next();
            lastUpdate = logEntry.getDate();
        }


        //PARTE DE JSON
        URLConnection uc = DHIS2url.openConnection();
        String userpass = "vmurciano" + ":" + "Vict0r2017#";
        String basicAuth = "Basic " + Base64.encode(userpass.getBytes());
        uc.setRequestProperty("Authorization", basicAuth);
        InputStream in = uc.getInputStream();
        boolean actualizar = false;
        try (InputStream is = in;
             JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            JsonArray hola = obj.getJsonArray(Paths.get(0));
            for (int i = 0; i < hola.size(); ++i) {
                JsonObject hola2 = hola.getJsonObject(i);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss");
                Date date2 = formatter.parse(hola2.getString("lastUpdated").replaceAll("Z$", "+0000"));
                if (date2.before(lastUpdate)) {
                    actualizar = true;
                    break;
                }
            }
            if (actualizar) {
                if (actualizar) {
                    Actualizar(Repository,DHIS2url,json_name,basicAuth);
                }
            }
            System.out.printf("Fecha obtenida del SVN: ");
            System.out.println(lastUpdate);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void UpdateGeneralSVN_WithoutForinJson(String SVN, File Repository, URL DHIS2url, String json_name, int i, boolean others) throws SVNException, IOException {

        //CONEXION A SVN
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

        SVNClientManager ourClientManager = SVNClientManager.newInstance(null, repository.getAuthenticationManager());
        SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
        updateClient.setIgnoreExternals(false);
        updateClient.doCheckout(SVNURL.parseURIEncoded(SVN), Repository, SVNRevision.HEAD, SVNRevision.HEAD, true);

        Date lastUpdate = new Date();
        Collection logEntries = null;
        logEntries = repository.log(new String[]{""}, null, startRevision, endRevision, true, true);
        for (Iterator entries = logEntries.iterator(); entries.hasNext(); ) {
            SVNLogEntry logEntry = (SVNLogEntry) entries.next();
            lastUpdate = logEntry.getDate();
        }

        //PARTE DE JSON
        URLConnection uc = DHIS2url.openConnection();
        String userpass = "vmurciano" + ":" + "Vict0r2017#";
        String basicAuth = "Basic " + Base64.encode(userpass.getBytes());
        uc.setRequestProperty("Authorization", basicAuth);
        InputStream in = uc.getInputStream();
        boolean actualizar = false;
        try (InputStream is = in; JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss");
            Date date2 = formatter.parse(obj.getString("lastUpdated").replaceAll("Z$", "+0000"));
            if (date2.before(lastUpdate)) {
                actualizar = true;
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        if (actualizar && !others) {
            Paths.set(3,Paths.get(3)+"/"+Sets[i][0]);
            Actualizar(Repository,DHIS2url,json_name,basicAuth);
            Paths.set(3,Paths.get(3).substring(0,Paths.get(3).length()-(2+Sets[i][0].length())));
        }
        else {
            Paths.set(3,Paths.get(3)+"/Others");
            Actualizar(Repository,DHIS2url,json_name,basicAuth);
            Paths.set(3,Paths.get(3).substring(0,Paths.get(3).length()-(7)));
        }

        System.out.printf("Fecha obtenida del SVN: ");
        System.out.println(lastUpdate);
    }

    private void Actualizar(File Repository, URL DHIS2url, String json_name, String basicAuth) throws IOException {
        URLConnection uc2 = DHIS2url.openConnection();
        uc2.setRequestProperty("Authorization", basicAuth);
        InputStream in2 = uc2.getInputStream();
        String Repo_aux = Repository.toString();
        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter("UpdatePath.txt", true); //true tells to append data.
            out = new BufferedWriter(fstream);
            out.write(Paths.get(3) + json_name);
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
        byte[] b = new byte[2048];
        int longitud;
        while ((longitud = in2.read(b)) != -1) {
            outputStream.write(b, 0, longitud);
        }
    }

}






