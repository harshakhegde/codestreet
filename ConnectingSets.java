import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConnectingSets {

    static List<Integer> counts = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int dimension = 0;

        String line = "";
        boolean[][] m = null;
        int i = 0;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            if (i>=dimension) {
                if (null != m)
                    findSolution(m);
                dimension = Integer.parseInt(line);
                i = 0;
                m = new boolean[dimension][dimension];
                continue;
            }
            String[] split = line.split(" ");
            for (int j = 0; j < split.length; j++) {
                m[i][j] = (Integer.parseInt(split[j]) == 0) ? false : true;
            }
            i++;
        }
        if (null != m)
            findSolution(m);
        br.close();
        for (Integer c : counts) {
            System.out.println(c);
        }
    }

    private static void findSolution(boolean[][] m) {
        Set<String> ones = new HashSet<String>();
        int dimension = m[0].length;
        int count = 0;
        for (int ii = 0; ii < dimension; ii++) {
            for (int j = 0; j < dimension; j++) {
                if (m[ii][j]) {
                    ones.add(ii + "," + j);
                }
            }
        }

        for (int ii = 0; ii < dimension; ii++) {
            for (int j = 0; j < dimension; j++) {
                if (m[ii][j] && ones.contains(ii + "," + j)) {
                    count++;
                    isNeighbourOne(ii, j, ones, dimension);
                }
            }
        }
        counts.add(count);

    }

    private static void isNeighbourOne(int i, int j, Set<String> ones, int dimension) {
        if (i >= dimension || j >= dimension) {
            return;
        }
        String[] neighours = { (i - 1) + "," + (j - 1), (i - 1) + "," + (j), (i - 1) + "," + (j + 1), (i) + "," + (j - 1),
                i + "," + (j + 1), (i + 1) + "," + (j - 1), (i + 1) + "," + (j), (i + 1) + "," + (j + 1), };
        for (String neighour : neighours) {
            if (ones.contains(neighour)) {
                String[] split = neighour.split(",");
                int ni = Integer.parseInt(split[0]);
                int nj = Integer.parseInt(split[1]);
                ones.remove(neighour);
                isNeighbourOne(ni, nj, ones, dimension);
            }
        }
        return;
    }

}
