package NeuralNet;
import Logging.EventType;
import Math.Matrix;
import Logging.Logger;

public class Layer {

    private int index;
    private int numberOfNodes;
    private Matrix nodes;


    //Erzeuge neue Ebene
    public Layer(int nodesNumber, int indexInNet){
        numberOfNodes = nodesNumber;
        index = indexInNet;
        nodes = new Matrix(new double[nodesNumber][1]);
        Logger.getNotification(EventType.INFO, "neue Ebene mit Index: " + index + "erzeugt");
    }

    public int getNumberOfNodes(){
        return numberOfNodes;
    }

    public int getIndex(){
        return index;
    }
}
