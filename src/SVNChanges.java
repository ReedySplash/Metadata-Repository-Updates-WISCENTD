import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.SVNException;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.net.URL;
import java.net.URLConnection;


public class SVNChanges {

    private ArrayList<String> Paths;
    private ArrayList<String> SVNCredentials;
    private String[][] Sets;


    public static void main(String[] argv) throws IOException, SVNException {

        //Set up connection protocols support:
        SVNChanges prueba = new SVNChanges();
        prueba.UpdateOrgGroupSets();
       //prueba.UpdateOrgUniteStructure();
       // prueba.UpdateOrgUnitLevel1();
        /*prueba.UpdateOrgUnitLevel2();
        prueba.UpdateOrgUnitLevel3();
        prueba.UpdateOrgUnitLevel4();
        prueba.UpdateOrgUnitLevel5();
        prueba.UpdateOrgUnitLevel6();
        prueba.UpdateOrgUnitLevel7();
        prueba.UpdateOrgUnitLevel8();

        prueba.UpdateAttributes();
        prueba.UpdateCategoryCombinations();
        prueba.UpdateCommonDataElements();
        prueba.UpdateDashboards();
        prueba.UpdateDataSets();
        prueba.UpdateIndictors();
        prueba.UpdateOrgbyCountry();
        prueba.UpdateOrgbyProgram();
        prueba.UpdateOrgforAssignment();
        prueba.UpdateOrgGeneral();
        prueba.UpdateProgramsWoRegistration();
        prueba.UpdateProgramsWRegistration();
        prueba.UpdateShapeFilesDetails();
        prueba.UpdateShapeOptimized();
        prueba.UpdateUserGroups();
        prueba.UpdateUserRoles();
        prueba.UpdateValidationRules();
        prueba.UpdateALLSVN();
        */
    }



    //Org Unit Groups

    public void UpdateOrgGroupSets() throws IOException, SVNException {
        GetPaths("_GS","_GS");
        File Repository = new File(Paths.get(1));
        URL url_aux = new URL(Paths.get(4)+Paths.get(2));
       // UpdateGeneralSVN(Paths.get(3),Repository,url_aux,"/"+Paths.get(0)+".json");
        GetAllSets(url_aux,Paths.get(0));
        int f =2;
    }



