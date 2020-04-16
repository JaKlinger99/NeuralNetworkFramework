package NeuralNet.NetLogic;

import Data.InputManager;
import Logging.EventType;
import Logging.Logger;
import Math.Matrix;
import Math.Operations;
import NeuralNet.Layer.Layer;
import NeuralNet.Layer.LayerHandler;
import NeuralNet.Weights.Weight;
import NeuralNet.Weights.WeightHandler;


/**
 * Eine Klasse, die sich um die Elementaren Operationen kümmert, die zum Trainieren eines Neuronalen Netzes nötig sind
 *
 * @author Jakob
 */
public class NeuralNetwork {

    private Matrix expectedResult;
    private double alpha = 0.1;

    public NeuralNetwork(int[] layers){
        createNet(layers);
    }

    /**
     * Eine Funktion die automatisch ein Netz (Layers + Weights) mit den gegebenen Spezifikationen erzeugt
     * @param layers jeder Integer steht für die Anzahl an Knoten in einer neuen Ebene
     */
    private void createNet(int[] layers){
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


    /**
     * Eine Funktion, die die noetigen Testdaten (Input layer und expectedResult) läd
     * @param i index der zu ladenden Testdaten
     */
    public void loadInputAndExpectedResult(int i){
        try {
            expectedResult = InputManager.getExpectedResultAt(i);
            LayerHandler.getLayerAtIndex(0).replaceMatrixWith(InputManager.getInputsAt(i));
            Logger.getNotification(EventType.INFO, "Neue Trainingsdaten geladen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Eine Funktion, die bei vorhandenen Input Daten die Forward Propagation ausfuehrt und eine Vorhersage trifft
     */
    public void calculatePrediction(){
        Logger.getNotification(EventType.INFO, "Beginn einer Vorhersage");
        LayerHandler.calculatePrediction();
    }


    /**
     * Eine Funktion, die nach der Forward Propagation die Cost Funktion zwischen dem korrekten Ergebnis aus den Testdaten (exptectedResult) und dem tatsächlichen Ergebnis ermittelt
     * @return Ergebnis der Cost Funktion bei dieser einzelnen Eingabe
     */
    public double calculateCost(){
        double loss = 0;
        Matrix output = LayerHandler.getOutputLayer().getMatrix();
        for (int i = 0; i < expectedResult.getColumns(); i++) {
            loss += (expectedResult.getValueAt(0, i) - output.getValueAt(0,i)) * (expectedResult.getValueAt(0, i) - output.getValueAt(0,i));
        }
        return loss;
    }


    /**
     * Funktion, die nachdem die Cost Funktion berechnet wurde eine Backpropagation ausfuehrt und dazu auf folgende Funktionen zugreift:
     * - calculateNodeDelta
     */
    public void backpropagade() throws Exception {
        for (int i = LayerHandler.getNumberOfLayers() - 1; i > 0 ; i--) {
            Layer layer = null;
            try {
                layer = LayerHandler.getLayerAtIndex(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int j = 0; j < layer.getNumberOfNodes(); j++) {
                calculateNodeDelta(layer, j);
                for (int k = 0; k < LayerHandler.getLayerAtIndex(layer.getIndex()-1).getNumberOfNodes(); k++) {
                    calculateWeightDerivative(WeightHandler.getPredecessor(layer),k,j);
                }
            }
            WeightHandler.getPredecessor(layer).applyGradientMatrix();

        }


    }

    /**
     * Funktion, die das nodeDelta für die Backpropagation berechnet
     * @param layer
     * @param nodeIndex
     * @return
     */
    public double calculateNodeDelta(Layer layer, int nodeIndex){
        double result = 0;
        if(LayerHandler.getOutputLayer() != layer){
            //HiddenLayer
            try {
                Layer nextLayer = LayerHandler.getLayerAtIndex(layer.getIndex()+1);

                for (int i = 0; i < nextLayer.getNumberOfNodes(); i++) {
                    result += nextLayer.getNodeDeltas().getValueAt(0,i) * Operations.derivativeOfActivationFunction(nextLayer.getPreActivationMatrix().getValueAt(0,i),nextLayer.getActivationFunction())
                            * WeightHandler.getSuccessor(layer).getMatrix().getValueAt(nodeIndex,i);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            //Output Layer
            // nodeDelta = out - target
            result = layer.getMatrix().getValueAt(0,nodeIndex) - expectedResult.getValueAt(0, nodeIndex);
        }
        layer.getNodeDeltas().setValueAt(0,nodeIndex,result);
        return result;
    }

    /**
     * Funktion, die berechnet um welchen Wert ein Gewicht geaendert werden soll
     * @param weight
     * @param startNodeIndex
     * @param endNodeIndex
     * Negatives Vorzeichen!
     */
    public void calculateWeightDerivative(Weight weight, int startNodeIndex, int endNodeIndex){
        double result =  - weight.getPredecessor().getMatrix().getValueAt(0,startNodeIndex) *
                Operations.derivativeOfActivationFunction(weight.getSuccessor().getPreActivationMatrix().getValueAt(0,endNodeIndex),weight.getSuccessor().getActivationFunction())
                * weight.getSuccessor().getNodeDeltas().getValueAt(0,endNodeIndex) * alpha;
        weight.addValueToGradientMatrix(startNodeIndex, endNodeIndex,result);
    }












    


}
