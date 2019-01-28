package testML;

public class Mainclass {
	public static void main(String[] args) {
		int[] i= {-1,}; 
		System.out.println(max_absolute(i));
	}
	 public static int max_absolute(int[] numbers){ 
		 int max_value = 0;
		 if(numbers.length > 5) 
   return max_value=-1;
  
  for(int i = 0; i<numbers.length; i++){ 
   if (numbers[i] < 0 )
    max_value = Math. max (max_value,Math. abs (numbers[i])); 
	//else max_value = Math. max (max_value, numbers[i]); 
  else return max_value=-1;
  } 
  System.out.println(max_value);
  return max_value;

	}
	}
