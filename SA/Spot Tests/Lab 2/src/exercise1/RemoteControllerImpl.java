package exercise1;

public class RemoteControllerImpl implements RemoteController {

    private TV tv;

    RemoteControllerImpl(TV tv){
        this.tv = tv;
    }

    @Override
    public void on() {
        tv.on();
    }

    @Override
    public void off() {
        tv.off();
    }

    @Override
    public void tune(int channel) {
        tv.tune(channel);
    }
}
