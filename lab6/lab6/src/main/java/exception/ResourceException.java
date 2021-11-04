package exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ResourceException extends Exception {
    
    public ResourceException(String errorMessage)
    {
        super(errorMessage);
    }
    
    public ResourceException(String errorMessage, Throwable root)
    {
        super(errorMessage, root);
    }
}
