package NeuralNet.NetLogic;

import Data.InputManager;
import Logging.EventType;
import Logging.Logger;
import Math.Matrix;
import NeuralNet.Layer.Layer;
import NeuralNet.Layer.LayerHandler;
import NeuralNet.Weights.Weight;
import NeuralNet.Weights.WeightHandler;


/**
 * Eine Klasse, die sich um die Elementaren Operationen kümmert, die zum Trainieren eines Neuronalen Netzes nötig sind
 *
 * @author Jakob
 */
public class NeuralNetwork {

    private Matrix expectedResult;

    public NeuralNetwork(int[] layers){
        createNet(layers);
    }

    /**
     * Eine Funktion die automatisch ein Netz (Layers + Weights) mit den gegebenen Spezifikationen erzeugt
     * @param layers jeder Integer steht für die Anzahl an Knoten in einer neuen Ebene
     */
    private void createNet(int[] layers){
        for (int i = 0; i < layers.length; i++) {
            Layer layer = new Layer(layers[i], i);
            LayerHandler.addLayerToEnd(layer);
            if(i != 0){
                try{
                    Weight w = new Weight(LayerHandler.getLayerAtIndex(i-1), LayerHandler.getLayerAtIndex(i));
                    WeightHandler.addWeight(w);
                } catch(Exception e){
                    Logger.getNotification(EventType.CRITTICAL_ERROR, "Fehler bei der Zuordnung der Netze");
                    e.printStackTrace();
                }
            }
        }


    }


    /**
     * Eine Funktion, die die noetigen Testdaten (Input layer und expectedResult) läd
     * @param i index der zu ladenden Testdaten
     */
    public void loadInputAndExpectedResult(int i){
        try {
            expectedResult = InputManager.getExpectedResultAt(i);
            LayerHandler.getLayerAtIndex(0).replaceMatrixWith(InputManager.getInputsAt(i));
            Logger.getNotification(EventType.INFO, "Neue Trainingsdaten geladen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Eine Funktion, die bei vorhandenen Input Daten die Forward Propagation ausfuehrt und eine Vorhersage trifft
     */
    public void calculatePrediction(){
        Logger.getNotification(EventType.INFO, "Beginn einer Vorhersage");
        LayerHandler.calculatePrediction();
    }


    /**
     * Eine Funktion, die nach der Forward Propagation die Loss Funktion zwischen dem korrekten Ergebnis aus den Testdaten (exptectedResult) und dem tatsächlichen Ergebnis ermittelt
     * @return Ergebnis der Loss Funktion bei dieser einzelnen Eingabe
     */
    public double calculateLoss(){
        double loss = 0;
        Matrix output = LayerHandler.getOutputLayer().getMatrix();
        for (int i = 0; i < expectedResult.getColumns(); i++) {
            loss += (expectedResult.getValueAt(0, i) - output.getValueAt(0,i)) * (expectedResult.getValueAt(0, i) - output.getValueAt(0,i));
        }
        return loss;
    }








    


}
