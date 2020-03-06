package exercise1;

public class SonyTV implements TV {
    @Override
    public void on() {
        System.out.println("Switched on Sony TV");
    }

    @Override
    public void off() {
        System.out.println("Switch off Sony tv");
    }

    @Override
    public void tune(int channel) {
        System.out.println("Switch on channel in Sony tv is "+ channel);
    }
}
