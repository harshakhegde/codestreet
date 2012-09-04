import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class MeetingScheduler {
    private static List<Calendar> startTimes;
    private static List<Calendar> endTimes;

    static int k;
    static int m = 1;

    public static void main(String[] args) throws Exception {

        startTimes = new ArrayList<Calendar>();
        endTimes = new ArrayList<Calendar>();
        readInput();
        if (startTimes.isEmpty() || endTimes.isEmpty()) {
            return;
        }
        Calendar st = cal(0, 0, false);
        Calendar nxtDay = cal(0, 0, true);
        Collections.sort(startTimes);
        SimpleDateFormat formt = new SimpleDateFormat("HH mm");
        Collections.sort(endTimes);
        for (int i = 0; i <= m; i++) {
            Calendar et = (i < m) ? startTimes.get(i) : nxtDay;

            int slot = (int) ((et.getTimeInMillis() - st.getTimeInMillis()) / 1000);
            if (slot >= k) {
                System.out.println(formt.format(st.getTime()) + " " + formt.format(et.getTime()));
            }
            st = (i < m) ? endTimes.get(i) : null;
        }

    }

    private static Calendar cal(int hr, int min, boolean isEndTime) {
        if (isEndTime && hr == 0 && min == 0)
            return new GregorianCalendar(1900, 1, 2, hr, min);
        else
            return new GregorianCalendar(1900, 1, 1, hr, min);
    }

    private static void readInput() throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = "";
            int i = 0;
            while ((line = br.readLine()) != null && !line.isEmpty() && i < m) {
                String[] split = line.split(" ");
                if (split.length == 2) {
                    m = Integer.parseInt(split[0]);
                    k = Integer.parseInt(split[1]) * 60;
                    continue;
                }
                Calendar st = cal(Integer.parseInt(split[0]), Integer.parseInt(split[1]), false);
                Calendar et = cal(Integer.parseInt(split[2]), Integer.parseInt(split[3]), true);
                if (zero(st, et)) {
                    System.exit(1);
                }
                startTimes.add(st);
                endTimes.add(et);
                i++;
            }
            br.close();
        } catch (Exception e) {
            return;
        }
    }

    private static boolean zero(Calendar st, Calendar et) {
        if (st.get(HOUR_OF_DAY) == 0 && st.get(MINUTE) == 0) {
            if (et.get(HOUR_OF_DAY) == 0 && et.get(MINUTE) == 0) {
                return true;
            }
        }
        return false;
    }

}
