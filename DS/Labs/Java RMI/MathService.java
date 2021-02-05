import java.rmi.Remote;
import java.rmi.RemoteException;
 
public interface MathService extends Remote{

	public int clientCounter() throws RemoteException;
    public int add(int a, int b) throws RemoteException;
    public int subtract(int a, int b) throws RemoteException;
    public int multiply(int a, int b) throws RemoteException;
    public int divide(int a, int b) throws RemoteException;
}