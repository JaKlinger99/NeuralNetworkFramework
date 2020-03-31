package NeuralNet;


import Data.InputManager;
import Logging.EventType;
import Logging.Logger;

//Verwaltet die elementaren Funktionalitäten eines Netzes
public abstract class NetManager {

    public static NeuralNetwork net;

    public static NeuralNetwork getNet() {
        return net;
    }

    public static void setNet(NeuralNetwork net) {
        net = net;
    }

    //führt alle Funktionen aus die nötig sind um ein Netz X mal mit den Daten im Input Manager zu trainieren
    public static void trainXTimes(int x){
        for (int i = 0; i < x; i++) {
            trainNet();
        }
    }


    //führt alle Funktionen zum einmaligen trainieren des Netzes aus
    private static void trainNet(){
        Logger.getNotification(EventType.INFO, "Neuer Trainungsdurchlauf gestartet");
        for (int i = 0; i < InputManager.getNumberOfInputs(); i++) {
            
            net.loadInputAndExpectedResult(i);

            net.calculatePrediction();


        }
    }
}
