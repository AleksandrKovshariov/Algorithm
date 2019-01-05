package tools;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Convert {
    public static Integer[] primitiveArr(int[] arr){
        return Arrays.stream(arr).boxed().toArray( Integer[]::new );
    }
    public static Character[] toCharacter(int[] arr){
        Character[] characters = new Character[arr.length];
        for (int i = 0; i < arr.length; i++)
            characters[i] = (char) (arr[i] + '0');
        return characters;
    }
}
