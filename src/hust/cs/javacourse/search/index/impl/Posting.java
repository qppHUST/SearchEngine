package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.List;

/**
 * ClassName: Posting
 * PackageName:hust.cs.javacourse.search.index.impl
 * Description:
 * date: 2022/4/6 17:27
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class Posting extends AbstractPosting {
    public Posting(int docId, int freq, List<Integer> positions) {
        super(docId, freq, positions);
    }
    public Posting(){}

    @Override
    public boolean equals(Object obj) {
        Posting posting = (Posting) obj;
        boolean flag = true;
        List<Integer> _positions = posting.getPositions();
        if(_positions.size() != positions.size()) return false;
        for (Integer position : _positions) {
            if(!positions.contains(position)){
                flag = false;
            }
        }
        if(obj == this) return true;
        if(posting.getDocId() == this.docId && posting.getFreq() == this.freq && flag) return true;
        return false;
    }

    @Override
    public String toString() {
        return docId+","+freq+","+positions.toString();
    }

    @Override
    public int getDocId() {
        return docId;
    }

    @Override
    public void setDocId(int docId) {
        this.docId = docId;
    }

    @Override
    public int getFreq() {
        return freq;
    }

    @Override
    public void setFreq(int freq) {
        this.freq = freq;
    }

    @Override
    public List<Integer> getPositions() {
        return positions;
    }

    @Override
    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    @Override
    public int compareTo(AbstractPosting o) {
        if(docId>o.getDocId()) {return 1;}
        if(docId<o.getDocId()){
            return -1;
        }
        return 0;
    }

    @Override
    public void sort() {
        //对内部的positions进行排序
        positions.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2){
                    return 1;
                }else if(o1<o2){
                    return -1;
                }
                return 0;
            }
        });
    }

    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            out.writeObject(this.docId);
            out.writeObject(this.freq);
            out.writeObject(this.positions);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream in) {
        try {
            this.docId = (int) (in.readObject());
            this.freq = (int) (in.readObject());
            this.positions = (List<Integer>) (in.readObject());
        } catch (ClassNotFoundException | IOException err) {
            err.printStackTrace();
        }
    }
}
