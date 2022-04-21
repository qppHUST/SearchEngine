package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleScanner;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.StringSplitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: TermTupleScanner
 * PackageName:hust.cs.javacourse.search.parse.impl
 * Description:
 * date: 2022/4/17 17:04
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class TermTupleScanner extends AbstractTermTupleScanner {
    public TermTupleScanner() {}

    //用于存储搜索到的list，实际上是模拟流的实现
    private List<AbstractTermTuple> list = new ArrayList<>();

    //用于记录位置
    private int position = 0;

    //对自己的分词器进行初始化
    private static StringSplitter splitter= new StringSplitter();
    static {
        splitter.setSplitRegex(Config.STRING_SPLITTER_REGEX);
    }

    public TermTupleScanner(BufferedReader input){
        super(input);//初始化input流
        String content = null;
        try {
            while ((content = input.readLine()) != null){
                //对这一行进行分词形成list
                List<String> strings = splitter.splitByRegex(content);
                for (String string : strings) {
                    //对每一个String形成一个tuple
                    list.add(new TermTuple(new Term(string),position++));//freq不用设置，对于每次找到的单词，其次数都是1
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AbstractTermTuple next(){
        if(this.list.size() != 0){
            return list.remove(0);
        }
        return null;
    }
}
