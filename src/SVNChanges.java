import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.*;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.SVNException;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class SVNChanges {


    public static void main(String[] argv) throws IOException, SVNException {

        //Set up connection protocols support:
        SVNChanges prueba = new SVNChanges();
        prueba.UpdateOrgUnitLevel1();
        prueba.UpdateOrgUnitLevel2();
        prueba.UpdateOrgUnitLevel3();
        prueba.UpdateOrgUnitLevel4();
        prueba.UpdateOrgUnitLevel5();
        prueba.UpdateOrgUnitLevel6();
        prueba.UpdateOrgUnitLevel7();
        prueba.UpdateOrgUnitLevel8();

    }



    //Org Unit Groups
    public void UpdateOrgbyCountry() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-groups/oug-by-country");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/org-unit-groups/oug-by-country","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }

    public void UpdateOrgbyProgram() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-groups/oug-by-program");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/org-unit-groups/oug-by-program","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }

    public void UpdateOrgforAssignment() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-groups/oug-for-assignment");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/org-unit-groups/oug-for-assignment","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }

    public void UpdateOrgGeneral() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-groups/oug-general");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/org-unit-groups/oug-general","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }





    //Shape Files Update
    public void UpdateShapeFilesDetails() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/shape-files/optimized");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }

    public void UpdateShapeOptimized() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/shape-files/detailed");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }





    //Common Data Elements
    public void UpdateCommonDataElements() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/1-who-global");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }




    //Dashoard
    public void UpdateDashboards() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/1-who-global");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }



    //Indicators
    public void UpdateIndictors() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/1-who-global");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }





    //Data Collection Forms
    public void UpdateDataSets() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/data-collection-forms/datasets");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }


    public void UpdateProgramsWoRegistration() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/data-collection-forms/programs-wo-registration");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }



    public void UpdateProgramsWRegistration() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/1-who-global");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }




    //General
    public void UpdateAttributes() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/1-who-global");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }

    public void UpdateCategoryCombinations() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/1-who-global");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }

    public void UpdateValidationRules() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/1-who-global");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }




    //User Management
    public void UpdateUserGroups() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/1-who-global");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }

    public void UpdateUserRoles() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/1-who-global");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }



    public void UpdateOrgUnitLevel1() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/1-who-global");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/1-who-global","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }




    //UPDATE FOR ORG_UNIT_LVLs
    public void UpdateOrgUnitLevel2() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/2-who-regions");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/2-who-regions","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }

    public void UpdateOrgUnitLevel3() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/3-who-member-states");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/3-who-member-states","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");


    }

    public void UpdateOrgUnitLevel4() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/4-level1");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/4-level1","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }

    public void UpdateOrgUnitLevel5() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/5-level2");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/5-level2","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }

    public void UpdateOrgUnitLevel6() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/6-level3");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/6-level3","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }


    public void UpdateOrgUnitLevel7() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/7-level4");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/7-level4","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }

    public void UpdateOrgUnitLevel8() throws IOException, SVNException {
        File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba/org-unit-tree/org-unit-levels/8-level8");
        URL url_aux = new URL("http://who-dev.essi.upc.edu:8081/api/organisationUnitLevels/eI3Bg6uFNKO");
        UpdateGeneralSVN("svn://who-dev.essi.upc.edu/metadata-repository/org-unit-tree/org-unit-levels/8-level8","mr-service","hatre5EpSVN",Repository,url_aux,"vmurciano","Vict0r2017#");
    }




    public void UpdateGeneralSVN (String url, String UserNameSVN, String passwordSVN, File Repository, URL DHIS2url, String DHIS2user, String DHIS2pass) throws SVNException, IOException {

        SVNRepositoryFactoryImpl.setup();
        SVNRepository repository = null;
        long startRevision = 0;
        long endRevision = -1; //HEAD (the latest) revision

        try {
            SVNRepositoryFactoryImpl.setup();
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(UserNameSVN, passwordSVN);
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

        //Aqui vemos todos los cambios que han ocurrido
        Collection logEntries = null;
        logEntries = repository.log( new String[] { "" } , null , startRevision , endRevision , true , true );
        for (Iterator entries = logEntries.iterator( ); entries.hasNext( );) {
            SVNLogEntry logEntry = (SVNLogEntry) entries.next();
            System.out.println("---------------------------------------------");
            System.out.println("revision: " + logEntry.getRevision());
            System.out.println("date: " + logEntry.getDate());
            lastUpdate = logEntry.getDate();
            System.out.println("log message: " + logEntry.getMessage());

            if (logEntry.getChangedPaths().size() > 0) {
                System.out.println();
                System.out.println("changed paths:");
                Set changedPathsSet = logEntry.getChangedPaths().keySet();

                for (Iterator changedPaths = changedPathsSet.iterator(); changedPaths.hasNext(); ) {
                    SVNLogEntryPath entryPath = (SVNLogEntryPath) logEntry.getChangedPaths().get(changedPaths.next());
                    System.out.println(" "
                            + entryPath.getType()
                            + " "
                            + entryPath.getPath()
                            + ((entryPath.getCopyPath() != null) ? " (from  "
                            + entryPath.getCopyPath() + " revision "
                            + entryPath.getCopyRevision() + ")" : ""));
                }
            }
        }


        URLConnection uc = DHIS2url.openConnection();
        String userpass = DHIS2user + ":" + DHIS2pass;
        String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
        uc.setRequestProperty ("Authorization", basicAuth);
        InputStream in = uc.getInputStream();

        String LastUpdate2;

        try (InputStream is = in;
             JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            System.out.println(obj.getString("id"));
            System.out.println("----------- ");
            System.out.printf("Fecha obtenida del Json: ");
            System.out.println(obj.getString("lastUpdated"));
            LastUpdate2 = obj.getString("lastUpdated");
            System.out.printf("Fecha obtenida del SVN: ");
            System.out.println(lastUpdate);
            System.out.println("-----------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

