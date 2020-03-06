package exercise3;

public class ObserverImpl implements Observer {

    private String observerName;

    public ObserverImpl(String observerName){
        this.observerName = observerName;
    }

    @Override
    public void update(String status) {
        System.out.println("Observer received state change of subject ID is = " + observerName + " Status = " + status);
    }
}
