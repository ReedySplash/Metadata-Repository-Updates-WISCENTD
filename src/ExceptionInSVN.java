import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;

import java.io.File;

public class ExceptionInSVN extends SVNException {

    public ExceptionInSVN(String s) {
        super(SVNErrorMessage.create(SVNErrorCode.CANCELLED,s));
        File UpdateFile = new File("UpdatePath.txt");
        if (UpdateFile.exists()) {
            UpdateFile.delete();
        }

    }
}
