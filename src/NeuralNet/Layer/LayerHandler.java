package NeuralNet.Layer;

import Logging.EventType;
import Logging.Logger;
import NeuralNet.Layer.Layer;


import java.util.ArrayList;

/**
 * abstrakte Klasse zur Verwaltung von Layer Objekten
 */
public abstract class LayerHandler {

    private static ArrayList<Layer> layers = new ArrayList<>();

    public static void addLayerToEnd(Layer layer){
        layers.add(layer);
        Logger.getNotification(EventType.INFO, "Layer zur Liste hinzugefuegt");
    }

    /**
     * Funktion, welche die Ebene mit dem gegebenen Index zurueck gibt
     * @param index Index der gesuchten Ebene
     * @return gesuchte Ebene
     * @throws Exception Fehler, falls keine Ebene mit gesuchtem Index existiert
     */
    public static Layer getLayerAtIndex(int index) throws Exception {
        if(index >= layers.size()){
            Logger.getNotification(EventType.CRITTICAL_ERROR, "Zugriff auf ungültiges Element einer Ebene");
            throw new Exception("Fehler @LayerHandler.getLayerAtIndex()");
        }else{
            return layers.get(index);
        }

    }

    /**
     * Funktion, welche die letzte Ebene des Netzes returned, ohne wissen zu muessen welchen Index diese hat
     * @return letzte Ebene des Netzes
     */
    public static Layer getOutputLayer(){
        return layers.get(layers.size()-1);
    }


    public static int getNumberOfLayers(){
        return layers.size();
    }

    /**
     * Funktion, die sich um die eigentliche Forward Propagation bzw. Berechnung einer Vorhersage kuemmert
     */
    public static void calculatePrediction(){
        for (int i = 0; i < layers.size(); i++) {
            try{
                getLayerAtIndex(i).createPrediction();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Funktion, die dafuer sorgt, dass am Ende einer Backpropagation alle Matrizen der Ebenen wieder resettet werden
     */
    public static void resetAllLayers(){
        for (Layer l: layers) {
            l.reset();
        }
        Logger.getNotification(EventType.INFO, "Alle Layer Matrizen wurden resettet");
    }




}
