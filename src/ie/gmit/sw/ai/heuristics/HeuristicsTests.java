package ie.gmit.sw.ai.heuristics;

public class HeuristicsTests {

    public static void main(String[] args) {

        String encoded = "HEQEFIRCHITZMHUKOTXEDKWLHKHQVDSIEAKOZTXMTKOEEQSBXTDYHEUKUDBMKYZ";

        HeuristicsCalculator calc = new HeuristicsCalculator();

        System.out.println(calc.scoreDecoding(encoded));

    }
}
