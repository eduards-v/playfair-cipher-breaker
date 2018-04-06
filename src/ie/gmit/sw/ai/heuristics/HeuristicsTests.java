package ie.gmit.sw.ai.heuristics;

public class HeuristicsTests {

    public static void main(String[] args) {

//        String encoded = "HEQEFIRCHITZMHUKOTXEDKWLHKHQVDSIEAKOZTXMTKOEEQSBXTDYHEUKUDBMKYZ";
//
//        HeuristicsCalculator calc = new HeuristicsCalculator();
//
//        calc.scoreDecoding(encoded);

        QuadGramsRepo repo = QuadGramsRepo.getInstance();

        repo.printMap();
    }
}
