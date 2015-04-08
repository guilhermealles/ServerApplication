package rmi.base;
/**
 * class to do some common things for client & server to get RMI working
 * @author srasul
 */
public abstract class RmiStarter {
    public RmiStarter() {
    	// TODO
        //System.setProperty("java.security.policy", PolicyFileLocator.getLocationOfPolicyFile());
        //System.setProperty("java.security.policy", "file:/Users/Alles/Development/Eclipse Workspace/RMI-Base/src/allow_all.policy");
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        start();
    }
    /**
     * extend this class and do RMI handling here
     */
    public abstract void start();
}
