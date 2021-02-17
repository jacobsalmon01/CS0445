


public class Assig2B {
    public static void main(String[] args){
        MyStringBuilder msb = new MyStringBuilder(" ");
        long time = System.nanoTime();
        //System.out.println("Time: " + time);
        int size = Integer.parseInt(args[0]);
        for (int i = 0; i < size; i++){
            msb.append('a');
        }
        long elapsedTime = System.nanoTime()-time;
        System.out.println("Elapsed time for MyStringBuilder's append = " + elapsedTime);
        long charTime = elapsedTime / size;
        System.out.println("Time per character for MyStringBuilder's append = " + charTime);
        
        time = System.nanoTime();
        for (int i = 0; i < size; i++){
            msb.delete(0,1);
        }
        elapsedTime = System.nanoTime()-time;
        System.out.println("Elapsed time for MyStringBuilder's delete = " + elapsedTime);
        charTime = elapsedTime / size;
        System.out.println("Time per character for MyStringBuilder's delete = " + charTime);
        
        time = System.nanoTime();
        for (int i = 0; i < size; i++){
            
            if (i % 2 == 1){
                msb.insert((i-1)/2, "a");
            }
            else{
                msb.insert((i/2), "a");
            
            }
        }

        elapsedTime = System.nanoTime()-time;
        System.out.println("Elapsed time for MyStringBuilder's insert = " + elapsedTime);
        charTime = elapsedTime / size;
        System.out.println("Time per character for MyStringBuilder's insert = " + charTime);
        
        StringBuilder sb = new StringBuilder();
        time = System.nanoTime();
        for (int i = 0; i < size; i++){
            sb.append('a');
        }
        elapsedTime = System.nanoTime()-time;
        System.out.println("Elapsed time for StringBuilder's append = " + elapsedTime);
        charTime = elapsedTime / size;
        System.out.println("Time per character for StringBuilder's append = " + charTime);
        
        time = System.nanoTime();
        for (int i = 0; i < size; i++){
            sb.delete(0,1);
        }
        elapsedTime = System.nanoTime()-time;
        System.out.println("Elapsed time for StringBuilder's delete = " + elapsedTime);
        charTime = elapsedTime / size;
        System.out.println("Time per character for StringBuilder's delete = " + charTime);
        
        time = System.nanoTime();
        for (int i = 0; i < size; i++){
            if (i % 2 != 0){
                sb.insert((i-1)/2, 'a');
            }
            else {
                sb.insert((i), 'a');
            }
        }
        elapsedTime = System.nanoTime()-time;
        System.out.println("Elapsed time for StringBuilder's insert = " + elapsedTime);
        charTime = elapsedTime / size;
        System.out.println("Time per character for StringBuilder's insert = " + charTime);

        String s = new String();
        time = System.nanoTime();
        for (int i = 0; i < size; i++){
            s = s + "a";

        }
        elapsedTime = System.nanoTime()-time;
        System.out.println("Elapsed time for String's append = " + elapsedTime);
        charTime = elapsedTime / size;
        System.out.println("Time per character for String's append = " + charTime);

        time = System.nanoTime();
        for (int i = 0; i < size; i++){
                s = s.substring(1);
        }
        elapsedTime = System.nanoTime()-time;
        System.out.println("Elapsed time for String's delete = " + elapsedTime);
        charTime = elapsedTime / size;
        System.out.println("Time per character for String's delete = " + charTime);

        time = System.nanoTime();
        for (int i = 0; i < size; i++){
            s = s.substring(0, (s.length()/2)) + "a" + s.substring(s.length()/2);
        }
        elapsedTime = System.nanoTime()-time;
        System.out.println("Elapsed time for String's insert = " + elapsedTime);
        charTime = elapsedTime / size;
        System.out.println("Time per character for String's insert = " + charTime);


        
        
    }
    
}
