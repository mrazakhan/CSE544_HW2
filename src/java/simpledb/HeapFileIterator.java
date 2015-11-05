package simpledb;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class HeapFileIterator implements DbFileIterator {

	private Iterator<Tuple> it;
	private HeapFile heapFile;
	private TransactionId tid;
	private int PageNum=0;
	public HeapFileIterator(HeapFile heapFile, TransactionId tid)
	{
		this.heapFile=heapFile;
		this.tid=tid;
	}
	@Override
	public void open() throws DbException, TransactionAbortedException {
		// TODO Auto-generated method stub
		PageNum=0;
		it=getTuplesFromPage(PageNum).iterator();
	}

	private List<Tuple> getTuplesFromPage(int pageNo) throws TransactionAbortedException, DbException
	{
		HeapPageId hpId = new HeapPageId(heapFile.getId(), pageNo);
		HeapPage page;
			page = (HeapPage) Database.getBufferPool().getPage(tid, 
					hpId, Permissions.READ_ONLY);
			return Arrays.asList(page.tuples);
		
		
	}
	@Override
	public boolean hasNext() throws DbException, TransactionAbortedException {
		// TODO Auto-generated method stub
		if (it==null)
			return false;
		else if (it.hasNext()){
			return true;
		}
		else if (PageNum<(heapFile.numPages()-1)){
			if(getTuplesFromPage(PageNum+ 1).size() != 0){
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	@Override
	public Tuple next() throws DbException, TransactionAbortedException, NoSuchElementException {
		// TODO Auto-generated method stub
		if(it == null){
            throw new NoSuchElementException("tuple is null");
        }
        
        if(it.hasNext()){
   
            Tuple t = it.next();
            return t;
        } else if(!it.hasNext() && (PageNum < heapFile.numPages()-1)) {
        	//Move to next page
            PageNum ++;
            it = getTuplesFromPage(PageNum).iterator();
            if (it.hasNext())
             return it.next();
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
		// TODO Auto-generated method stub
		close();
		open();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		it=null;
	}

}
