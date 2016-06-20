package uva;

import java.text.DecimalFormat;

public class Challenge100 {

    public static void main(String[] args) {
        new TestSuite().run();
    }

    static class Solver {

        public String solve(int i, int j) {
            int smaller = Math.min(i, j);
            int larger = Math.max(i, j);

            int max = Integer.MIN_VALUE;
            for (int x = smaller; x <= larger; x++) {
                max = Math.max(max, getCycleLength(x));
            }
            return output(i, j, max);
        }

        private String output(int i, int j, int max) {
            StringBuilder builder = new StringBuilder();
            builder.append(i).append(" ").append(j).append(" ").append(max);
            return builder.toString();
        }

        private int getCycleLength(int n) {
            int count = 0;
            while (n != 1) {
                count++;
                if (isOdd(n)) {
                    n = 3*n + 1;
                } else {
                    n  = n >> 1; // divide by two
                }
            }
            count++; // count n == 1
            return count;
        }

        private boolean isOdd(int x) { return x % 2 == 1; }
    }

    static class TestSuite {
        private Solver solver;
        private int passed, total;

        public TestSuite() {
            this.solver = new Solver();
            this.passed = 0;
            this.total = 0;
        }

        private void pass() {
            System.out.println("* PASS");
            passed++;
            total++;
        }

        private void fail() {
            System.out.println("X FAIL");
            total++;
        }

        private void stats() {
            double accuracy = (((double) passed) / total) * 100;

            StringBuilder builder = new StringBuilder();
            DecimalFormat df = new DecimalFormat("0.00##");

            builder.append("# PASSED = ").append(passed).append("\t");
            builder.append("# FAILED = ").append(total - passed).append("\t");
            builder.append("% ACCURACY = ").append(df.format(accuracy));

            System.out.println("----------");
            System.out.println(builder.toString());
        }

        public void run() {
            if (solver.solve(2, 4).equals("2 4 8"))  { pass(); } else { fail(); }
            if (solver.solve(4, 4).equals("4 4 3"))  { pass(); } else { fail(); }
            if (solver.solve(1, 10).equals("1 10 20"))  { pass(); } else { fail(); }
            if (solver.solve(100, 200).equals("100 200 125"))  { pass(); } else { fail(); }
            if (solver.solve(201, 210).equals("201 210 89"))  { pass(); } else { fail(); }
            if (solver.solve(900, 1000).equals("900 1000 174"))  { pass(); } else { fail(); }
            if (solver.solve(1000, 900).equals("1000 900 174"))  { pass(); } else { fail(); }
            if (solver.solve(999999, 999990).equals("999999 999990 259"))  { pass(); } else { fail(); }
            stats();
        }
    }
}
