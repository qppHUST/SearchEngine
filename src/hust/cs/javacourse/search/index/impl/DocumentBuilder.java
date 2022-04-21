package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.parse.impl.LengthTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.PatternTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.StopWordTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.TermTupleScanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * ClassName: DocumentBuilder
 * PackageName:hust.cs.javacourse.search.index.impl
 * Description:
 * date: 2022/4/17 16:11
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class DocumentBuilder extends AbstractDocumentBuilder {
    @Override
    public AbstractDocument build(int docId, String docPath, AbstractTermTupleStream termTupleStream) {
        AbstractDocument document = new Document(docId,docPath);
        AbstractTermTuple next = termTupleStream.next();
        while (next != null){
            document.addTuple(next);
            next = termTupleStream.next();
        }
        termTupleStream.close();
        return document;
    }

    @Override
    public AbstractDocument build(int docId, String docPath, File file) {
        //利用file制作stream,只会调用这个方法
        AbstractTermTupleStream stream = null;
        try {
            stream = new PatternTermTupleFilter(new StopWordTermTupleFilter(new LengthTermTupleFilter(new TermTupleScanner(new BufferedReader(new FileReader(file))))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this.build(docId, docPath, stream);
    }
}
