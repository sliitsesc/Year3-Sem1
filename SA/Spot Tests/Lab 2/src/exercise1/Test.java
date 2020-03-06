package exercise1;

public class Test {

    public static void main(String[] args) {
        TV lgTV = new LGTV();
        TV sonyTV = new SonyTV();

        new RemoteControllerImpl(lgTV).on();
        new RemoteControllerImpl(lgTV).off();
        new RemoteControllerImpl(lgTV).tune(10);
        new RemoteControllerImpl(sonyTV).on();
        new RemoteControllerImpl(sonyTV).off();
        new RemoteControllerImpl(sonyTV).tune(20);

    }

}
