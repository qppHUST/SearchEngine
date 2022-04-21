package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractIndexBuilder;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.FileUtil;

import java.io.File;
import java.util.List;

/**
 * ClassName: IndexBuilder
 * PackageName:hust.cs.javacourse.search.index.impl
 * Description:
 * date: 2022/4/17 16:12
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class IndexBuilder extends AbstractIndexBuilder {
    public IndexBuilder(AbstractDocumentBuilder docBuilder) {
        super(docBuilder);
    }

    @Override
    public AbstractIndex buildIndex(String rootDirectory) {
        AbstractIndex index = new Index();
        List<String> list = FileUtil.list(rootDirectory);
        for (String s : list) {
            AbstractDocument build = docBuilder.build(docId++, s, new File(s));
            index.addDocument(build);
        }
        index.save(new File(Config.INDEX_DIR+"index.dat"));
        return index;
    }
}
