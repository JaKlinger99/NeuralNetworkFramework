package Logging;

public abstract class Logger {


    private static LoggingLevel level = LoggingLevel.HIGH;

    public static void getNotification(EventType type, String message){
        switch(level){
            case INACTIVE:
                break;
            case LOW:
                switch(type){
                    case CRITTICAL_ERROR:
                        handleCriticalError(message);
                        break;
                }

                break;
            case MIDDLE:
                switch (type){
                    case CRITTICAL_ERROR:
                        handleCriticalError(message);
                        break;
                    case IMPORTANT_SUCCESS:
                        handleImportantSuccess(message);
                        break;
                }

                break;
            case HIGH:
                switch (type){
                    case CRITTICAL_ERROR:
                        handleCriticalError(message);
                        break;
                    case IMPORTANT_SUCCESS:
                        handleImportantSuccess(message);
                        break;
                    case MINOR_SUCCESS:
                        handleMinorSucess(message);
                        break;
                    case INFO:
                        handleInfo(message);
                        break;
                }

                break;
        }
    }

    private static void handleCriticalError(String message){
        System.out.println("CriticalError: " + message);
    }

    private static  void handleImportantSuccess(String message){
        System.out.println("Major Success: "+ message);
    }

    private static void handleMinorSucess(String message){
        System.out.println("Minor Success: " + message);
    }

    private static void handleInfo(String message){
        System.out.println("Info: " + message);
    }

    public static void setLevel(LoggingLevel l){
        level = l;
    }
}
