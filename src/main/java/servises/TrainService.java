package servises;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TrainService {
    List<Train> trainList;

    ObjectMapper objectMapper=new ObjectMapper();

    private static final String TRAIN_PATH="C:\\Users\\shiv\\java_workspace\\LovePrit_SpringBoot\\IRCTC_TicketBooking\\src\\main\\localDB\\train.json";


    public TrainService(Train train) throws IOException {
        this.trainList = trainList;
        loadTrains();
    }

    public TrainService() throws IOException {
        loadTrains();
    }

    public List<Train> loadTrains() throws IOException {
        File train=new File(TRAIN_PATH);
        // deserialize
        trainList=objectMapper.readValue(train, new TypeReference<List<Train>>() {
        });
        return trainList;
    }


    public List<Train> searchTrain(String source,String destination){

        return trainList.stream().filter(train -> validTrain(train,source,destination)).collect(Collectors.toList());
    }

    private boolean validTrain(Train train,String source,String destination){
        List<String> statinOrder=train.getStation();

        int sourceIndex=statinOrder.indexOf(source.toLowerCase());
        int destinationIndex=statinOrder.indexOf(destination.toLowerCase());

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }


}
