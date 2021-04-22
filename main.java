import java.util.Scanner;


public class main {
	private static Scanner sc = new Scanner(System.in);
	
	public static void Menu(){
		String redo = null;
		int option;

		do{
			System.out.println("1. Search by word");
        	System.out.println("2. Search by definition");
        	System.out.println("3. History");
        	System.out.println("4. Add new slang-word");
        	System.out.println("5. Edit new slang-word");
        	System.out.println("6. Delete new slang-word");
        	System.out.println("7. Reset SlangWord's list");
        	System.out.println("8. Random slang-word");
        	System.out.println("9. Funny game! Find definition by slang-word");
        	System.out.println("10. Funny game! Find slang-word by definition");
        	System.out.println("Choose on of the options: ");

        	option = sc.nextInt();
        	sc.nextLine();

        	switch(option){
        		case 1:
        			System.out.println("1");
        			break;
        		case 2:
        			System.out.println("2");
        			break;
        		case 3:
        			System.out.println("3");
        			break;
        		case 4:
        			System.out.println("4");
        			break;
        		case 5:
        			System.out.println("5");
        			break;
        		case 6:
        			System.out.println("6");
        			break;
        		case 7:
        			System.out.println("7");
        			break;
        		case 8:
        			System.out.println("8");
        			break;
        		case 9:
        			System.out.println("9");
        			break;
        		case 10:
        			System.out.println("10");
        			break;
        		default: 
        			System.out.println("Your option is not available!");
        			break;
        	}

        	System.out.println("Keep doing? (yes/no)");
        	redo = sc.nextLine();
        	
		}while(redo.equals("yes") == true);
	}
	
	public static void main(String[] args){
		Menu();
	}
}