package NeuralNet.Layer;
import Logging.EventType;
import Math.Matrix;
import Math.Operations;
import Logging.Logger;
import NeuralNet.Weights.WeightHandler;


/**
 * Klasse zur Darstellung einer Ebene des Netzes
 * @author Jakob
 */
public class Layer {

    private int index;
    private int numberOfNodes;
    private Matrix nodes;
    private ActivationFunction activationFunction;

    private Matrix preActivationMatrix;
    private Matrix nodeDeltas;


    //Erzeuge neue Ebene
    public Layer(int nodesNumber, int indexInNet){
        numberOfNodes = nodesNumber;
        index = indexInNet;
        nodes = new Matrix(new double[1][nodesNumber]);

        preActivationMatrix = new Matrix(new double[1][nodesNumber]);
        nodeDeltas = new Matrix(new double[1][nodesNumber]);


        Logger.getNotification(EventType.INFO, "neue Ebene mit Index: " + index + "erzeugt");
        activationFunction = ActivationFunction.RELU;
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

    public Matrix getPreActivationMatrix() {
        return preActivationMatrix;
    }

    public void setPreActivationMatrix(Matrix preActivationMatrix) {
        this.preActivationMatrix = preActivationMatrix;
    }

    public Matrix getNodeDeltas() {
        return nodeDeltas;
    }

    public void setNodeDeltas(Matrix nodeDeltas) {
        this.nodeDeltas = nodeDeltas;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public void createPrediction(){
        if(index != 0){
            try{
                Matrix result = Operations.multiplyMat(LayerHandler.getLayerAtIndex(index - 1).getMatrix(), WeightHandler.getPredecessor(this).getMatrix());
                preActivationMatrix = result;
                result = Operations.applyActivationFunction(result,activationFunction);
                nodes = result;
            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }

    public void replaceMatrixWith(Matrix m) throws Exception {
        if (m.getColumns() == nodes.getColumns() && m.getRows() == nodes.getRows()){
            nodes = m;
        }else{
            Logger.getNotification(EventType.CRITTICAL_ERROR, "Versuch eine Matrix mit einer unpassenden zu Ersetzen");
            throw new Exception("Fehler @Layer.replaceMatrixwith()");
        }
    }


    public void reset() {
        nodes = new Matrix(new double[1][numberOfNodes]);
        preActivationMatrix = new Matrix(new double[1][numberOfNodes]);
        nodeDeltas = new Matrix(new double[1][numberOfNodes]);

    }


}
