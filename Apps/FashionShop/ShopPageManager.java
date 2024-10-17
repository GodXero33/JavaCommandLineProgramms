import java.util.HashMap;

class ShopPageManager {
	final private HashMap<String, String[]> pagesData = new HashMap<>();

	ShopPageManager () {
		String[] str;

		str = new String[12];
		this.pagesData.put("home", str);

		str[0] = "   ";
		str[1] = " /$$$$$$$$                 /$$       /$$                            /$$$$$$  /$$\n";
		str[2] = "| $$_____/                | $$      |__/                           /$$__  $$| $$\n";
		str[3] = "| $$    /$$$$$$   /$$$$$$$| $$$$$$$  /$$  /$$$$$$  /$$$$$$$       | $$  \\__/| $$$$$$$   /$$$$$$   /$$$$$$\n";
		str[4] = "| $$$$$|____  $$ /$$_____/| $$__  $$| $$ /$$__  $$| $$__  $$      |  $$$$$$ | $$__  $$ /$$__  $$ /$$__  $$\n";
		str[5] = "| $$__/ /$$$$$$$|  $$$$$$ | $$  \\ $$| $$| $$  \\ $$| $$  \\ $$       \\____  $$| $$  \\ $$| $$  \\ $$| $$  \\ $$\n";
		str[6] = "| $$   /$$__  $$ \\____  $$| $$  | $$| $$| $$  | $$| $$  | $$       /$$  \\ $$| $$  | $$| $$  | $$| $$  | $$\n";
		str[7] = "| $$  |  $$$$$$$ /$$$$$$$/| $$  | $$| $$|  $$$$$$/| $$  | $$      |  $$$$$$/| $$  | $$|  $$$$$$/| $$$$$$$/\n";
		str[8] = "|__/   \\_______/|_______/ |__/  |__/|__/ \\______/ |__/  |__/       \\______/ |__/  |__/ \\______/ | $$____/\n";
		str[9] = "                                                                                                | $$\n";
		str[10] = "                                                                                                | $$\n";
		str[11] = "                                                                                                |__/\n";
		
		str = new String[7];
		this.pagesData.put("place-order", str);
		
		str[0] = "   ";
		str[1] = " _____  _                   ____          _\n";
		str[2] = "|  __ \\| |                 / __ \\        | |\n";
		str[3] = "| |__) | | __ _  ___ ___  | |  | |_ __ __| | ___ _ __\n";
		str[4] = "|  ___/| |/ _` |/ __/ _ \\ | |  | | '__/ _` |/ _ \\ '__|\n";
		str[5] = "| |    | | (_| | (_|  __/ | |__| | | | (_| |  __/ |\n";
		str[6] = "|_|    |_|\\__,_|\\___\\___|  \\____/|_|  \\__,_|\\___|_|\n";
		
		str = new String[7];
		this.pagesData.put("search-customer", str);
		
		str[0] = "   ";
		str[1] = "  _____                     _        _____          _\\n";
		str[2] = " / ____|                   | |      / ____|        | |\\n";
		str[3] = "| (___   ___  __ _ _ __ ___| |__   | |    _   _ ___| |_ ___  _ __ ___   ___ _ __\\n";
		str[4] = " \\___ \\ / _ \\/ _` | '__/ __| '_ \\  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__|\\n";
		str[5] = " ____) |  __/ (_| | | | (__| | | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |\\n";
		str[6] = "|_____/ \\___|\\__,_|_|  \\___|_| |_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|\\n";
	}

	public void renderPageHeader (String page, String globalPadding) {
		if (!this.pagesData.containsKey(page)) return;

		final StringBuilder stringBuilder = new StringBuilder();
		final String[] strArr = this.pagesData.get(page);
		final String localPadding = strArr[0] + globalPadding;

		for (int i = 1; i < strArr.length; i++) {
			stringBuilder.append(localPadding);
			stringBuilder.append(strArr[i]);
		}

		System.out.println(stringBuilder.toString());
	}
}
