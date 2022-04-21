package hust.cs.javacourse.search.run;

import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractIndexBuilder;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.impl.DocumentBuilder;
import hust.cs.javacourse.search.index.impl.Index;
import hust.cs.javacourse.search.index.impl.IndexBuilder;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.FileUtil;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * 测试索引构建
 */
public class TestBuildIndex {
    /**
     *  索引构建程序入口
     * @param args : 命令行参数
     */
    public static void main(String[] args){
        IndexBuilder builder = new IndexBuilder(new DocumentBuilder());
        AbstractIndex index = builder.buildIndex("D:\\大二\\JAVA\\实验\\Java新实验一\\SearchEngineForStudent\\text");
        Set<AbstractTerm> dictionary = index.getDictionary();
        for (AbstractTerm abstractTerm : dictionary) {
            System.out.println(abstractTerm.getContent());
        }
    }
}
