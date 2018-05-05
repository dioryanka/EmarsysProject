import exception.NotValidInputException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;

public class DestinationRepository {

    private static String REGEX = "[a-z]=>|[a-z]=>[a-z]";

    public List<String> readDestinationsFromFile(String filePath) throws Exception {
        File file = new File(filePath);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        List<String> destinations = Collections.emptyList();
        while ((line = bufferedReader.readLine()) != null) {
            if (line.matches(REGEX)) {
                destinations.add(line);
            } else {
                throw new NotValidInputException("The given line: " + line + " should match on " + REGEX);
            }
        }

        return destinations;
    }
}
