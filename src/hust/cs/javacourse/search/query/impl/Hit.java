package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.query.AbstractHit;

import java.util.Map;

/**
 * ClassName: Hit
 * PackageName:hust.cs.javacourse.search.query.impl
 * Description:
 * date: 2022/4/18 15:49
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class Hit extends AbstractHit {
    @Override
    public int getDocId() {
        return docId;
    }

    @Override
    public String getDocPath() {
        return docPath;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public double getScore() {
        return score;
    }

    @Override
    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public Map<AbstractTerm, AbstractPosting> getTermPostingMapping() {
        return termPostingMapping;
    }

    @Override
    public String toString() {
        return docId+","+docPath+","+content+","+termPostingMapping;
    }

    @Override
    public int compareTo(AbstractHit o) {
        if(score>o.getScore()){
            return 1;
        }else if (score<o.getScore()){
            return -1;
        }
        return 0;
    }

    public Hit(int docId, String docPath) {
        super(docId, docPath);
    }

    public Hit(int docId, String docPath, Map<AbstractTerm, AbstractPosting> termPostingMapping) {
        super(docId, docPath, termPostingMapping);
    }

    public Hit(){}
}
