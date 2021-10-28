package core.listeners;

public interface ConnectionListener {
    
    public void onConnection();
    
    public void onDisconnection();
    
    public void onConnectionError();
}
