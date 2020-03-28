package NeuralNet;

import Logging.EventType;
import Logging.Logger;

import java.util.ArrayList;

public abstract class LayerHandler {

    private static ArrayList<Layer> layers;

    public void addLayerToEnd(Layer layer){

    }

    public Layer getLayerAtIndex(int index) throws Exception {
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
