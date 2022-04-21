package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.AbstractTermTuple;

/**
 * ClassName: TermTuple
 * PackageName:hust.cs.javacourse.search.index.impl
 * Description:
 * date: 2022/4/6 17:23
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class TermTuple extends AbstractTermTuple {
    public TermTuple(AbstractTerm term,int position){
        this.curPos = position;
        this.term = term;
    }
    public TermTuple(){}

    @Override
    public boolean equals(Object obj) {
        //如果地址相等，或者字段都相等，就是true
        TermTuple obj1 = (TermTuple) obj;
        if(this == obj) {return true;}
        if(this.term.equals(obj1.term) && this.curPos== obj1.curPos){return true;}
        //否则就是false
        return false;
    }

    @Override
    public String toString() {
        return term.toString()+","+freq+","+curPos;
    }
}
