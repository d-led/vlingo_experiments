package dled.github.com;

public class App {
    public static void main(String[] args) {
        chooseAppFrom(args).run();
    }

    // all apps in one jar for the demo simplicity
    static AppToRun chooseAppFrom(String[] args) {
        if (args.length == 1) {
            switch (args[0]) {
                case "server":
                    return new ServerApp();
                case "worker":
                    return new WorkerApp();
            }
        }
        return new NoApp();
    }
}
