package NeuralNet;

import Logging.EventType;
import Logging.Logger;


import java.util.ArrayList;

public abstract class WeightHandler {

    private ArrayList<Weight> weights;

    public Weight getPredecessor(Layer layer) throws Exception {
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

    public Weight getSuccessor(Layer layer) throws Exception {
        if(LayerHandler.getSize() - 1 == layer.getIndex()){
            //Letzte Ebene ohne Nachfolgende Gewichte
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
}
