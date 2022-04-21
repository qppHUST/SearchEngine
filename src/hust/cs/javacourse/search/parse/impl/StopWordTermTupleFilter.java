package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.StopWords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: StopWordFilter
 * PackageName:hust.cs.javacourse.search.parse.impl
 * Description:
 * date: 2022/4/18 14:19
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class StopWordTermTupleFilter extends AbstractTermTupleFilter {
    private static List<String> stopWord;
    static {
        stopWord = Arrays.asList(StopWords.STOP_WORDS);
    }

    /**
     * 构造函数
     *
     * @param input ：Filter的输入，类型为AbstractTermTupleStream
     */
    public StopWordTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }


    /**
     * 获得下一个三元组
     * 过滤掉停等词
     *
     * @return: 下一个三元组；如果到了流的末尾，返回null
     */
    @Override
    public AbstractTermTuple next() {
        AbstractTermTuple stopWordFilter = input.next();
        if (stopWordFilter == null) return null;
        while (stopWord.contains(stopWordFilter.term.getContent())) {
            stopWordFilter = input.next();
            if (stopWordFilter == null) return null;
        }
        return stopWordFilter;
    }
}
