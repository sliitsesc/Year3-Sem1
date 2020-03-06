package exercise3;

import java.util.ArrayList;
import java.util.List;

public class SubjectImpl implements Subject{
    private List<Observer> observerList = new ArrayList<>();
    private String status;

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
        notifyObservers();
    }

    public void registerObserver(Observer observer){
        observerList.add(observer);
    }

    public void notifyObservers(){
        for (Observer observer: observerList
             ) {
            observer.update(status);
        }
    }
}
