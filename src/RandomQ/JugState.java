package RandomQ;
import java.util.*;

public class JugState {
    int jug1;
    int jug2;

    public JugState(int jug1, int jug2) {
        this.jug1 = jug1;
        this.jug2 = jug2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        JugState jugState = (JugState) obj;
        return jug1 == jugState.jug1 && jug2 == jugState.jug2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jug1, jug2);
    }
}

class JugProblem {

    public static void main(String[] args) {
        int jug1Capacity = 4;
        int jug2Capacity = 3;
        int targetAmount = 2;

        System.out.println("BFS Solution:");
        bfs(jug1Capacity, jug2Capacity, targetAmount);

        System.out.println("\nDFS Solution:");
        dfs(jug1Capacity, jug2Capacity, targetAmount);
    }

    private static void bfs(int jug1Capacity, int jug2Capacity, int targetAmount) {
        Queue<JugState> queue = new LinkedList<>();
        Set<JugState> visited = new HashSet<>();

        JugState initialState = new JugState(0, 0);
        queue.offer(initialState);
        visited.add(initialState);

        while (!queue.isEmpty()) {
            JugState currentState = queue.poll();
            if (currentState.jug1 == targetAmount || currentState.jug2 == targetAmount) {
                System.out.println("Solution Found: " + currentState.jug1 + " " + currentState.jug2);
                return;
            }

            List<JugState> nextStates = getNextStates(currentState, jug1Capacity, jug2Capacity);

            for (JugState nextState : nextStates) {
                if (!visited.contains(nextState)) {
                    queue.offer(nextState);
                    visited.add(nextState);
                }
            }
        }

        System.out.println("No solution found.");
    }

    private static void dfs(int jug1Capacity, int jug2Capacity, int targetAmount) {
        Set<JugState> visited = new HashSet<>();
        JugState initialState = new JugState(0, 0);

        dfsHelper(initialState, jug1Capacity, jug2Capacity, targetAmount, visited);
    }

    private static void dfsHelper(JugState currentState, int jug1Capacity, int jug2Capacity, int targetAmount, Set<JugState> visited) {
        if (currentState.jug1 == targetAmount || currentState.jug2 == targetAmount) {
            System.out.println("Solution Found: " + currentState.jug1 + " " + currentState.jug2);
            return;
        }

        visited.add(currentState);
        List<JugState> nextStates = getNextStates(currentState, jug1Capacity, jug2Capacity);

        for (JugState nextState : nextStates) {
            if (!visited.contains(nextState)) {
                dfsHelper(nextState, jug1Capacity, jug2Capacity, targetAmount, visited);
            }
        }
    }

    private static List<JugState> getNextStates(JugState currentState, int jug1Capacity, int jug2Capacity) {
        List<JugState> nextStates = new ArrayList<>();

        // Fill jug1
        if (currentState.jug1 < jug1Capacity) {
            nextStates.add(new JugState(jug1Capacity, currentState.jug2));
        }

        // Fill jug2
        if (currentState.jug2 < jug2Capacity) {
            nextStates.add(new JugState(currentState.jug1, jug2Capacity));
        }

        // Pour water from jug1 to jug2
        int pour1to2 = Math.min(currentState.jug1, jug2Capacity - currentState.jug2);
        nextStates.add(new JugState(currentState.jug1 - pour1to2, currentState.jug2 + pour1to2));

        // Pour water from jug2 to jug1
        int pour2to1 = Math.min(currentState.jug2, jug1Capacity - currentState.jug1);
        nextStates.add(new JugState(currentState.jug1 + pour2to1, currentState.jug2 - pour2to1));

        // Empty jug1
        nextStates.add(new JugState(0, currentState.jug2));

        // Empty jug2
        nextStates.add(new JugState(currentState.jug1, 0));

        return nextStates;
    }
}

