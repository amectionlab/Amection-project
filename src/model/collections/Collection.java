package model.collections;

import java.util.HashMap;

abstract class Collection {
    
    protected final HashMap<String, Object> collection;
    
    //Constructor
    public Collection() {
        this.collection = new HashMap<>();
    }
    
    public boolean addToCollection(String key, Object o) {
        
        return this.collection.put(key, o) != null;
    }
    
    public boolean removeFromCollection(String key) {
        
        return this.collection.remove(key) != null;
    }
    
    public Object selectFromCollection(String key) {
        return this.collection.get(key);
    }
    
    public HashMap getCollection() {
        return this.collection;
    }
    public int getSizeCollection() {
        return this.collection.size();
    }
}
