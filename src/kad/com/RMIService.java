package kad.com;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RMIService extends Remote {
	

	String exc(String url, String keyword, int type) throws RemoteException;
		
	
}
