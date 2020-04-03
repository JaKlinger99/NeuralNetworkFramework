package NeuralNet.NetLogic;


import Data.InputManager;
import Logging.EventType;
import Logging.Logger;

/**
 * Eine Klasse zum Verwalten der Funktionen eines neuronalen Netzes
 *
 * @author Jakob
 */
public abstract class NetManager {

    private static NeuralNetwork net;

    public static NeuralNetwork getNet() {
        return net;
    }

    public static void setNet(NeuralNetwork net) {
        net = net;
    }

    /**
     * Eine Funktion, die das Netz X mal trainiert
     * @param x Anzahl der Trainingsdurchläufe
     */
    public static void trainXTimes(int x){
        for (int i = 0; i < x; i++) {
            trainNet();
        }
    }


    /**
     * Eine Funktion, die alle Schritte zum einmaligen Trainieren des Netzes ausführt
     *
     */
    private static void trainNet(){
        Logger.getNotification(EventType.INFO, "Neuer Trainungsdurchlauf gestartet");
        double error = 0;
        for (int i = 0; i < InputManager.getNumberOfInputs(); i++) {
            
            net.loadInputAndExpectedResult(i);

            net.calculatePrediction();


            error += net.calculateLoss();
        }
        double avgError = error/InputManager.getNumberOfInputs();
        Logger.getNotification(EventType.MINOR_SUCCESS, "Trainingsdurchlauf über alle Trainingsdaten beendet. Error: " + avgError);
    }
}
