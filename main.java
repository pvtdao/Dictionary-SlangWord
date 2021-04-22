import java.util.*;
import java.io.*;

public class main {
    private static Scanner sc = new Scanner(System.in);

    // Create a hashmap includes key: String and value: List<String>
    public static HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

    public static void Menu(){
        String redo = null;
        int option;

        do{
            System.out.println("");
            System.out.println("=====================MENU========================");
            System.out.println("|          1. Search by word                    |");
            System.out.println("|          2. Search by definition              |");
            System.out.println("|          3. History                           |");
            System.out.println("|          4. Add new slang-word                |");
            System.out.println("|          5. Edit slang-word                   |");
            System.out.println("|          6. Delete slang-word                 |");
            System.out.println("|          7. Reset orginal Slang list          |");
            System.out.println("|          8. Random slang-word                 |");
            System.out.println("|          9. Game 1                            |");
            System.out.println("|          10.Game 2                            |");
            System.out.println("================================================");
            System.out.print("Choose one of the options: ");

            boolean check = false;

            while(!check){
                try{
                    option = sc.nextInt();   
                    sc.nextLine();
                    check = true;

                    switch(option){
                        case 1:
                            findDefine();
                            break;
                        case 2:
                            System.out.println("Function 2");
                            break;
                        case 3:
                            System.out.println("Function 3");
                            break;
                        case 4:
                            System.out.println("Function 4");
                            break;
                        case 5:
                            System.out.println("Function 5");
                            break;
                        case 6:
                            System.out.println("Function 6");
                            break;
                        case 7:
                            System.out.println("Function 7");
                            break;
                        case 8:
                            System.out.println("Function 8");
                            break;
                        case 9:
                            System.out.println("Function 9");
                            break;
                        case 10:
                            System.out.println("Function 10");
                            break;
                        default: 
                            System.out.println("Your option is not available!");
                            break;
                    }
                }
                catch(Exception ex){
                    System.out.println("Your choice must be a number! Choose again: ");
                    sc.nextLine();
                }
            }
            
            System.out.println("Keep doing? (yes/no)");
            redo = sc.nextLine();
            
        }while(redo.equals("yes") == true);
    }

    public static void readFile(String fileName){
        try{
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);

            BufferedReader bufferedReader= new BufferedReader(fileReader);
            String line;

            while((line = bufferedReader.readLine()) != null){
                if(line.contains("`")){
                    List<String> slang = new ArrayList<String>();

                    String[] s = line.split("`");
                    if(s[1].contains("|")){
                        String[] tmp = s[1].split("\\|");
                        for (int i = 0; i < tmp.length; i++) {
                            tmp[i] = tmp[i].trim();
                        }
                        //Arrays.asList return a list that changable
                        slang = Arrays.asList(tmp);
                    }
                    else{
                        slang.add(s[1]);
                    }

                    //Add key-value into hashmap
                    hashMap.put(s[0], slang);

                }
            }

            fileReader.close();
            bufferedReader.close();
        }
        catch(Exception ex){
            System.out.println("Something goes wrong: " + ex);
        }
    }

    public static void findDefine(){
        System.out.print("Enter a Slang: ");
        String key = sc.nextLine();

        key = key.toUpperCase();

        // Check if hashMap have a key that equals to key wanna find
        if(hashMap.containsKey(key)){
            List<String> list = hashMap.get(key);
            System.out.println("");
            System.out.print(key + ": ");
            for(String define: list){
                System.out.println(define);
                System.out.println("");
            }
        }else{
            System.out.println("Cannot found!");
        }
    }

    public static void seeAllDictionary(){
        // for (String i:hashMap.keySet()){
        //     for (List<String> j:hashMap.values()){
        //         System.out.println(i + " " + j);
        //     }
        // }

        for(String i: hashMap.keySet()){
            System.out.println(i + " " +hashMap.get(i));
        }
    }

    public static void main(String[] args){
        if (hashMap.isEmpty()) {
            readFile("slang.txt");
        }

        Menu();
    }
}