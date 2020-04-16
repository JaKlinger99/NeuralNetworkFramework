package NeuralNet.Weights;

import Logging.EventType;
import Logging.Logger;
import NeuralNet.Layer.Layer;
import NeuralNet.Layer.LayerHandler;
import NeuralNet.Weights.Weight;


import java.util.ArrayList;


/**
 * Klasse zur Verwaltung von Gewichtsobjekten
 * @author Jakob
 */
public abstract class WeightHandler {

    private static ArrayList<Weight> weights = new ArrayList<>();


    public static Weight getPredecessor(Layer layer) throws Exception {
        if (layer.getIndex() == 0){
            return null;
        }
        for (Weight w: weights) {
            if (w.getSuccessor() == layer){
                return w;
            }
        }
        Logger.getNotification(EventType.CRITTICAL_ERROR, "Fehler beim Bestimmen der Input Gewicht für Layer: "+ layer.getIndex());
        throw new Exception("Fehler @WeightHandler.getPredecessor()");
    }

    public static Weight getSuccessor(Layer layer) throws Exception {
        if(LayerHandler.getOutputLayer().getIndex() == layer.getIndex()){
            return null;
        }
        for (Weight w: weights) {
            if (w.getPredecessor() == layer){
                return w;
            }
        }
        Logger.getNotification(EventType.CRITTICAL_ERROR, "Fehler beim Bestimmen der Output Gewichte für Layer: "+ layer.getIndex());
        throw new Exception("Fehler @WeightHandler.getSuccessor()");
    }

    public static void addWeight(Weight w){
        weights.add(w);
        Logger.getNotification(EventType.INFO, "Gewicht zur Liste hinzugefügt");
    }



}
