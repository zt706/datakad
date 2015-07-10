package com.kad.util;

import java.util.Comparator;
import java.util.Map;


public class ValueComparator implements Comparator<String> {  

Map<String, String> base;  
public ValueComparator(Map<String, String> base) {  
    this.base = base;  
}  

// Note: this comparator imposes orderings that are inconsistent with equals.      
public int compare(String a, String b) {  
    if (Integer.parseInt(base.get(a))<Integer.parseInt(base.get(b))) {  
        return 1;  
    } else {  
        return -1;  
    } // returning 0 would merge keys  
}  
}
