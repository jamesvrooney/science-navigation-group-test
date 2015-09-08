package org.rooney.james;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jamesvrooney on 08/09/15.
 */
public class JournalTest {

    @Test
    public void testRankJournalInOrder(){
        Journal A = new Journal("A", 5.6, Journal.TYPE.NON_REVIEW);
        Journal B = new Journal("B", 2.4, Journal.TYPE.NON_REVIEW);
        Journal C = new Journal("C", 3.1, Journal.TYPE.NON_REVIEW);

        List<Journal> unordered = Arrays.asList(B, C, A);

        List<Journal> ordered = Journal.sortJournalsByScoreDescendingAndName(unordered);

        assertEquals(A, ordered.get(0));
        assertEquals(C, ordered.get(1));
        assertEquals(B, ordered.get(2));

        // Check they are ranked correctly
        assertEquals(1, ordered.get(0).getRank());
        assertEquals(2, ordered.get(1).getRank());
        assertEquals(3, ordered.get(2).getRank());
    }

    @Test
    public void testRankJournalWithSharedRank(){
        Journal A = new Journal("A", 2.2, Journal.TYPE.NON_REVIEW);
        Journal B = new Journal("B", 6.2, Journal.TYPE.NON_REVIEW);
        Journal C = new Journal("C", 6.2, Journal.TYPE.NON_REVIEW);

        List<Journal> unordered = Arrays.asList(A, C, B);

        List<Journal> ordered = Journal.sortJournalsByScoreDescendingAndName(unordered);

        assertEquals(B, ordered.get(0));
        assertEquals(C, ordered.get(1));
        assertEquals(A, ordered.get(2));

        // Check they are ranked correctly
        assertEquals(1, ordered.get(0).getRank());
        assertEquals(1, ordered.get(1).getRank());
        assertEquals(3, ordered.get(2).getRank());
    }

    @Test
    public void testRankJournalsExcludingReviewJournals(){
        Journal A = new Journal("A", 5.6, Journal.TYPE.REVIEW);
        Journal B = new Journal("B", 2.4, Journal.TYPE.NON_REVIEW);
        Journal C = new Journal("C", 3.1, Journal.TYPE.NON_REVIEW);

        List<Journal> unordered = Arrays.asList(A, C, B);

        List<Journal> unorderedFilteredByType = Journal.filterByType(unordered, Journal.TYPE.NON_REVIEW);
        List<Journal> orderedAndFiltered = Journal.sortJournalsByScoreDescendingAndName(unorderedFilteredByType);

        assertEquals(C, orderedAndFiltered.get(0));
        assertEquals(B, orderedAndFiltered.get(1));

        // Check they are ranked correctly
        assertEquals(1, orderedAndFiltered.get(0).getRank());
        assertEquals(2, orderedAndFiltered.get(1).getRank());
    }
}
