package ohm.softa.a02;

import sun.java2d.pipe.SpanShapeRenderer;

import java.util.Iterator;
import java.lang.Iterable;


/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable <Object>{
    private Element head;
    private int sizeCount;

    public SimpleListImpl(){
        head=new Element();
        sizeCount=0;
    }
    public void add(Object item){
        SimpleListImpl.Element ele=new SimpleListImpl.Element();
        ele.item=item;

        SimpleIteratorImpl iterator= (SimpleIteratorImpl) iterator();
        while(iterator.hasNext()){
            iterator.next();
        }
        iterator.add(ele);
        sizeCount+=1;
    }

    public int size(){
        return sizeCount;
    }
    public SimpleList filter(SimpleFilter filter)
    {
        SimpleList list=new SimpleListImpl();

        for(Object item:this){
            if(filter.include(item)){
                list.add(item);
            }
        }
        return list;
    }

    public Iterator iterator(){
       return new SimpleIteratorImpl();
    }

    private class SimpleIteratorImpl implements Iterator<Object>{
        private SimpleListImpl.Element current; //Added

        SimpleIteratorImpl(){   //Added
            current=head;
        }

        public void add(SimpleListImpl.Element element){ //Added
            current.next=element;
        }

        public Element currentElement(){return current;}

        @Override
        public boolean hasNext() {
            if (current.next == null)
                return false;
            else return true;
            }

            @Override
        public Object next(){
            current=current.next;
            return current.item;
        }
    }

    private static class Element{
        private  Object item;
        private  Element next;
    }
}
