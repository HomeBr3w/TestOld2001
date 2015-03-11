/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test.Support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        Collections.sort(results, new Comparator<MatchResult>() {
            @Override
            public int compare(MatchResult m1, MatchResult m2) {
                return (int)(m1.getConfidence() * 1000 - m2.getConfidence()* 1000);
            }

        });/*
        for (MatchResult res : results) {
            System.out.println("Name: " + res.getCompared().getName() + " Conf: " + res.getConfidence());
        }*/
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

}
