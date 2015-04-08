/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test.Support;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Siebren
 */
public class MatchResults {

    private final ArrayList<MatchResult> results;

    /**
     * Creates a new instance of MatchResults. This class contains a list with
     * MatchResults
     */
    public MatchResults() {
        results = new ArrayList<>();
    }

    /**
     * Sorts the array (with lambda expression ;) so that the first index of the
     * results is the best match. Return this match.
     *
     * @return
     */
    public MatchResult getBestMatch() {
        if (results.isEmpty()) {
            return null;
        }

        Collections.sort(results, (MatchResult m1, MatchResult m2) -> (int) (m1.getError() * 1000 - m2.getError() * 1000));
        if (results.get(0).getError() == results.get(1).getError()) {
            System.out.println("WARNING: Multiple objects have same ERROR: " + results.get(0).getCompared().getName() + " and " + results.get(1).getCompared().getName());
        }
        return results.get(0);
    }

    /**
     * Adds a new MatchResult to the results array.
     *
     * @param matchResult
     */
    public void addResult(MatchResult matchResult) {
        results.add(matchResult);
    }

    /**
     * Clears the results list.
     */
    public void clear() {
        results.clear();
    }

    public ArrayList<MatchResult> getResults() {
        return results;
    }
}
