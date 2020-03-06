package exercise3;

public interface Subject {

    String getStatus();
    public void setStatus(String status);
    void registerObserver(Observer observer);
    public void notifyObservers();
}
