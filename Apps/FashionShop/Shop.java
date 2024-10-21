import java.util.ArrayList;
import java.util.HashMap;

class Shop {
	final static ShopData data = new ShopData();
	final static ShopConsole console = new ShopConsole();
	final static ShopPageManager pageManager = new ShopPageManager();
	final static HashMap<String, Runnable> pagesMap = new HashMap<>();
	final static String padding = " ".repeat(5);

	static {
		ShopData.addSampleData(data);
		
		pagesMap.put("home", Shop::homePage);
		pagesMap.put("place-order", Shop::placeOrder);
		pagesMap.put("search-customer", Shop::searchCustomer);
		pagesMap.put("search-order", Shop::searchOrder);
		pagesMap.put("view-reports", Shop::viewReports);
		pagesMap.put("change-status", Shop::changeStatus);
		pagesMap.put("delete-order", Shop::deleteOrder);
		pagesMap.put("customer-reports", Shop::customerReports);
		pagesMap.put("item-reports", Shop::itemReports);
		pagesMap.put("order-reports", Shop::orderReports);
		pagesMap.put("best-in-customers", Shop::bestInCustomers);
		pagesMap.put("view-customers", Shop::viewCustomers);
		pagesMap.put("all-customer-details", Shop::allCustomerDetails);
		pagesMap.put("best-in-item-by-qty", Shop::bestInItemByQty);
		pagesMap.put("best-in-item-by-amount", Shop::bestInItemByAmount);
		pagesMap.put("all-orders", Shop::allOrders);
		pagesMap.put("orders-by-amount", Shop::ordersByAmount);
	}

	public static void main (String[] args) {
		goToPage("home");
	}

	public static boolean isValidPage (String pageName) {
		return pagesMap.containsKey(pageName);
	}

	public static void goToPage (String pageName) {
		if (!isValidPage(pageName)) return;

		console.clearAll();
		pageManager.renderPageHeader(pageName, padding);
		pagesMap.get(pageName).run();
	}


	// Pages

	public static void homePage () {
		final String[] possiblePages = {
			"place-order",
			"search-customer",
			"search-order",
			"view-reports",
			"change-status",
			"delete-order"
		};

		System.out.println(padding + "          [1] Place Order                                        [2] Search Customer\n");
		System.out.println(padding + "          [3] Search Order                                       [4] View Reports\n");
		System.out.println(padding + "          [5] Set Order Status                                   [6] Delete Order\n");
		System.out.println(padding + "                                       [7] Exit\n\n");

		int option = console.getInteger(padding + "          Input Option : ");
		System.out.println();

		if (option == 7) System.exit(0);

		if (option < 1 || option > 6) {
			final boolean response = console.getUserResponse(padding + "Invalid Option. Do you want to re-enter. Press \"y\" if yes, or \"n\" to exit : ");

			if (response) goToPage("home");
		} else {
			goToPage(possiblePages[option - 1]);
		}
	}

	public static void placeOrder () {
		System.out.println(padding + "Order ID : " + data.getCurrentOrderID() + "\n");

		String phone = console.getMatchedString(padding + "Enter Customer Phone Number : ", "^(0?7\\d{8})$");
		System.out.println();

		String size = console.getMatchedString(padding + "Enter T-Shirt Size : ", "^(" + String.join("|", data.sizes) + "|" + String.join("|", data.sizes).toLowerCase() + ")$");
		System.out.println();

		int qty;

		while (true) {
			qty = console.getInteger(padding + "Enter QTY : ");
			if (qty > 0) break;
			console.clearLine(1);
		}

		System.out.println();

		double amount = data.getAmount(size, qty);
		System.out.println(padding + "Amount : " + amount);
		System.out.println();

		boolean response = console.getUserResponse(padding + "Do you want to place this order? (y/n) : ");
		System.out.println();

		if (response) {
			if (phone.length() == 9) phone = '0' + phone;

			data.pushRecord(phone, size, qty, amount);
			System.out.println(padding + "          Order Placed!");
			System.out.println();
		}

		response = console.getUserResponse(padding + "Do you want to place another order? (y/n) : ");

		if (response) {
			goToPage("place-order");
		} else {
			goToPage("home");
		}
	}

