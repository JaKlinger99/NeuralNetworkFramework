package NeuralNet;
import Logging.EventType;
import Math.Matrix;
import Math.Operations;
import Logging.Logger;

public class Layer {

    private int index;
    private int numberOfNodes;
    private Matrix nodes;


    //Erzeuge neue Ebene
    public Layer(int nodesNumber, int indexInNet){
        numberOfNodes = nodesNumber;
        index = indexInNet;
        nodes = new Matrix(new double[1][nodesNumber]);
        Logger.getNotification(EventType.INFO, "neue Ebene mit Index: " + index + "erzeugt");
    }

    public int getNumberOfNodes(){
        return numberOfNodes;
    }

    public int getIndex(){
        return index;
    }

    public Matrix getMatrix() {
        return nodes;
    }

    public void createPrediction(){
        if(index != 0){
            try{
                Matrix result = Operations.multiplyMat(LayerHandler.getLayerAtIndex(index - 1).getMatrix(),WeightHandler.getPredecessor(this).getMatrix());
                nodes = result;
            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }
}
