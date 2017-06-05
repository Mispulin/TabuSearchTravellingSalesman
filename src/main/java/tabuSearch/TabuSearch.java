package tabuSearch;

import java.util.ArrayList;
import java.util.List;

public class TabuSearch {

    private int start = 0;
    private int[] solution;
    private int[][] distances;
    private int cost = 0;

    public TabuSearch(int[][] distances, int start) {
        this.distances = distances;
        this.start = start;
    }

    public int getObjectiveFunctionValue(int solution[]) {
        // returns the path cost
        // the first and the last cities positions do not change
        // example solution : {0, 1, 3, 4, 2, 0}
        int cost = 0;
        for (int i = 0; i < solution.length - 1; i++) {
            cost += distances[solution[i]][solution[i + 1]];
        }
        return cost;
    }

    private int[] getBestNeighbour(TabuList tabuList,
                                   int[] initSolution) {

        int[] bestSol = new int[initSolution.length]; // this is the best Solution So Far
        System.arraycopy(initSolution, 0, bestSol, 0, bestSol.length);
        int bestCost = getObjectiveFunctionValue(initSolution);
        int city1 = 0;
        int city2 = 0;
        boolean firstNeighbor = true;

        for (int i = 1; i < bestSol.length - 1; i++) {
            for (int j = 2; j < bestSol.length - 1; j++) {
                if (i == j) {
                    continue;
                }

                int[] newBestSol = new int[bestSol.length]; //this is the best Solution So Far
                System.arraycopy(bestSol, 0, newBestSol, 0, newBestSol.length);

                newBestSol = swapOperator(i, j, initSolution); // Try swapping cities i and j
                // maybe we get a better solution
                int newBestCost = getObjectiveFunctionValue(newBestSol);

                if ((newBestCost > bestCost || firstNeighbor) && tabuList.tabuList[i][j] == 0) { //if better move found, store it
                    firstNeighbor = false;
                    city1 = i;
                    city2 = j;
                    System.arraycopy(newBestSol, 0, bestSol, 0, newBestSol.length);
                    bestCost = newBestCost;
                }

            }
        }

        if (city1 != 0) {
            tabuList.decrementTabu();
            tabuList.tabuMove(city1, city2);
        }
        return bestSol;

    }

    // return list of nodes to visit
    // first and last is starting point
    private int[] getInitialSolution() {
        List<Integer> cities = new ArrayList<>();
        for (int i = 0; i < distances.length; i++) {
            cities.add(i);
        }
        int startIndex = cities.indexOf(start);
        cities.remove(startIndex);
        cities.add(start);
        cities.add(0, start);
        int[] initialSolution = new int[distances.length + 1];
        for (int i = 0; i < cities.size(); i++) {
            initialSolution[i] = cities.get(i);
        }
        return initialSolution;
    }

    // swaps two cities
    private int[] swapOperator(int city1, int city2, int[] solution) {
        int temp = solution[city1];
        solution[city1] = solution[city2];
        solution[city2] = temp;
        return solution;
    }

    public void start() {

        int[] currSolution = getInitialSolution();

        int numberOfIterations = 50;
        int tabuLength = 10;
        TabuList tabuList = new TabuList(tabuLength);

        solution = new int[currSolution.length];
        System.arraycopy(currSolution, 0, solution, 0, solution.length);
        cost = getObjectiveFunctionValue(solution);

        for (int i = 0; i < numberOfIterations; i++) {

            currSolution = getBestNeighbour(tabuList, currSolution);
            int currCost = getObjectiveFunctionValue(currSolution);
            if (currCost < cost) {
                System.arraycopy(currSolution, 0, solution, 0, solution.length);
                cost = currCost;
            }
        }
    }

    public int getCost() {
        return cost;
    }

    public int[] getSolution() {
        return solution;
    }

    private void printSolution() {
        for (int cityId : solution) {
            System.out.print(cityId + " ");
        }
        System.out.println();
        System.out.println();
    }
}
