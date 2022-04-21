package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * ClassName: PostingList
 * PackageName:hust.cs.javacourse.search.index.impl
 * Description:
 * date: 2022/4/6 17:38
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class PostingList extends AbstractPostingList {
    public PostingList(){}

    @Override
    public void add(AbstractPosting posting) {
        if(!list.contains(posting)){
            list.add(posting);
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public void add(List<AbstractPosting> postings) {
        for (AbstractPosting posting : postings) {
            if(!list.contains(posting)){
                list.add(posting);
            }
        }
    }

    @Override
    public AbstractPosting get(int index) {
        return list.get(index);
    }

    @Override
    public int indexOf(AbstractPosting posting) {
        return list.indexOf(posting);
    }

    @Override
    public int indexOf(int docId) {
        //一个postingList对应的是一个单词，给一个docId
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getDocId() == docId){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(AbstractPosting posting) {
        return list.contains(posting);
    }

    @Override
    public void remove(int index) {
        list.remove(index);
    }

    @Override
    public void remove(AbstractPosting posting) {
        list.remove(posting);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.removeAll(list);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void sort() {
        list.sort((o1,o2)->{
            if(o1.getDocId()> o2.getDocId()){
                return 1;
            }else if(o1.getDocId()<o2.getDocId()){
                return -1;
            }
            return 0;
        });
    }

    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream in) {
        try {
            PostingList postingList = (PostingList) in.readObject();
            this.list = postingList.list;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
