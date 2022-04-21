package hust.cs.javacourse.search.run;

import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.AbstractIndexSearcher;
import hust.cs.javacourse.search.query.Sort;
import hust.cs.javacourse.search.query.impl.IndexSearcher;
import hust.cs.javacourse.search.query.impl.SimpleSorter;
import hust.cs.javacourse.search.util.Config;

import javax.swing.plaf.nimbus.AbstractRegionPainter;

/**
 * 测试搜索
 */
public class TestSearchIndex {
    /**
     *  搜索程序入口
     * @param args ：命令行参数
     */
    public static void main(String[] args){
        AbstractIndexSearcher search = new IndexSearcher();
        search.open("D:\\大二\\JAVA\\实验\\Java新实验一\\SearchEngineForStudent\\index\\index.dat");
        AbstractHit[] almosts = search.search(new Term("eyes"), new SimpleSorter());
        System.out.println(almosts.length);
        for (AbstractHit almost : almosts) {
            System.out.println(almost.getContent());
            System.out.println(almost.getScore());
            System.out.println(almost.getDocId());
            System.out.println(almost.getDocPath());
        }
    }
}
