package dled.github.com;

public class NoApp implements AppToRun {
    @Override
    public void run() {
        System.out.println("Run either with 'server' or 'worker' as a single argument");
    }
}
