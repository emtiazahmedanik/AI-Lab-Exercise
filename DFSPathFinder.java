import java.util.*;

public class DFSPathFinder {
    static int N;
    static int obs;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}; // Directions for DFS
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter grid size N: ");
        N = scanner.nextInt();
        System.out.print("Enter number of obstacle: ");
        obs = scanner.nextInt();

        grid = new int[N][N];
        visited = new boolean[N][N];
        generateGrid(obs);
        printGrid();

        System.out.print("Enter start position (row col): ");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();

        System.out.print("Enter goal position (row col): ");
        int goalX = scanner.nextInt();
        int goalY = scanner.nextInt();

        List<int[]> path = new ArrayList<>();
        if (dfs(startX, startY, goalX, goalY, path)) {
            System.out.println("Path Found!");
            for (int[] pos : path) {
                System.out.println("(" + pos[0] + ", " + pos[1] + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }

    static void generateGrid(int obs) {
    Random rand = new Random();
    int obstacleCount = obs;

    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            grid[i][j] = 0;
        }
    }

    
    for (int k = 0; k < obstacleCount; k++) {
        int i = rand.nextInt(N); 
        int j = rand.nextInt(N); 
        grid[i][j] = 1; 
    }
}

    static void printGrid() {
        System.out.println("Generated Grid:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }

    static boolean dfs(int x, int y, int goalX, int goalY, List<int[]> path) {
        if (x < 0 || y < 0 || x >= N || y >= N || grid[x][y] == 1 || visited[x][y]) {
            return false;
        }
        
        visited[x][y] = true;
        path.add(new int[]{x, y});
        
        if (x == goalX && y == goalY) {
            return true;
        }
        
        for (int i = 0; i < 4; i++) {
            if (dfs(x + dx[i], y + dy[i], goalX, goalY, path)) {
                return true;
            }
        }
        
        path.remove(path.size() - 1);
        return false;
    }
}