    public void UpdateOrgbyCountry() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-groups/oug-by-country");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-groups/oug-by-country",Repository,url_aux,null);
    }

    public void UpdateOrgbyProgram() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-groups/oug-by-program");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-groups/oug-by-program",Repository,url_aux,null);
    }

    public void UpdateOrgforAssignment() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-groups/oug-for-assignment");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-groups/oug-for-assignment",Repository,url_aux,null);
    }

    public void UpdateOrgGeneral() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-groups/oug-general");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-groups/oug-general",Repository,url_aux,null);
    }





    //Shape Files Update
    public void UpdateShapeFilesDetails() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/shape-files/optimized");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/shape-files/optimized",Repository,url_aux,null);
    }

    public void UpdateShapeOptimized() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/shape-files/detailed");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/shape-files/detailed",Repository,url_aux,null);
    }





    //Common Data Elements
    public void UpdateCommonDataElements() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/common-data-elements");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/common-data-elements",Repository,url_aux,null);
    }




    //Dashoard
    public void UpdateDashboards() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/dashboards");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/dashboards",Repository,url_aux,null);
    }



    //Indicators
    public void UpdateIndictors() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/indicators");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/indicators",Repository,url_aux,null);
    }





    //Data Collection Forms
    public void UpdateDataSets() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/data-collection-forms/datasets");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/data-collection-forms/datasets",Repository,url_aux,null);
    }

    public void UpdateProgramsWoRegistration() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/data-collection-forms/programs-wo-registration");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/data-collection-forms/programs-wo-registration",Repository,url_aux,null);
    }

    public void UpdateProgramsWRegistration() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/data-collection-forms/programs-w-registration");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/data-collection-forms/programs-w-registration",Repository,url_aux,null);
    }




    //General
    public void UpdateAttributes() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/general/attributes");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/general/attributes",Repository,url_aux,null);
    }

    public void UpdateCategoryCombinations() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/general/category-combinations");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/general/category-combinations",Repository,url_aux,null);
    }

    public void UpdateValidationRules() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/general/validation-rules");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/general/validation-rules",Repository,url_aux,null);
    }




    //User Management
    public void UpdateUserGroups() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/user-management/user-groups");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/user-management/user-groups",Repository,url_aux,null);
    }

    public void UpdateUserRoles() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/user-management/user-roles");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/user-management/user-roles",Repository,url_aux,null);
    }


    //UPDATE ORG_UNIT_LVLs STRUCTURE
    public void UpdateOrgUniteStructure() throws IOException, SVNException, FileNotFoundException {
        GetPaths("_S","_S");
        File Repository = new File(Paths.get(1));
        URL url_aux = new URL(Paths.get(4)+Paths.get(2));
        UpdateGeneralSVN(Paths.get(3),Repository,url_aux,"/org-unit-levels.json");

    }


    //UPDATE FOR ORG_UNIT_LVLs
    public void UpdateOrgUnitLevel1() throws IOException, SVNException {
        GetPaths("_S","_S");
        URL PathOrgStructure = new URL(Paths.get(4)+Paths.get(2));
        GetPaths("_1","_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 1));
        File Repository = new File(Paths.get(1)+Paths.get(5));
        URL url_aux = new URL(Paths.get(4)+Paths.get(2));
        UpdateGeneralSVN(Paths.get(3),Repository,url_aux,"/1-who-global.json");
    }

    public void UpdateOrgUnitLevel2() throws IOException, SVNException {
        GetPaths("_S","_S");
        URL PathOrgStructure = new URL(Paths.get(4)+Paths.get(2));
        GetPaths("_2","_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 2));
        File Repository = new File(Paths.get(1)+Paths.get(5));
        URL url_aux = new URL(Paths.get(4)+Paths.get(2));
        UpdateGeneralSVN(Paths.get(3),Repository,url_aux,"/2-who-regions.json");
    }


    public void UpdateOrgUnitLevel3() throws IOException, SVNException {
        GetPaths("_S","_S");
        URL PathOrgStructure = new URL(Paths.get(4)+Paths.get(2));
        GetPaths("_3","_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 3));
        File Repository = new File(Paths.get(1)+Paths.get(5));
        URL url_aux = new URL((Paths.get(4)+Paths.get(2)));
        UpdateGeneralSVN(Paths.get(3),Repository,url_aux,"/3-who-member-states.json");
    }

    public void UpdateOrgUnitLevel4() throws IOException, SVNException {
        URL PathOrgStructure = new URL(Paths.get(4)+Paths.get(2));
        GetPaths("_4","_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 4));
        File Repository = new File(Paths.get(1)+Paths.get(5));
        URL url_aux = new URL(Paths.get(4)+Paths.get(2));
        UpdateGeneralSVN(Paths.get(3),Repository,url_aux,"/4-level1.json");
    }

    public void UpdateOrgUnitLevel5() throws IOException, SVNException {
        GetPaths("_S","_S");
        URL PathOrgStructure = new URL(Paths.get(4)+Paths.get(2));
        GetPaths("_5","_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 5));
        File Repository = new File(Paths.get(1)+Paths.get(5));
        URL url_aux = new URL(Paths.get(4)+Paths.get(2));
        UpdateGeneralSVN(Paths.get(3),Repository,url_aux,"/5-level2.json");
    }

    public void UpdateOrgUnitLevel6() throws IOException, SVNException {
        GetPaths("_S","_S");
        URL PathOrgStructure = new URL(Paths.get(4)+Paths.get(2));
        GetPaths("_6","_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 6));
        File Repository = new File(Paths.get(1)+Paths.get(5));
        URL url_aux = new URL(Paths.get(4)+Paths.get(2));
        UpdateGeneralSVN(Paths.get(3),Repository,url_aux,"/6-level3.json");
    }

    public void UpdateOrgUnitLevel7() throws IOException, SVNException {
        GetPaths("_S","_S");
        URL PathOrgStructure = new URL(Paths.get(4)+Paths.get(2));
        GetPaths("_7","_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 7));
        File Repository = new File(Paths.get(1)+Paths.get(5));
        URL url_aux = new URL(Paths.get(4)+Paths.get(2));
        UpdateGeneralSVN(Paths.get(3),Repository,url_aux,"/7-level4.json");
    }

    public void UpdateOrgUnitLevel8() throws IOException, SVNException {
        GetPaths("_S","_S");
        URL PathOrgStructure = new URL(Paths.get(4)+Paths.get(2));
        GetPaths("_8","_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 8));
        File Repository = new File(Paths.get(1)+Paths.get(5));
        URL url_aux = new URL(Paths.get(4)+Paths.get(2));
        UpdateGeneralSVN(Paths.get(3),Repository,url_aux,"/8-level8.json");
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


    private void GetPaths(String Type, String Type2) throws IOException {
        //Initialize Properties file
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream("./config.properties");
            prop.load(is);
        } catch(IOException e) {
            System.out.println(e.toString());
        }
        this.SVNCredentials = new ArrayList<>(2);
        this.SVNCredentials.add(prop.getProperty("UserName"));
        this.SVNCredentials.add(prop.getProperty("password"));
        this.Paths = new ArrayList<>(5);
        this.Paths.add(0,prop.getProperty("NameInJs"+Type2));
        this.Paths.add(1,prop.getProperty("PathFile"+Type2));
        this.Paths.add(2,prop.getProperty("UrlDHIS2"+Type));
        this.Paths.add(3,prop.getProperty("UrlSvnRe"+Type2));
        this.Paths.add(4,prop.getProperty("BaseDHIS2url"));
    }

    private void GetAllSets(URL DHIS2url, String name) throws IOException {
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
                for(int j = 0; j < SetsID.size(); ++j) {
                    JsonObject id = SetsID.getJsonObject(j);
                    Sets[i][j+1] = id.getString("id");
                }
            }
        }
    }



    public void Commit() throws SVNException {
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
        File[] repos = new File[2];
        repos[0] = new File("svn://who-dev.essi.upc.edu/metadata-repository");
        SVNCommitClient commitClient = ourClientManager.getCommitClient();
        commitClient.doImport(repos[0],SVNURL.parseURIEncoded("svn://who-dev.essi.upc.edu/metadata-repository"),"Update",false,false);
    }



    private void UpdateGeneralSVN(String url, File Repository, URL DHIS2url, String json_name) throws SVNException, IOException {

        //CONEXION A SVN
        SVNRepositoryFactoryImpl.setup();
        SVNRepository repository = null;
        long startRevision = 0;
        long endRevision = -1; //HEAD (the latest) revision

        try {
            SVNRepositoryFactoryImpl.setup();
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(this.SVNCredentials.get(0), this.SVNCredentials.get(1));
            repository.setAuthenticationManager(authManager);
        } catch (SVNException e) {
            e.printStackTrace();
            System.exit(1);
        }

        SVNClientManager ourClientManager = SVNClientManager.newInstance(null, repository.getAuthenticationManager());
        SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
        updateClient.setIgnoreExternals(false);
        updateClient.doCheckout(SVNURL.parseURIEncoded(url),Repository,SVNRevision.HEAD,SVNRevision.HEAD,true);

        Date lastUpdate = new Date();
        Collection logEntries = null;
        logEntries = repository.log( new String[] { "" } , null , startRevision , endRevision , true , true );
        for (Iterator entries = logEntries.iterator( ); entries.hasNext( );) {
            SVNLogEntry logEntry = (SVNLogEntry) entries.next();
            lastUpdate = logEntry.getDate();
        }



        //PARTE DE JSON
        URLConnection uc = DHIS2url.openConnection();
        String userpass = "vmurciano" + ":" + "Vict0r2017#";
        String basicAuth = "Basic " + Base64.encode(userpass.getBytes());
        uc.setRequestProperty ("Authorization", basicAuth);
        InputStream in = uc.getInputStream();
        boolean actualizar = false;
        try (InputStream is = in;
             JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            JsonArray hola = obj.getJsonArray(Paths.get(0));
            for (int i = 0; i < hola.size(); ++i ) {
                JsonObject hola2 = hola.getJsonObject(i);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss");
                Date date2 = formatter.parse(hola2.getString("lastUpdated").replaceAll("Z$", "+0000"));
                if (date2.before(lastUpdate))  {
                    actualizar = true;
                    break;
                }
            }
            if (actualizar) {
                URLConnection uc2= DHIS2url.openConnection();
                uc2.setRequestProperty ("Authorization", basicAuth);
                InputStream in2 = uc2.getInputStream();
                String Repo_aux = Repository.toString();
                Repo_aux = Repo_aux + json_name;
                OutputStream outputStream = new FileOutputStream(Repo_aux); // path y nombre del nuevo fichero creado
                byte[] b = new byte[2048];
                int longitud;
                while ((longitud = in2.read(b)) != -1) {
                    outputStream.write(b, 0, longitud);
                }
                /*Commit del fichero Json que acabamos de crear
                File[] repos = new File[2];
                String file_aux = Repository.toString();
                repos[0] = new File(file_aux);
                file_aux = file_aux + json_name;
                File repos_aux = new File(file_aux);
                repos[1] = repos_aux;
                //SVNCommitClient commitClient = ourClientManager.getCommitClient();
                //commitClient.doImport(repos[0],SVNURL.parseURIEncoded(url),"Update",false,false);*/
            }
            System.out.printf("Fecha obtenida del SVN: ");
            System.out.println(lastUpdate);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}