	public static void searchCustomer () {
		final String tablePadding = padding + "          ";
		final String tableHr = tablePadding + "+------+------+------------+";
		String phone = console.getMatchedString(padding + "Enter Customer Phone Number (Enter 1 to return) : ", "^(0?7\\d{8})|1$");

		if (phone.equals("1")) {
			goToPage("home");
			return;
		}

		if (phone.length() == 9) phone = '0' + phone;
		
		System.out.println();
		System.out.println(tableHr + "\n" + tablePadding + "| Size | Qty  |   Amount   |\n" + tableHr);
		ArrayList<Integer> orderIndices = data.getAllMatchesFromList(data.phoneList, phone);

		for (int i = 0; i < orderIndices.size(); i++) {
			final int index = orderIndices.get(i);

			System.out.printf(
				tablePadding + "| %-4s | %4d | %10.2f |\n" + tableHr + "\n",
				data.sizes[data.sizesList.get(index)],
				data.qtyList.get(index),
				data.amountList.get(index)
			);
		}

		System.out.println();
		boolean response = console.getUserResponse(padding + "Do you want to search another customer report? (y/n) : ");

		if (response) {
			goToPage("search-customer");
		} else {
			goToPage("home");
		}
	}

	public static void printRecordData (int id) {
		ShopDataRecord record = data.getRecord(id);

		System.out.println("\n" + padding + "Phone Number : " + record.phone);
		System.out.println(padding + "Size         : " + data.sizes[record.size]);
		System.out.println(padding + "QTY          : " + record.qty);
		System.out.println(padding + "Amount       : " + record.amount);
		System.out.println(padding + "Status       : " + data.getStatus(record.status) + "\n");
	}

	public static void searchOrder () {
		final String message = padding + "Enter order ID : ";
		int id;

		while (true) {
			id = console.getInteger(message);
			int index = data.idList.indexOf(id);

			if (index != -1) {
				console.clearLine(1);
				System.out.print(message);
				System.out.println(data.getOrderID(id - 1));
				break;
			}

			console.clearLine(1);
		}

		printRecordData(id);
		boolean response = console.getUserResponse(padding + "Do you want to search another order? (y/n) : ");

		if (response) {
			goToPage("search-order");
		} else {
			goToPage("home");
		}
	}

	public static void viewReports () {
		final String[] possiblePages = {
			"customer-reports",
			"item-reports",
			"order-reports",
			"home"
		};

		System.out.println(padding + "          [1] Customer Reports\n");
		System.out.println(padding + "          [2] Item Reports\n");
		System.out.println(padding + "          [3] Order Reports\n");
		System.out.println(padding + "          [4] Main Menu\n");

		int option = console.getInteger(padding + "Enter Option : ");

		if (option < 1 || option > 4) {
			final boolean response = console.getUserResponse(padding + "Invalid Option. Do you want to re-enter. Press \"y\" if yes, or \"n\" to return main menu : ");

			if (response) {
				goToPage("view-reports");
			} else {
				goToPage("home");
			}

			return;
		}

		goToPage(possiblePages[option - 1]);
	}

	public static void changeStatus () {
		System.out.println("change-status");
	}

	public static void deleteOrder () {
		final String message = padding + "Enter order ID : ";
		int id;

		while (true) {
			id = console.getInteger(message);
			int index = data.idList.indexOf(id);

			if (index != -1) {
				console.clearLine(1);
				System.out.print(message);
				System.out.println(data.getOrderID(id - 1));
				break;
			}

			console.clearLine(1);
		}

		printRecordData(id);
		boolean response = console.getUserResponse(padding + "Do you want to delete this order? (y/n) : ");
		System.out.println();

		if (response) {
			data.deleteRecord(id);
			System.out.println(padding + "          Order Deleted!");
			System.out.println();
		}

		response = console.getUserResponse(padding + "Do you want to delete another order? (y/n) : ");

		if (response) {
			goToPage("delete-order");
		} else {
			goToPage("home");
		}
	}

