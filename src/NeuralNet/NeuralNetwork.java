package NeuralNet;

import Data.InputManager;
import Logging.EventType;
import Logging.Logger;
import Math.Matrix;


//eine Klasse, die die elementaren Funktionen eines Neuronalen Nets ausführen kann
public class NeuralNetwork {

    private Matrix expectedResult;

    public NeuralNetwork(int[] layers){
        createNet(layers);
    }

    //Erzeugt automatisch passende Gewichtsmatrizen und Layers
    //jedes Element der Liste repräsentiert eine Ebene -> Zahl gibt Anzahl der Knoten an
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


    //Läd input in die Input Layer des
    public void loadInputAndExpectedResult(int i){
        try {
            expectedResult = InputManager.getExpectedResultAt(i);
            LayerHandler.getLayerAtIndex(0).replaceMatrixWith(InputManager.getInputsAt(i));
            Logger.getNotification(EventType.INFO, "Neue Trainingsdaten geladen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //berechnet eine Vorhersage für die in der Input Layer vorliegenden Daten
    public void calculatePrediction(){
        Logger.getNotification(EventType.INFO, "Beginn einer Vorhersage");
        LayerHandler.calculatePrediction();
    }








    


}
