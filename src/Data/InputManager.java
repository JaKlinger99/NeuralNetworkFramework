package Data;
import Logging.EventType;
import Logging.Logger;
import Math.Matrix;
import java.util.ArrayList;

public abstract class InputManager {

    private static ArrayList<TrainingData> trainingExamples= new ArrayList<>();

    public static void addInput(TrainingData trainingData){
        trainingExamples.add(trainingData);
    }

    public static int getNumberOfInputs(){
        return trainingExamples.size();
    }

    public static Matrix getInputsAt(int index) throws Exception {
        if (index >= trainingExamples.size()){
            Logger.getNotification(EventType.CRITTICAL_ERROR, "Zugriff auf ungültige Trainingsdaten");
            throw new Exception("Fehler @InputManager.getInputsAt()");
        }else{
            Matrix result =  new Matrix(trainingExamples.get(index).getInputs());
            return result;
        }

    }

    public static Matrix getExpectedResultAt(int index) throws Exception {
        if (index >= trainingExamples.size()){
            Logger.getNotification(EventType.CRITTICAL_ERROR, "Zugriff auf ungültige Trainingsdaten");
            throw new Exception("Fehler @InputManager.getInputsAt()");
        }else{
            Matrix result =  new Matrix(trainingExamples.get(index).getExpectedResults());
            return result;
        }

    }





}
