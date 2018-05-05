import java.util.List;

public class Main {

    private static final String FILE_PATH = "input.txt";

    public static void main(String args[]) {
        DestinationRepository destinationRepository = new DestinationRepository();
        OptimalPathService optimalPathService = new OptimalPathService();
        try {
            List<String> destinations = destinationRepository.readDestinationsFromFile(FILE_PATH);
            String result = optimalPathService.calculateOptimalPath(destinations);
            System.out.print(result);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
