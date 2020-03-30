package NeuralNet;

import Logging.EventType;
import Logging.Logger;

public class NeuralNetwork {

    public NeuralNetwork(){

    }

    //Erzeugt automatisch passende Gewichtsmatrizen und Layers
    //jedes Element der Liste repräsentiert eine Ebene -> Zahl gibt Anzahl der Knoten an
    public void createNet(int[] layers){
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

    public void calculatePrediction(){
        Logger.getNotification(EventType.INFO, "Beginn einer Vorhersage");
        LayerHandler.calculatePrediction();
    }

    


}
