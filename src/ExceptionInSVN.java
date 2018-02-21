import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;

import java.io.File;

public class ExceptionInSVN extends Exception {

    public ExceptionInSVN(String s) {
        super(s);
        File UpdateFile = new File("UpdatePath.txt");
        if (UpdateFile.exists()) {
            UpdateFile.delete();
        }


    }
}
