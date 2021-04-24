import java.util.*;
import java.io.*;

public class main {
    private static Scanner sc = new Scanner(System.in);

    // Create a hashmap includes key: String and value: List<String>
    public static HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
    public static ArrayList<String> history = new ArrayList<String>();

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
            System.out.println("|          11.Exit                              |");
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
                            findSlang();
                            break;
                        case 3:
                            showHistory();
                            break;
                        case 4:
                            addFunction();
                            break;
                        case 5:
                            editSlang();
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
                        case 11:
                            System.exit(0);
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

            saveHistory("history.txt");
            writeFile("newSlang.txt");
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

    public static void writeFile(String fileName){
        try{
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);

            //Get slang word
            for (String slang : hashMap.keySet()) {
                fileWriter.write(slang + "`");
                List<String> definition = hashMap.get(slang);

                int i;
                for (i = 0; i < definition.size() - 1; i++) {
                    fileWriter.write(definition.get(i) + "| ");
                }

                fileWriter.write(definition.get(i) + "\n");
            }

            fileWriter.close();
        }catch(Exception ex){
            System.out.println("Something goes wrong: " + ex);
        }
    }

    // Function 1
    public static void findDefine(){
        System.out.print("Enter a Slang: ");
        String key = sc.nextLine();

        history.add(key);
        key = key.toUpperCase();

        // Check if hashMap have a key that equals to key wanna find
        if(hashMap.containsKey(key)){
            List<String> list = hashMap.get(key);
            System.out.print(key + ": ");
            System.out.println(list);

        }else{
            System.out.println("Cannot found!");
        }
    }

    // get the definition of the corresponding slangWord
    public static void getDefinition(String slangWord){
        List<String> list = hashMap.get(slangWord);

        for(String word: list){
            System.out.print(word + " ");
        }

        System.out.print("\n");
    }

    // Function 2
    public static void findSlang(){
        //Arraylist of slang words which has a definition that user wanna search
        ArrayList<String> slang = new ArrayList<String>();

        System.out.print("Enter a definition: ");
        String define = sc.nextLine();

        history.add(define);

        define = define.toLowerCase();


        //Check that definition is contains user's input
        for (String key: hashMap.keySet()) {    //get key
            for(String value: hashMap.get(key)){ //get value
                if(value.toLowerCase().contains(define)){
                    //If contains, add to the slang list
                    slang.add(key);
                }
            }
        }

        if(slang.isEmpty()){
            System.out.println("Cannot found!");
        }
        else{
            for(String word : slang){
                System.out.print("- " + word + ": ");
                getDefinition(word);
            }
        }
    }

    //save what you look for into history variable
    public static void saveHistory(String fileName){
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);

            for(String item : history){
                fileWriter.write(item + "\n");
            }

            fileWriter.close();
        }catch(Exception ex){
            System.out.println("Something goes wrong: " + ex);
        }
    }

    //read data from history variable and write down that data to history.txt file 
    public static ArrayList<String> readHistory(String fileName){
        ArrayList<String> history = new ArrayList<String>();

        try{
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while((line = bufferedReader.readLine()) != null){
                history.add(line);
            }

            fileReader.close();
            bufferedReader.close();

        }catch(Exception ex){
            System.out.println("Something goes wrong: " + ex);
        }

        return history;
    }

    // Function 3
    public static void showHistory(){
        System.out.print("History: ");
        System.out.println(history.toString());

        // for(String item: history){
        //     System.out.println("-" + item);
        // }
    }

    public static void addNew(String slangWord, String define){
        ArrayList<String> definition = new ArrayList<String>();
        definition.add(define);

        hashMap.put(slangWord.toUpperCase(), definition);
        System.out.println("Add successfully");
    }

    public static void addDuplicate(String slangWord, String define){
        List<String> definition = new ArrayList<String>();
        definition = hashMap.get(slangWord);
        definition.add(define);

        hashMap.put(slangWord.toUpperCase(), definition);
        System.out.println("Add successfully");
    }

    // Function 4
    public static void addFunction(){
        System.out.print("Enter slang word: ");
        String slang = sc.nextLine();
        slang = slang.toUpperCase().trim();

        System.out.print("Enter definition: ");
        String definition = sc.nextLine();

        if(hashMap.containsKey(slang)){
            System.out.println("This slang word already exists! \nDo you want to overwrite or duplicate it?");
            System.out.println("1. Overwrite");
            System.out.println("2. Duplicate");
            System.out.print("Your choice: ");;

            boolean check = false;
            int option;

            while(!check){
                try{
                    option = sc.nextInt();
                    sc.nextLine();
                    check = true;

                    switch(option){
                        case 1:
                            addNew(slang, definition);
                            break;
                        case 2:
                            addDuplicate(slang, definition);
                            break;
                        default:
                            break;
                    }
                }
                catch(Exception eX){
                    System.out.println("Your choice must be a number! Choose again: ");
                    sc.nextLine();
                }
            }
        }
        else{
            addNew(slang, definition);
        }
    }

    public static void editSlang(){
        System.out.print("Enter a slang: ");
        String slang = sc.nextLine();
        slang = slang.toUpperCase();

        if(hashMap.containsKey(slang)){
            System.out.print("Enter new slang: ");
            String newSlang = sc.nextLine();
            System.out.print("Enter new definition of this slang: ");
            String newdDefine = sc.nextLine();

            List<String> definition = new ArrayList<String>();
            definition.add(newdDefine);
            hashMap.put(newSlang.toUpperCase(), definition);
            hashMap.remove(slang);

            System.out.println("Edit successfully!");
        }
        else{
            System.out.println("This slang word does not exist!");
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

        readFile("newSlang.txt");
        history = readHistory("history.txt");

        Menu();
    }
}