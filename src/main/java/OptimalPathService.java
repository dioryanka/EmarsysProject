import java.util.*;
import java.util.stream.Collectors;

/**
 * This class calculate the optimal travelseas of the destinations.
 * X=>U, X is destination, U is the ordering condition of this destination.
 */
public class OptimalPathService {

    private static String SEPARATOR = "=>";
    private static int MIN_LENGTH_OF_STRING = 1;

    private List<Stack<String>> prioritizedPaths = new ArrayList<Stack<String>>();
    private Set<String> visitedElements = new HashSet<String>();
    private Set<String> printedElements = new HashSet<String>();
    private Map<String, String> destinationPairs = new HashMap<String, String>();

    public String calculateOptimalPath(final List<String> destinations) {
        loadDestinationPairs(destinations);
        List<String> destinationsWithoutPrioritizationCondition = destinations.stream().map( destination -> {
            String[] str = destination.split(SEPARATOR);
            return str[0];
        }).collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        for (String destination : destinationsWithoutPrioritizationCondition) {
            Stack<String> stack = findPrioritizedDestinations(destination);
            if (stack != null) {
                builder.append(getStackElementsAsString(stack));
            } else if (!visitedElements.contains(destination) && destinationPairs.containsKey(destination)) {
                prioritizedPaths.add(buildStack(destination, new Stack<String>()));
            } else if (!visitedElements.contains(destination)) {
                visitedElements.add(destination);
                printedElements.add(destination);
                builder.append(destination);
            }
        }

        //append the rest of the elements
        //at that point we leaved the ordering conditions of these elements
        //e.g.: U is printed and x=>U, so x will be after U
        visitedElements.removeAll(printedElements);
        visitedElements.forEach(destination -> builder.append(destination));
        return builder.toString();
    }

    //LinkedList with stack, where we create path of the destination by ordering condition
    private Stack<String> buildStack(final String destination, final Stack<String> destinationsStack) {
        if (destinationPairs.containsKey(destination)) {
            String prioritizedDestionation = destinationPairs.get(destination);
            destinationsStack.push(destination);
            visitedElements.add(destination);
            return buildStack(prioritizedDestionation, destinationsStack);
        } else {
            destinationsStack.push(destination);
            visitedElements.add(destination);
            return destinationsStack;
        }
    }

    private Stack<String> findPrioritizedDestinations(final String destination) {
        for (Stack stack : prioritizedPaths) {
            if (stack != null && stack.peek().equals(destination)) {
                return stack;
            }
        }

        return null;
    }

    private String getStackElementsAsString(final Stack<String> stack) {
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            String destination = stack.pop();
            printedElements.add(destination);
            builder.append(destination);
        }

        return builder.toString();
    }

    //save the destinations which has ordering condition
    private void loadDestinationPairs(final List<String> destinations) {
        for (String destination : destinations) {
            String[] str = destination.split(SEPARATOR);
            if (str.length > MIN_LENGTH_OF_STRING ) {
                destinationPairs.put(str[0], str[1]);
            }
        }
    }
}
