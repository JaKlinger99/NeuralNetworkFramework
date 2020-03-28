package NeuralNet;

import Logging.EventType;
import Logging.Logger;

import java.util.ArrayList;

public abstract class LayerHandler {

    private static ArrayList<Layer> layers = new ArrayList<>();

    public static void addLayerToEnd(Layer layer){
        layers.add(layer);
        Logger.getNotification(EventType.INFO, "Layer zur Liste hinzugefügt");
    }

    public static Layer getLayerAtIndex(int index) throws Exception {
        if(index >= layers.size()){
            Logger.getNotification(EventType.CRITTICAL_ERROR, "Zugriff auf ungültiges Element einer Ebene");
            throw new Exception("Fehler @LayerHandler.getLayerAtIndex()");
        }else{
            return layers.get(index);
        }

    }

    public static int getSize(){
        return layers.size();
    }


}
