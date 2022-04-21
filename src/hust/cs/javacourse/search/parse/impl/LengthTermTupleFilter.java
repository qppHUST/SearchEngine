package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

/**
 * ClassName: LengthFiter
 * PackageName:hust.cs.javacourse.search.parse.impl
 * Description:
 * date: 2022/4/18 14:14
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class LengthTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * 构造函数
     *
     * @param input ：Filter的输入，类型为AbstractTermTupleStream
     */
    public LengthTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    @Override
    public AbstractTermTuple next() {
        AbstractTermTuple tuple = input.next();
        if (tuple==null) return null;
        while (tuple.term.getContent().length() > Config.TERM_FILTER_MAXLENGTH || tuple.term.getContent().length()<Config.TERM_FILTER_MINLENGTH){
            tuple = input.next();
            if (tuple==null) return null;
        }
        //直到找到一个合适的
        return tuple;
    }
}
