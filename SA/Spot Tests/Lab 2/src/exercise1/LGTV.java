package exercise1;

public class LGTV implements TV {
    @Override
    public void on() {
        System.out.println("Switched on LG TV");
    }

    @Override
    public void off() {
        System.out.println("Switched off LG TV");
    }

    @Override
    public void tune(int channel) {
        System.out.println("Switch on channel in LG tv is "+ channel);
    }
}
