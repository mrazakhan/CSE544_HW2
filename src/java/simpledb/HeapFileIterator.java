
package simpledb;

import java.io.*;
import java.util.*;

public class HeapFileIterator implements DbFileIterator {
        
        
        private Iterator<Tuple> iter;
        private TransactionId tid;
        
        private  HeapFile f;
        private int currentPageNum; 
        
        public HeapFileIterator( HeapFile f,TransactionId tid) {
            this.tid = tid;
            this.f=f;
             
        }
            
        private List<Tuple> getTuplesFromPage(int pgNum) throws TransactionAbortedException, DbException{
            
            PageId pageId = new HeapPageId(f.getId(), pgNum);
            Page page = Database.getBufferPool().getPage(tid, pageId, Permissions.READ_ONLY);
                            
            List<Tuple> tupleList = new ArrayList<Tuple>();
            
            // get all tuples from the first page in the file
            HeapPage hp = (HeapPage)page;
            Iterator<Tuple> itr = hp.iterator();
            while(itr.hasNext()){
                tupleList.add(itr.next());
            }
            return  tupleList;
        }


        @Override
        public void open() throws DbException, TransactionAbortedException {
        	currentPageNum = 0;
            iter = getTuplesFromPage(currentPageNum).iterator();
        }

        @Override
        public boolean hasNext() throws DbException, TransactionAbortedException {
            if( iter == null){
                return false;
            }
            if(iter.hasNext()){
                return true;
            } else if (currentPageNum < f.numPages()-1){
                // if we have more pages to iterate
                if(getTuplesFromPage(currentPageNum + 1).size() != 0){
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        @Override
        public Tuple next() throws DbException, TransactionAbortedException,
                NoSuchElementException {
            //
            if(iter == null){
                throw new NoSuchElementException("tuple is null");
            }
            
            if(iter.hasNext()){
            	
                Tuple t = iter.next();
                return t;
            } else if(!(iter.hasNext()) && (currentPageNum < f.numPages()-1)) {
            	//Get tuples form next page
            	currentPageNum ++;
                iter = getTuplesFromPage(currentPageNum).iterator();
                if (iter.hasNext())
                 return iter.next();
                else {
                    throw new NoSuchElementException("No more Tuples");
                }
                
            } else {
                // no more tuples on current page and no more pages in file
                throw new NoSuchElementException("No more Tuples");
            }
            
        }
          
        
        @Override
        public void rewind() throws DbException, TransactionAbortedException {
            close();
            open();

        }

        @Override
        public void close() {
        	iter = null;

        }

    }
