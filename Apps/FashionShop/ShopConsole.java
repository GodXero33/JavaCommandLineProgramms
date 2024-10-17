import java.util.Scanner;

class ShopConsole {
	final private Scanner input = new Scanner(System.in);

	public void clearAll () {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void clearLine (int lineCount) {
		System.out.printf("\033[%dA\033[0J", lineCount);
		System.out.flush();
	}

	public int getInteger (String message) {
		while (true) {
			System.out.print(message);
			final String str = input.nextLine();
			
			if (str.matches("^(\\d+)$")) return Integer.parseInt(str);

			this.clearLine(1);
		}
	}

	public double getDouble (String message) {
		while (true) {
			System.out.print(message);
			final String str = input.nextLine();
			
			if (str.matches("^(\\d+)(\\.\\d+)?$")) return Double.parseDouble(str);

			this.clearLine(1);
		}
	}

	public String getString (String message) {
		System.out.print(message);
		return input.nextLine();
	}
}
