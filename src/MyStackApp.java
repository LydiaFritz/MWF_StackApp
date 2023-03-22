import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MyStackApp {
	public static void main(String[] args) throws FileNotFoundException {

		Stack<Character> myStack = new Stack<Character>();
		Scanner fin = new Scanner(new File("Slot.h.txt"));

		while (fin.hasNextLine()) {
			String line = fin.nextLine();
			for (int i = 0; i < line.length(); i++) {
				char currCh = line.charAt(i);
				if (isLHS(currCh))
					myStack.add(currCh);
				else if (isRHS(currCh)) {
					if (myStack.isEmpty()) {
						// illegal state
						System.out.println("Stack is empty.  Failure.");
						System.exit(10);
					}
					// we are still here, so stack is not empty
					char currTop = myStack.pop();
					if (!isMatch(currTop, currCh)) {
						System.out.println("Mismatch.  Failure.");
						System.exit(20);
					}
				}
			}
		}
		System.out.println("Everything is OK");
	}

	public static boolean isLHS(char ch) {
		return ch == '[' || ch == '{' || ch == '(';
	}

	public static boolean isRHS(char ch) {
		return ch == ']' || ch == ')' || ch == '}';
	}

	public static boolean isMatch(char chLHS, char chRHS) {
		if (chLHS == '{')
			return chRHS == '}';
		else if (chLHS == '[')
			return chRHS == ']';
		else if (chLHS == '(')
			return chRHS == ')';
		return false;
	}
}
