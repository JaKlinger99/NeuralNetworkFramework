import NeuralNet.NetLogic.NeuralNetwork;
import NeuralNet.NetLogic.NetManager;

public class Main {

    public static void main(String[] args){
        int[] input = {4,4,4,1};
        NeuralNetwork n = new NeuralNetwork(input);
        NetManager.setNet(n);



    }
}
