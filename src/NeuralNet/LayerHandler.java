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

    public static void calculatePrediction(){
        for (int i = 0; i < layers.size(); i++) {
            try{
                getLayerAtIndex(i).createPrediction();
            }catch(Exception e){
                e.printStackTrace();
            }
            if(i == layers.size()-1){
                Logger.getNotification(EventType.MINOR_SUCCESS, "Vorhersage getroffen:");
            }
        }
    }


}
