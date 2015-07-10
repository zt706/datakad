package com.kad.util;

import java.util.Comparator;
import java.util.Map;


public class KeyComparator implements Comparator<String> {  

Map<String, String> base;  
public KeyComparator(Map<String, String> base) {  
    this.base = base;  
}  

// Note: this comparator imposes orderings that are inconsistent with equals.      
public int compare(String a, String b) {  
    if (Integer.parseInt(a)<Integer.parseInt(b)) {  
        return -1;  
    } else {  
        return 1;  
    } // returning 0 would merge keys  
}  
}