	public static void customerReports () {
		final String[] possiblePages = {
			"best-in-customers",
			"view-customers",
			"all-customer-details",
			"view-reports",
			"home"
		};

		System.out.println(padding + "          [1] Best in Customers\n");
		System.out.println(padding + "          [2] View Customers\n");
		System.out.println(padding + "          [3] All Customer Report\n");
		System.out.println(padding + "          [4] Return to Reports\n");
		System.out.println(padding + "          [5] Main Menu\n");

		int option = console.getInteger(padding + "Enter Option : ");

		if (option < 1 || option > 5) {
			final boolean response = console.getUserResponse(padding + "Invalid Option. Do you want to re-enter. Press \"y\" if yes, or \"n\" to return reports : ");

			if (response) {
				goToPage("customer-reports");
			} else {
				goToPage("view-reports");
			}

			return;
		}

		goToPage(possiblePages[option - 1]);
	}

	public static void itemReports () {
		System.out.println("item-reports");
	}

	public static void orderReports () {
		System.out.println("order-reports");
	}

	public static void viewCustomersCommonMethod (boolean needsSort) {
		HashMap<String, ShopDataRecord> map = data.getMergedRecordsByPhone();
		final int n = map.size();
		final String tablePadding = padding + "          ";
		final String tableHr = tablePadding + "+-------------+---------+--------------+";
		final String[] possiblePages = {
			"customer-reports",
			"home"
		};

		System.out.println(tableHr + "\n" + tablePadding + "| Customer ID | All Qty | Total Amount |\n" + tableHr);

		if (needsSort) {
			int[] qtyList = new int[n];
			String[] phoneList = new String[n];
			int i = 0;
	
			for (String phone : map.keySet()) {
				qtyList[i] = map.get(phone).qty;
				phoneList[i] = phone;
				i++;
			}
	
			int[] sortIndices = ShopArrays.getSortedArrayIndices(qtyList, false);
	
			for (int j = 0; j < n; j++) {
				String phone = phoneList[sortIndices[j]];
				ShopDataRecord record = map.get(phone);
				System.out.printf(
					tablePadding + "|  %s |  %5d  |  %10.2f  |\n" + tableHr + "\n",
					phone,
					record.qty,
					record.amount
				);
			}
		} else {
			for (String phone : map.keySet()) {
				ShopDataRecord record = map.get(phone);
				System.out.printf(
					tablePadding + "|  %s |  %5d  |  %10.2f  |\n" + tableHr + "\n",
					phone,
					record.qty,
					record.amount
				);
			}
		}

		System.out.println();
		System.out.println(padding + "       [1] Return to Customer Reports\n");
		System.out.println(padding + "       [2] Return to Main Menu\n");

		int option;
		
		while (true) {
			option = console.getInteger(padding + "Enter Option : ");

			if (option == 1 || option == 2) {
				break;
			}

			console.clearLine(1);
		}

		goToPage(possiblePages[option - 1]);
	}

	public static void bestInCustomers () {
		viewCustomersCommonMethod(true);
	}

	public static void viewCustomers () {
		viewCustomersCommonMethod(false);
	}

	public static void allCustomerDetails () {
		final String tablePadding = padding + "          ";
		final String tableHr = tablePadding + "+--------------+------+------+------+------+------+------+------------+";
		final String[] possiblePages = {
			"customer-reports",
			"home"
		};
		HashMap<String, int[]> map = data.getRecordsAllDetails();

		System.out.println(tableHr + "\n" + tablePadding + "| Phone Number |  XS  |   S  |   M  |   L  |  XL  |  XXL |    Total   |\n" + tableHr);

		for (String phone : map.keySet()) {
			int[] record = map.get(phone);
			
			System.out.printf(
				tablePadding + "|  %s  | %4d | %4d | %4d | %4d | %4d | %4d | %7d.00 |\n" + tableHr + "\n",
				phone,
				record[0],
				record[1],
				record[2],
				record[3],
				record[4],
				record[5],
				record[6]
			);
		}

		System.out.println();
		System.out.println(padding + "       [1] Return to Customer Reports\n");
		System.out.println(padding + "       [2] Return to Main Menu\n");

		int option;
		
		while (true) {
			option = console.getInteger(padding + "Enter Option : ");

			if (option == 1 || option == 2) {
				break;
			}

			console.clearLine(1);
		}

		goToPage(possiblePages[option - 1]);
	}

	public static void bestInItemByQty () {
		System.out.println("best-in-item-by-qty");
	}

	public static void bestInItemByAmount () {
		System.out.println("best-in-item-by-amount");
	}

	public static void allOrders () {
		System.out.println("all-orders");
	}

	public static void ordersByAmount () {
		System.out.println("orders-by-amount");
	}
}
