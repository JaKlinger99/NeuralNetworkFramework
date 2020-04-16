package NeuralNet.NetLogic;


import Data.InputManager;
import Logging.EventType;
import Logging.Logger;
import NeuralNet.Layer.LayerHandler;

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

    public static void setNet(NeuralNetwork network) {
        net = network;
    }

    /**
     * Eine Funktion, die das Netz X mal trainiert
     * @param x Anzahl der Trainingsdurchläufe
     */
    public static void trainXTimes(int x){
        for (int i = 0; i <= x; i++) {
            Logger.getNotification(EventType.INFO, i + "ter Trainingsdurchlauf gestartet");
            trainNet();
        }
    }


    /**
     * Eine Funktion, die alle Schritte zum einmaligen Trainieren des Netzes ausführt
     *
     */
    private static void trainNet(){
        double error = 0;

        for (int i = 0; i < InputManager.getNumberOfInputs(); i++) {
            net.loadInputAndExpectedResult(i);


            net.calculatePrediction();


            Logger.getNotification(EventType.MINOR_SUCCESS, "Vorhersage getroffen:" );

            error += net.calculateCost();

            try {
                net.backpropagade();
            } catch (Exception e) {
                Logger.getNotification(EventType.CRITTICAL_ERROR, "Fehler bei der Backpropagation");
                e.printStackTrace();
            }
        }

        double avgError = error/InputManager.getNumberOfInputs();
        Logger.getNotification(EventType.MINOR_SUCCESS, "Trainingsdurchlauf über alle Trainingsdaten beendet. Error: " + avgError);
        LayerHandler.resetAllLayers();
    }
}
