import Data.InputManager;
import Data.TrainingData;
import NeuralNet.NetLogic.NeuralNetwork;
import NeuralNet.NetLogic.NetManager;

public class Main {

    public static void main(String[] args){
        int[] input = {3,3,1};
        NeuralNetwork n = new NeuralNetwork(input);
        NetManager.setNet(n);

        init();

        NetManager.trainXTimes(10);



    }

    private static void init(){
        double[] din = {0,1,0};
        double[] dout = {1};
        TrainingData d1 = new TrainingData(din,dout);
        InputManager.addInput(d1);
    }
}
