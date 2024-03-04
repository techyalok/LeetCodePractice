package RandomQ;
import java.util.Arrays;

public class GraphColoring {

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 1, 0}
        };

        String[] colors = {"Red", "Green", "Blue"};

        String[] colorAssignment = new String[graph.length];
        Arrays.fill(colorAssignment, "Uncolored");

        if (graphColoring(graph, colors, colorAssignment, 0)) {
            System.out.println("Graph can be colored with Red, Green, and Blue.");
            System.out.println("Color assignment: " + Arrays.toString(colorAssignment));
        } else {
            System.out.println("Graph cannot be colored with Red, Green, and Blue.");
        }
    }

    private static boolean graphColoring(int[][] graph, String[] colors, String[] colorAssignment, int vertex) {
        if (vertex == graph.length)
            return true;

        for (String color : colors) {
            if (isSafe(graph, vertex, colorAssignment, color)) {
                colorAssignment[vertex] = color;

                if (graphColoring(graph, colors, colorAssignment, vertex + 1))
                    return true;

                colorAssignment[vertex] = "Uncolored";
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] graph, int vertex, String[] colorAssignment, String color) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[vertex][i] == 1 && colorAssignment[i].equals(color))
                return false;
        }
        return true;
    }
}
