package org.rooney.james;

import static java.util.Comparator.comparing;

import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * Created by jamesvrooney on 08/09/15.
 */
public class Journal {
    protected enum TYPE { REVIEW, NON_REVIEW };

    private String name;
    private double score;
    private int rank;
    private TYPE journalType;

    public Journal(String name, double score, TYPE journalType) {
        this.name = name;
        this.score = score;
        this.journalType = journalType;
    }


    public static List<Journal> sortJournalsByScoreDescendingAndName(List<Journal> unordered){

        List<Journal> ordered = unordered.stream()
                                            .sorted(comparing(Journal::getScore).reversed().
                                                    thenComparing(Journal::getName))
                                            .collect(toList());

        return rankJournals(ordered);
    }

    public static List<Journal> filterByType(List<Journal> ordered, TYPE type){

        List<Journal> filtered = ordered.stream()
                                        .filter(journal -> journal.getJournalType().equals(type))
                                        .collect(toList());

        return rankJournals(filtered);
    }

    private static List<Journal> rankJournals(List<Journal> ordered){

        for (int i = 0; i < ordered.size(); i++){
            if ( i == 0 ) {
                ordered.get(i).setRank(i + 1);
            } else {
                if (ordered.get(i).getScore() == ordered.get(i - 1).getScore()){
                    ordered.get(i).setRank(ordered.get(i - 1).getRank());
                } else {
                    ordered.get(i).setRank(i + 1);
                }
            }
        }

        return ordered;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public TYPE getJournalType() {
        return journalType;
    }
}
