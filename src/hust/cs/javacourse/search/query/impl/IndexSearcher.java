package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.impl.Index;
import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.AbstractIndexSearcher;
import hust.cs.javacourse.search.query.Sort;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * ClassName: IndexSearcher
 * PackageName:hust.cs.javacourse.search.query.impl
 * Description:
 * date: 2022/4/18 15:49
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class IndexSearcher extends AbstractIndexSearcher {
    @Override
    public void open(String indexFile) {
        index.load(new File(indexFile));
    }

    @Override
    public AbstractHit[] search(AbstractTerm queryTerm, Sort sorter) {
        //sorter的作用就是算个分，然后排序
        AbstractHit[] answer = null;
        if(!index.getDictionary().contains(queryTerm)){
            //没有这东西
            return new AbstractHit[0];
        }
        AbstractPostingList search = index.search(queryTerm);
        //长度即结果数
        answer = new AbstractHit[search.size()];
        for (int i = 0; i < search.size(); i++) {
            AbstractPosting abstractPosting = search.get(i);
            Map<AbstractTerm,AbstractPosting> termPostingMap = new TreeMap<>();
            termPostingMap.put(queryTerm,abstractPosting);
            //形成一个新的hit放到数组之中
            AbstractHit bridge = new Hit(abstractPosting.getDocId(),index.getDocName(abstractPosting.getDocId()),termPostingMap);
            //给每个文档评分并且放到数组里面
            double score = sorter.score(bridge);
            bridge.setScore(score);
            answer[i] = bridge;
        }
        List<AbstractHit> abstractHits = Arrays.asList(answer);
        sorter.sort(abstractHits);
        return ((AbstractHit[]) abstractHits.toArray());
    }

    @Override
    public AbstractHit[] search(AbstractTerm queryTerm1, AbstractTerm queryTerm2, Sort sorter, LogicalCombination combine) {
        return new AbstractHit[0];
    }
}
