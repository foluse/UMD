import java.util.Scanner;

public class OriolesBaseball {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String name; //player last name
		int initialResponse,number; //initial response, response number
		final int NUM_DAVIS = 19; final int NUM_COBB = 17; 
		final int NUM_MANCINI = 16; final int NUM_IGLESIAS = 11;

		System.out.print("Type 1 to enter a number or 2 to enter a name: ");
		initialResponse = scanner.nextInt();
		if (initialResponse == 1) { //processing an entry of 1
			System.out.print("Enter player number: "); 
			number = scanner.nextInt();

			if (number == 19 || number == 17 || number == 16 || number == 11) {
				System.out.print ("Which player wears number " + number + 
						" on his jersey? "); 
				scanner.nextLine();
				name = scanner.next();
				if (name.equals("Davis") && number == NUM_DAVIS || name.equals 
						("Cobb") && number == NUM_COBB || name.equals("Mancini") 
						&& number == NUM_MANCINI || name.equals("Iglesias") && 
						number == 
						NUM_IGLESIAS) 
				{
					System.out.print("Correct!");
				} else {
					System.out.print("Incorrect!");
				}

			} else {
				System.out.print("Invalid choice.");
			}

		} else { //processing an entry of 2
			System.out.print("Choose a name:");
			scanner.nextLine();
			name = scanner.nextLine();

			if (name.equals("Davis") || name.equals ("Cobb")
					|| name.equals("Mancini") || name.equals("Iglesias")) {
				System.out.print("What number does " + name + " wear? ");
				number = scanner.nextInt();
				if (name.equals("Davis") && number == NUM_DAVIS
						|| name.equals ("Cobb")
						&& number == NUM_COBB || name.equals("Mancini") && 
						number == NUM_MANCINI || name.equals("Iglesias") && 
						number == NUM_IGLESIAS){
					System.out.print("Correct!");
				}
				else {
					System.out.print("Incorrect!");
				}
			}
			else {
				System.out.print("Invalid choice.");
			}
		}

		scanner.close();

	}
}
