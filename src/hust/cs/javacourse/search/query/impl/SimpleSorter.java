package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.impl.Posting;
import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.Sort;

import java.util.*;

/**
 * ClassName: SimpleSorter
 * PackageName:hust.cs.javacourse.search.query.impl
 * Description:
 * date: 2022/4/18 16:04
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class SimpleSorter implements Sort {
    public SimpleSorter() {}

    @Override
    public void sort(List<AbstractHit> hits) {
        hits.sort(new Comparator<AbstractHit>() {
            @Override
            public int compare(AbstractHit o1, AbstractHit o2) {
                if(o1.getScore()>o2.getScore()){
                    return 1;
                }else if(o1.getScore()<o2.getScore()){
                    return -1;
                }
                return 0;
            }
        });
    }

    @Override
    public double score(AbstractHit hit) {
        Map<AbstractTerm, AbstractPosting> termPostingMapping = hit.getTermPostingMapping();
        AbstractPosting abstractPosting = termPostingMapping.get(termPostingMapping.keySet().iterator().next());
        return abstractPosting.getFreq();
    }
}
