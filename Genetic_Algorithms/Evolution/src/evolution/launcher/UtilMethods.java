package evolution.launcher;

public class UtilMethods {
	
	public static int[] insertionSortLTH(int[] array){
		if(array.length <= 0 || array.length == 1){
			return array;
		}
		
		int lowest = array[0];
		int index = 0;
		for(int i = 0; i < array.length - 1 ; i++){
			for(int j = 0; j < array.length; j++){
				if(lowest > array[j]){
					lowest = array[j];
					index = j;
				}
				
				if(array[i] > lowest){
					int temp = array[index];
					array[index] = array[i];
					array[i] = temp;
				}
			}
			
			
			lowest = array[i+1];
		}
		
		return array;
	}
}
