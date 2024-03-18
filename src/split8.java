import java.util.*;

public class split8 extends split{
    public split8(){
        super();
    }
    public split8(String seq, String seqcor,String s) {
        super.seq=seq;
        super.seqcor=seqcor;
        super.s=s;
        lsp1=new ArrayList<>();
        lsp2=new ArrayList<>();
    }
    public static List<List<Integer>> extractAllCombinations(String input, int count) {
        List<List<Integer>> allCombinations = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();
        Set<Integer> usedIndexes = new HashSet<>();
        backtrack(input, count, 0, currentCombination, usedIndexes, allCombinations);
        return allCombinations;
    }
    private static void backtrack(String input, int count, int start, List<Integer> currentCombination, Set<Integer> usedIndexes, List<List<Integer>> allCombinations) {
        if (count == 0) {
            allCombinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = start; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                int digit = Character.getNumericValue(c);
                if (!usedIndexes.contains(i)) {
                    currentCombination.add(digit);
                    usedIndexes.add(i);
                    backtrack(input, count - 1, i + 1, currentCombination, usedIndexes, allCombinations);
                    currentCombination.remove(currentCombination.size() - 1);
                    usedIndexes.remove(i);
                }
            }
        }
    }
    private static List<String> formatCombinations(List<List<Integer>> combinations) {
        List<String> formattedCombinations = new ArrayList<>();
        for (List<Integer> combination : combinations) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < combination.size(); i++) {
                sb.append(combination.get(i));
                if (i < combination.size() - 1) {
                    sb.append("");
                }
            }
            formattedCombinations.add(sb.toString());
        }
        return formattedCombinations;
    }
    public void split(){
        List<List<Integer>> allCombinations = extractAllCombinations(s, 3);
        Set<List<Integer>> uniqueCombinations = new HashSet<>(allCombinations); // Remove duplicates
        List<List<Integer>> sortedCombinations = new ArrayList<>(uniqueCombinations);
        Collections.sort(sortedCombinations, (a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i) != b.get(i)) {
                    return a.get(i) - b.get(i);
                }
            }
            return 0;
        });
        List<String> formattedCombinations = formatCombinations(sortedCombinations);
        this.lsp1 = new ArrayList<>(formattedCombinations);
        for (String combination : lsp1) {
            lsp2.add(removeDuplicates(s,combination));
        }
    }

    public void print()
    {
        split();
        System.out.println("组1：");
        for (String s : lsp1)
        {
            System.out.print(s+" ");
        }
        System.out.println("组2：");
        for (String s : lsp2) {
            System.out.print(s + " ");
        }
    }
}
