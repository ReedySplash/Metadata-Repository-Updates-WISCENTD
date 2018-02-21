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
    private ArrayList<String> ID = null;
    private boolean creado;

    //This main is for testing the different functions
    public static void main(String[] argv) throws IOException, SVNException, ExceptionInSVN {

        //Set up connection protocols support:
        SVNChanges prueba = new SVNChanges();
        File UpdateFile = new File("UpdatePath.txt");
        if (UpdateFile.exists()) {
            UpdateFile.delete();
        }
        prueba.CheckoutOrUpdate();
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
        prueba.UpdateDataElements();
        prueba.UpdateDataElementGroups();
        prueba.UpdateIndicators();
        prueba.UpdateIndicatorGroups();
        prueba.UpdateIndicatorsGroupsSets();
        prueba.UpdateValidationRule();
        prueba.UpdateValidationRuleGroups();
        prueba.UpdateProgramIndicatorGroups();
        prueba.UpdateProgramIndicators();
        prueba.UpdateIndicatorTypes();
        prueba.UpdateDataSet();
        prueba.UpdateCategory();
        prueba.UpdateCategoryCombination();
        prueba.UpdateCategoryOption();
        prueba.UpdateCategoryOptionCombination();
        prueba.UpdateConstant();
        prueba.UpdateAttribute();
        prueba.UpdateOptionSet();
        prueba.UpdateLegend();
        prueba.UpdateExternalMapLayer();
        prueba.Commit();
    }




    //Org Unit Groups
    public void UpdateOrgGroupSetsStructure() throws IOException, SVNException, ExceptionInSVN {
        //For getting Paths, Urls and Names form config file
        GetPaths("_GS", "_GS");
        //We create a File where we have the local repository
        File Repository = new File(Paths.get(1));
        //In this URL we have the DHIS2's url
        URL DHIS2url = new URL(Paths.get(4) + Paths.get(2));
        //For update .json files which needs a loop in the json file
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3), Repository, DHIS2url, "/" + Paths.get(0) + ".json");
    }

    public void UpdateAllOrgGroups() throws IOException, SVNException, ExceptionInSVN {
        GetPaths("_GS", "_GS");
        //Get in a Matrix all the Sets and his Groups
        GetAllSets(new URL(Paths.get(4) + Paths.get(2)), Paths.get(0));
        //For every Set we create a folder and insert the differents groups in Json files
        for (int i = 0; i < Sets.length; ++i) {
            //If directory don't exists, we create it and commit it later
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
        GetAllID(new URL(Paths.get(4) + Paths.get(2)));
        for (int i = 0; i < ID.size(); ++i) {
            File Repository = new File(Paths.get(1) + "/Others");
            URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + ID.get(i));
            UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3) + "/Others",Repository,url_aux,"/" + ID.get(i) + "-",0,true,false);
        }
    }

    //Data Elements
    public void UpdateDataElements() throws IOException, SVNException, ExceptionInSVN {
        //For update .json when we need ID's
        GeneralLoop("_D");
    }

    public void UpdateDataElementGroups() throws IOException, SVNException, ExceptionInSVN {
        ID = null;
        /*
        GetPaths("_DGS", "_DGS");
        GetAllSets(new URL(Paths.get(4) + Paths.get(2)), Paths.get(0));
        for (int i = 0; i < Sets.length; ++i) {
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
        GetAllID(new URL(Paths.get(4) + Paths.get(2)));
        for (int i = 0; i < ID.size(); ++i) {
            File Repository = new File(Paths.get(1) + "/Others");
            URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + ID.get(i));
            UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3) + "/Others",Repository,url_aux,"/" + ID.get(i) + "-",0,true,false);
        }
    }

    public void UpdateDataElementGroupSets() throws IOException, SVNException, ExceptionInSVN {
        GetPaths("_DGS", "_DGS");
        File Repository = new File(Paths.get(1));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3), Repository, url_aux, "/" + Paths.get(0) + ".json");
    }

    //Indicators
    public void UpdateIndicators() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_I");
    }

    public void UpdateIndicatorGroups() throws IOException, SVNException, ExceptionInSVN {
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
        GetAllID(new URL(Paths.get(4) + Paths.get(2)));
        for (int i = 0; i < ID.size(); ++i) {
            File Repository = new File(Paths.get(1) + "/Others");
            URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + ID.get(i));
            UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3) + "/Others",Repository,url_aux,"/" + ID.get(i) + "-",0,true,false);
        }
    }

    public void UpdateIndicatorsGroupsSets() throws IOException, SVNException, ExceptionInSVN {
        GetPaths("_IGS", "_IGS");
        CreateDirectory(false,-1);
        File Repository = new File(Paths.get(1));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3), Repository, url_aux, "/" + Paths.get(0) + ".json");
    }


    //Validations
    public void UpdateValidationRule() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_V");
    }

    public void UpdateValidationRuleGroups() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_VG");
    }


    //FALTA VALIDATION NOTIFICATION//


    //ProgramIndicatorGroups

    public void UpdateProgramIndicatorGroups() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_PIG");
    }

    public void UpdateProgramIndicators() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_PI");
    }


    //IndicatorTypes
    public void UpdateIndicatorTypes() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_IT");
    }


    //Data Set
    public void UpdateDataSet() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_DS");
    }

    //CATEGORY

    public void UpdateCategoryOption() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_CO");
    }

    public void UpdateCategoryOptionGroup() {

    }

    public void UpdateCategoryOptionGroupSet() {

    }

    public void UpdateCategoryCombination() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_CC");
    }

    public void UpdateCategoryOptionCombination() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_COC");
    }

    public void UpdateCategory() throws IOException, ExceptionInSVN {
        GeneralLoop("_C");
    }



    //UPDATE ORG_UNIT_LVLs STRUCTURE
    public void UpdateOrgUniteStructure() throws IOException, SVNException, ExceptionInSVN {
        GetPaths("_S", "_S");
        File Repository = new File(Paths.get(1));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3), Repository, url_aux, "/org-unit-levels.json");

    }


    //UPDATE FOR ORG_UNIT_LVLs
    public void UpdateOrgUnitLevel1() throws IOException, SVNException, ExceptionInSVN {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_1", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 1));
        File Repository = new File(Paths.get(1) +"/"+ Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/1-who-global.json");
    }

    public void UpdateOrgUnitLevel2() throws IOException, SVNException, ExceptionInSVN {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_2", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 2));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/2-who-regions.json");
    }

    public void UpdateOrgUnitLevel3() throws IOException, SVNException, ExceptionInSVN {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_3", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 3));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL((Paths.get(4) + Paths.get(2)));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/3-who-member-states.json");
    }

    public void UpdateOrgUnitLevel4() throws IOException, SVNException, ExceptionInSVN {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_4", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 4));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/4-level1.json");
    }

    public void UpdateOrgUnitLevel5() throws IOException, SVNException, ExceptionInSVN {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_5", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 5));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/5-level2.json");
    }

    public void UpdateOrgUnitLevel6() throws IOException, SVNException, ExceptionInSVN {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_6", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 6));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/6-level3.json");
    }

    public void UpdateOrgUnitLevel7() throws IOException, SVNException, ExceptionInSVN {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_7", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 7));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/7-level4.json");
    }

    public void UpdateOrgUnitLevel8() throws IOException, SVNException, ExceptionInSVN {
        GetPaths("_S", "_S");
        URL PathOrgStructure = new URL(Paths.get(4) + Paths.get(2));
        GetPaths("_8", "_FA");
        Paths.add(GetOrgUnitLevelName(PathOrgStructure, 8));
        File Repository = new File(Paths.get(1) + "/" + Paths.get(6));
        URL url_aux = new URL(Paths.get(4) + Paths.get(2));
        CreateDirectory(false, 0);
        UpdateGeneralSVN(Paths.get(5) + Paths.get(3) + "/" + Paths.get(6), Repository, url_aux, "/8-level8.json");
    }


    //OTHERS
    public void UpdateConstant() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_CON");
    }

    public void UpdateAttribute() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_A");
    }

    public void UpdateOptionSet() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_OS");
    }

    public void UpdateLegend() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_L");
    }

    public void UpdatePredictor() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_P");
    }

    public void UpdatePushAnalysis() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_PA");
    }

    public void UpdateExternalMapLayer() throws IOException, SVNException, ExceptionInSVN {
        GeneralLoop("_EML");
    }









    //Make the directory if it's necessary
    private void CreateDirectory(boolean Different, int i) throws ExceptionInSVN {
        boolean success;
        //Different == true when we have Matrix with all Sets and Groups
        if (Different) success = (new File(Paths.get(1) + "/" + Sets[i][0])).mkdirs();
            //i == -1 -> If we have complete path in Paths.get(1)
        else if (i == -1 && !Different) success = new File(Paths.get(1)).mkdirs();
        else success = new File(Paths.get(1) +"/"+ Paths.get(6)).mkdirs();
        if (success) {
            //if directory is created, we need to commit it later, creado == true
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
                throw new ExceptionInSVN("ERROR");
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new ExceptionInSVN("ERROR");
                    }
                }
            }
        }
    }


    //Get the name of the level that we need
    private String GetOrgUnitLevelName(URL DHIS2url, int Level) throws IOException, ExceptionInSVN {
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

    //To Get all IDs or groups to update them into repostory
    private void GetAllID(URL DHIS2url) throws IOException, ExceptionInSVN {
        if (ID == null) ID = new ArrayList<>();
        URLConnection uc = initConnectionToDHIS2(DHIS2url);
        InputStream in = uc.getInputStream();
        try (InputStream is = in; JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            if(Objects.equals(Paths.get(0).substring(Paths.get(0).length() - 1), "y")) Paths.set(0,Paths.get(0).substring(0,Paths.get(0).length()-1)+"ie");
            JsonArray OrgGroups = obj.getJsonArray(Paths.get(0)+"s");
            for (int i = 0; i < OrgGroups.size(); ++i) {
                JsonObject GroupOrID = OrgGroups.getJsonObject(i);
                String id = GroupOrID.getString("id");
                if (ID != null && ID.contains(id)) {
                    ID.remove(id);
                }
                else {
                    ID.add(id);
                }
            }
        }
        catch (java.lang.NullPointerException n) {
            throw new ExceptionInSVN("ERROR");
        }
    }

    //To get all Paths, urls and names that we need to update Repository from config file
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

    //Put all sets and his groups in a matrix and put groups in a ArrayList
    private void GetAllSets(URL DHIS2url, String name) throws IOException, ExceptionInSVN {
        ID = new ArrayList<>();
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
                    ID.add(id.getString("id"));
                }
            }
        }
    }

    //For update a repository when we need to get id's.
    private void GeneralLoop(String Type) throws IOException, ExceptionInSVN {
        ID = null;
        GetPaths(Type, Type);
        CreateDirectory(false, -1);
        GetAllID(new URL(Paths.get(4) + Paths.get(2)));
        for (int i = 0; i < ID.size(); ++i) {
            File Repository = new File(Paths.get(1));
            URL url_aux = new URL(Paths.get(4) + Paths.get(2) + "/" + ID.get(i));
            UpdateGeneralSVN_WithoutForinJson(Paths.get(5) + Paths.get(3),Repository,url_aux,"/" + ID.get(i) + "-",0,true,false);
        }
    }



    //Intialize connection to DHIS2
    private URLConnection initConnectionToDHIS2(URL DHIS2url) throws ExceptionInSVN {
        //We open the connection with DHIS2 and set the credentials to get in.
        URLConnection uc = null;
        try {
            uc = DHIS2url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInSVN("Error in connection");
        }
        String userpass = "vmurciano" + ":" + "Vict0r2017#";
        String basicAuth = "Basic " + Base64.encode(userpass.getBytes());
        uc.setRequestProperty("Authorization", basicAuth);
        return uc;
    }

    //Intialize connection to SVN
    private SVNRepository initConnectionToSVN(String SVN) {
        //We open the connection to SVN and set the credentials to update it if it's necessary
        SVNRepositoryFactoryImpl.setup();
        SVNRepository repository = null;
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

    //Before try to update the repository we need to checkout if not exists or update if exists local repository
    private void CheckoutOrUpdate() throws IOException, SVNException {
        GetPaths("_GE", "_GE");
        //We initialize connection to SVN with SVN url
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

    //Update repository, this function it's useful if we need to do a search with a loop inside Json file in DHIS2url
    private void UpdateGeneralSVN(String SVN, File Repository, URL DHIS2url, String json_name) throws ExceptionInSVN, IOException {

        //CONEXION A SVN
        SVNRepository repository = initConnectionToSVN(SVN);
        Date lastUpdate = GetLastUpdateDate_SVN(SVN,Repository,repository);

        //PARTE DE JSON
        URLConnection uc = initConnectionToDHIS2(DHIS2url);
        InputStream in = uc.getInputStream();
        boolean actualizar = false;
        try (InputStream is = in;
             JsonReader rdr = Json.createReader(is)) {
            TimeZone.setDefault(new SimpleTimeZone(60 * 60 * 1000, "CET"));
            JsonObject obj = rdr.readObject();
            JsonArray jsonarray = obj.getJsonArray(Paths.get(0));
            if (jsonarray == null) return;
            for (int i = 0; i < jsonarray.size(); ++i) {
                JsonObject jsonObject = jsonarray.getJsonObject(i);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss");
                Date date2 = formatter.parse(jsonObject.getString("lastUpdated").replaceAll("Z$", "+0000"));
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


    //Update repository, this function it's useful if we need to do a search without a loop inside Json file in DHIS2url
    private void UpdateGeneralSVN_WithoutForinJson(String SVN, File Repository, URL DHIS2url, String json_name, int i, boolean others, boolean DifferentDirectory) throws ExceptionInSVN, ExceptionInSVN, IOException {

        //CONEXION A SVN
        SVNRepository repository = initConnectionToSVN(SVN);
        Date lastUpdate = GetLastUpdateDate_SVN(SVN,Repository,repository);

        //PARTE DE JSON
        URLConnection uc = initConnectionToDHIS2(DHIS2url);
        InputStream in = null;
        try {
            in = uc.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();

        }

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
            json_name = json_name.replace("?","(Not secure)");

            if (lastUpdate == null || lastUpdate.before(date2)) {
                actualizar = true;
            }
        } catch (ParseException | IOException e1) {
            e1.printStackTrace();
            throw new ExceptionInSVN("ERROR");
        }

        if (!DifferentDirectory && actualizar) {
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

    //Return the date of the last update in a directoy in the repository
    private Date GetLastUpdateDate_SVN(String SVN, File Repository, SVNRepository repository) throws ExceptionInSVN {
        long startRevision = 0;
        long endRevision = -1; //HEAD (the latest) revision
        Date lastUpdate = new Date();
        Collection logEntries = null;

        if(Repository.exists() && !creado) {
            try {
                logEntries = repository.log(new String[]{""}, null, startRevision, endRevision, true, true);
            } catch (SVNException e) {
                e.printStackTrace();
                throw new ExceptionInSVN("Error in SVN");
            }
            for (Iterator entries = logEntries.iterator(); entries.hasNext(); ) {
                SVNLogEntry logEntry = (SVNLogEntry) entries.next();
                lastUpdate = logEntry.getDate();
            }
            return lastUpdate;
        }
        return null;
    }

    //Do a update of a file
    private void Actualizar(File Repository, URL DHIS2url, String json_name) throws IOException, ExceptionInSVN {
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

    //Commit all file updates in a single revision.
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






