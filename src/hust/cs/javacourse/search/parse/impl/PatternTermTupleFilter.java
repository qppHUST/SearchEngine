package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

/**
 * ClassName: PatternFilter
 * PackageName:hust.cs.javacourse.search.parse.impl
 * Description:
 * date: 2022/4/18 14:18
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class PatternTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * 构造函数
     *
     * @param input ：Filter的输入，类型为AbstractTermTupleStream
     */
    public PatternTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    @Override
    public AbstractTermTuple next() {
        AbstractTermTuple tuple = input.next();
        if (tuple == null)
            return null;
        while (!tuple.term.getContent().matches(Config.TERM_FILTER_PATTERN)) {
            tuple = input.next();
            if (tuple == null)
                return null;
        }
        return tuple;
    }
}
