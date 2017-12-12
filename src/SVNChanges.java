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


public class SVNChanges {


  public static void main(String[] argv) throws MalformedURLException, SVNException {

      //Set up connection protocols support:

      SVNRepositoryFactoryImpl.setup();
      String url = "svn://who-dev.essi.upc.edu/metadata-repository/";
      String name = "mr-service";
      String password = "hatre5EpSVN";
      File Repository = new File("C:/Users/Victor/Desktop/metadata-repository_prueba");
      SVNRepository repository = null;
      long startRevision = 0;
      long endRevision = -1; //HEAD (the latest) revision

      //Configuramos el repositorio con el que trabajaremos
    try {
        SVNRepositoryFactoryImpl.setup();
        repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, password);
        repository.setAuthenticationManager(authManager);
    } catch (SVNException e) {
        e.printStackTrace();
        System.exit(1);
    }
    //work with the repository


      SVNClientManager ourClientManager = SVNClientManager.newInstance(null, repository.getAuthenticationManager());
      SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
      updateClient.setIgnoreExternals(false);
      updateClient.doCheckout(SVNURL.parseURIEncoded(url),Repository,SVNRevision.HEAD,SVNRevision.HEAD, true);


      //Aqui vemos todos los cambios que han ocurrido
      Collection logEntries = null;
      logEntries = repository.log( new String[] { "" } , null , startRevision , endRevision , true , true );
      for (Iterator entries = logEntries.iterator( ); entries.hasNext( ); ) {
          SVNLogEntry logEntry = (SVNLogEntry) entries.next();
          System.out.println("---------------------------------------------");
          System.out.println("revision: " + logEntry.getRevision());
          System.out.println("author: " + logEntry.getAuthor());
          System.out.println("date: " + logEntry.getDate());
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
                          + ((entryPath.getCopyPath() != null) ? " (from "
                          + entryPath.getCopyPath() + " revision "
                          + entryPath.getCopyRevision() + ")" : ""));
              }
          }
      }
    }
}

