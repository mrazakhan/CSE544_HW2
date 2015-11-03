package simpledb;

import java.io.Serializable;

/**
 * A RecordId is a reference to a specific tuple on a specific page of a
 * specific table.
 */
public class RecordId implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new RecordId referring to the specified PageId and tuple
     * number.
     * 
     * @param pid
     *            the pageid of the page on which the tuple resides
     * @param tupleno
     *            the tuple number within the page.
     */
    private PageId pid;
    private int tupleno;
    
    public RecordId(PageId pid, int tupleno) {
        // some code goes here
    	this.pid=pid;
    	this.tupleno=tupleno;
    }

    /**
     * @return the tuple number this RecordId references.
     */
    public int tupleno() {
        // some code goes here
        return this.tupleno;
    }

    /**
     * @return the page id this RecordId references.
     */
    public PageId getPageId() {
        // some code goes here
        return this.pid;
    }

    /**
     * Two RecordId objects are considered equal if they represent the same
     * tuple.
     * 
     * @return True if this and o represent the same tuple
     */
    @Override
    public boolean equals(Object o) {
        // some code goes here
        boolean ret=false;
        if (o instanceof RecordId){
        	RecordId passedRecord=(RecordId)o;
        	if ((this.pid==passedRecord.pid)&&(this.tupleno==passedRecord.tupleno))
        		ret=true;
        	else
        		ret=false;
        }
        
        return ret;
    }

    /**
     * You should implement the hashCode() so that two equal RecordId instances
     * (with respect to equals()) have the same hashCode().
     * 
     * @return An int that is the same for equal RecordId objects.
     */
    @Override
    public int hashCode() {
        // some code goes here
        int pid_hash = this.pid.hashCode();
        int tupleno_hash = (""+this.tupleno).hashCode();
        
        return pid_hash+tupleno_hash;
    }

}
