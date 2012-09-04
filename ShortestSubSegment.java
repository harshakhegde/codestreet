import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShortestSubSegment {

    private static List<String> input = new ArrayList<String>();
    private static int wc;
    private static Set<String> words = new HashSet<String>();
    private static String result;
    private static int segment_wc = 200000;

    public static void main(String[] args) throws Exception {
        prepare();
        for (int i = 0; i < input.size() - wc; i++) {
            List<String> basket = new ArrayList<String>();
            for (int j = i; j < input.size(); j++) {
                basket.add(input.get(j));
                if (hasAllWords(basket)) {
                    putIfSmaller(basket);
                    break;
                }
            }
        }
        System.out.println((null == result) ? "NO SUBSEGMENT FOUND" : result);

    }

    private static void putIfSmaller(List<String> basket) {
        if (basket.size() < segment_wc) {
            segment_wc = basket.size();
            result = basket.toString().replaceAll("[^a-zA-Z0-9 ]+", "");
        }

    }

    private static boolean hasAllWords(List<String> basket) {
        for (String w : words) {
            if (!contains(basket, w)) {
                return false;
            }
        }
        return true;
    }

    private static boolean contains(List<String> basket, String sample) {
        for (String string : basket) {
            if (string.equalsIgnoreCase(sample)) {
                return true;
            }
        }
        return false;
    }

    private static void prepare() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> temp = new ArrayList<String>();
        Collections.addAll(temp, br.readLine().replaceAll("[^a-zA-Z0-9 ]+", "").split(" "));
        for (String s : temp) {
            input.add(s);
        }
        wc = Integer.parseInt(br.readLine());
        for (int i = 0; i < wc; i++) {
            words.add(br.readLine().toLowerCase());
        }
        br.close();
    }

}
