package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTerm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * ClassName: Term
 * PackageName:hust.cs.javacourse.search.index.impl
 * Description:
 * date: 2022/4/6 17:10
 *
 * @author: 邱攀攀
 * @version:
 * @since JDK 1.8
 */
public class Term extends AbstractTerm {
    public Term(String content){
        this.content = content;
    }
    public Term(){}

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {return true;}
        if(this.content == ((Term) obj).content) {
            return true;
        }
        if(this.content.equals(((Term) obj).content)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(AbstractTerm o) {
        if(this.getContent().compareTo(o.getContent())>0){
            return 4;
        }else if(this.getContent().compareTo(o.getContent())<0){
            return -1;
        }
        return 0;
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
            //读取并将值给自己
            Object o = in.readObject();
            content = ((Term) o).content;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
