package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.*;

import java.io.*;
import java.util.*;

/**
 * AbstractIndex的具体实现类
 */
public class Index extends AbstractIndex{
    public Index() {}

    /**
     * 返回索引的字符串表示
     *
     * @return 索引的字符串表示
     */
    @Override
    public String toString() {
        //先这么写上
        return docIdToDocPathMapping+","+termToPostingListMapping;
    }

    /**
     * 添加文档到索引，更新索引内部的HashMap
     *
     * @param document ：文档的AbstractDocument子类型表示
     */
    @Override
    public void addDocument(AbstractDocument document) {
        //protected  Map<AbstractTerm, AbstractPostingList> termToPostingListMapping = new TreeMap<AbstractTerm, AbstractPostingList>();
        //先更新编号到名字的映射
        docIdToDocPathMapping.put(document.getDocId(),document.getDocPath());
        //以一个文件为基准进行,此时文件里有用的东西就剩下tuples了
        List<AbstractTermTuple> tuples = document.getTuples();
        //很多的tuple形成posting,将tuples中有用的东西提取出来,形成map
        Map<AbstractTerm,AbstractPosting> data = new TreeMap<>();
        for (AbstractTermTuple tuple : tuples) {
            if(!data.containsKey(tuple.term)){
                Posting posting = new Posting();
                ArrayList<Integer> positions = new ArrayList<>();
                positions.add(tuple.curPos);
                //形成新的posting
                posting.setFreq(1);
                posting.setDocId(document.getDocId());
                posting.setPositions(positions);
                data.put(tuple.term,posting);
            }else {
                AbstractPosting abstractPosting = data.get(tuple.term);
                //需要修改的是出现次数以及出现的位置
                abstractPosting.setFreq(abstractPosting.getFreq()+1);
                abstractPosting.getPositions().add(tuple.curPos);
            }
        }
        //此时完成了data的初始化
        data.forEach((key,value)->{
            if(!termToPostingListMapping.containsKey(key)){
                PostingList postingList = new PostingList();
                postingList.add(value);
                termToPostingListMapping.put(key,postingList);
            }else {
                //已有这个key
                AbstractPostingList abstractPostingList = termToPostingListMapping.get(key);
                abstractPostingList.add(value);
                termToPostingListMapping.put(key,abstractPostingList);
            }
        });
    }

    /**
     * <pre>
     * 从索引文件里加载已经构建好的索引.内部调用FileSerializable接口方法readObject即可
     * @param file ：索引文件
     * </pre>
     */
    @Override
    public void load(File file) {
        try {
            readObject(new ObjectInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     * 将在内存里构建好的索引写入到文件. 内部调用FileSerializable接口方法writeObject即可
     * @param file ：写入的目标索引文件
     * </pre>
     */
    @Override
    public void save(File file) {
        try {
            writeObject(new ObjectOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回指定单词的PostingList
     *
     * @param term : 指定的单词
     * @return ：指定单词的PostingList;如果索引字典没有该单词，则返回null
     */
    @Override
    public AbstractPostingList search(AbstractTerm term) {
        return termToPostingListMapping.get(term);
    }

    /**
     * 返回索引的字典.字典为索引里所有单词的并集
     *
     * @return ：索引中Term列表
     */
    @Override
    public Set<AbstractTerm> getDictionary() {
        return termToPostingListMapping.keySet();
    }

    /**
     * <pre>
     * 对索引进行优化，包括：
     *      对索引里每个单词的PostingList按docId从小到大排序
     *      同时对每个Posting里的positions从小到大排序
     * 在内存中把索引构建完后执行该方法
     * </pre>
     */
    @Override
    public void optimize() {
        Collection<AbstractPostingList> values = termToPostingListMapping.values();
        for (AbstractPostingList value : values) {
            value.sort();
            for (int i = 0; i < value.size(); i++) {
                AbstractPosting abstractPosting = value.get(i);
                abstractPosting.sort();
            }
        }
    }

    /**
     * 根据docId获得对应文档的完全路径名
     *
     * @param docId ：文档id
     * @return : 对应文档的完全路径名
     */
    @Override
    public String getDocName(int docId) {
        return docIdToDocPathMapping.get(docId);
    }

    /**
     * 写到二进制文件
     *
     * @param out :输出流对象
     */
    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            out.writeObject(termToPostingListMapping);
            out.writeObject(docIdToDocPathMapping);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从二进制文件读
     *
     * @param in ：输入流对象
     */
    @Override
    public void readObject(ObjectInputStream in) {
        try {
            termToPostingListMapping = ((Map<AbstractTerm, AbstractPostingList>) in.readObject());
            docIdToDocPathMapping = ((Map<Integer, String>) in.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getDocPath(int docId){
        return docIdToDocPathMapping.get(docId);
    }
}
