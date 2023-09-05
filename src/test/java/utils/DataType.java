package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DataType {

    public static <T> T castingFromObjectToClassName(Object o, Class<T> ct) {
        try {
            return ct.cast(o);
        } catch(ClassCastException e) {
            System.err.println("castingFromObjectToClassName error");
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static <T> T transformType(Object o, Class<T> ct) {
        String oType = o.getClass().getSimpleName();
        // System.err.println(oType);

        try {
            switch(oType){
                case "Long" : 
                    o = Integer.parseInt(String.valueOf(o));
            }

            return ct.cast(o);
        } catch(ClassCastException e) {
            System.err.println("convertInstanceOfObject error : " + o );
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static Object[] makeReturnArr(Object retunValue, String returnType) {
        Object[] returnArr = new Object[1];
        
        switch(returnType) {
            case "String" :
                returnArr[0] = transformType(retunValue, String.class);
                break;
            case "int" :
                returnArr[0] = transformType(retunValue, Integer.class);
                break;
            case "double" :
                // returnSet.add(convertInstanceOfObject(retunValue, Double.class));
                break;
                case "long" :
                // returnSet.add(convertInstanceOfObject(retunValue, Long.class));
                break;
            default :
                returnArr[0] = null;
                break;
        }

        return returnArr;
    }
}
