package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractTermTuple;

import java.util.List;

/**
 * ClassName: Document
 * PackageName:hust.cs.javacourse.search.index.impl
 * Description:
 * date: 2022/4/6 18:16
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class Document extends AbstractDocument {
    public Document(int docId, String docPath, List<AbstractTermTuple> tuples) {
        super(docId, docPath, tuples);
    }

    public Document(int docId, String docPath) {
        super(docId, docPath);
    }

    public Document(){}

    @Override
    public int getDocId() {
        return docId;
    }

    @Override
    public void setDocId(int docId) {
        this.docId = docId;
    }

    @Override
    public String getDocPath() {
        return docPath;
    }

    @Override
    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    @Override
    public List<AbstractTermTuple> getTuples() {
        return tuples;
    }

    @Override
    public void addTuple(AbstractTermTuple tuple) {
        if(!tuples.contains(tuple)){
            tuples.add(tuple);
        }
    }

    @Override
    public boolean contains(AbstractTermTuple tuple) {
        return tuples.indexOf(tuple) == -1? false:true;
    }

    @Override
    public AbstractTermTuple getTuple(int index) {
        return tuples.get(index);
    }

    @Override
    public int getTupleSize() {
        return tuples.size();
    }

    //先这么写上
    @Override
    public String toString() {
        return docId+","+docPath+","+tuples.toString();
    }
}
