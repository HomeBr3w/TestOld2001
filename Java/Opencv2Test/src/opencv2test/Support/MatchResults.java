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
    private float confidence;

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

        Collections.sort(results, (MatchResult m1, MatchResult m2) -> (int) (m2.getError() * 1000 - m1.getError() * 1000));
        confidence = calculateConfidence();
        return results.get(0);
    }
    
    /**
     * Returns the confidence
     * @return float confidence
     */
    public float getConfidence()
    {
        return confidence;
    }
    
    /**
     * Calculates the confidence:
     * Formula: foreach ( MatchResult ) do sum += errorBestMatch / errorOtherResult[n]
     * @return float confidence
     */
    private float calculateConfidence()
    {
        float totalSum = 0;
        float errBestMatch = results.get(0).getError();
        for(int i = 1; i < results.size(); i++)
        {
            float diff = results.get(i).getError() - errBestMatch;
            totalSum += (diff / 100f);
        }
        return totalSum;
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
    
    public ArrayList<MatchResult> getResults()
    {
        return results;
    }
}
