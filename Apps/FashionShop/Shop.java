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
		System.out.println("home");
		console.getInteger("Enter an Integer : ");
	}

	public static void placeOrder () {
		System.out.println("place-order");
	}

	public static void searchCustomer () {
		System.out.println("search-customer");
	}

	public static void searchOrder () {
		System.out.println("search-order");
	}

	public static void viewReports () {
		System.out.println("view-reports");
	}

	public static void changeStatus () {
		System.out.println("change-status");
	}

	public static void deleteOrder () {
		System.out.println("delete-order");
	}

	public static void customerReports () {
		System.out.println("customer-reports");
	}

	public static void itemReports () {
		System.out.println("item-reports");
	}

	public static void orderReports () {
		System.out.println("order-reports");
	}

	public static void bestInCustomers () {
		System.out.println("best-in-customers");
	}

	public static void viewCustomers () {
		System.out.println("view-customers");
	}

	public static void allCustomerDetails () {
		System.out.println("all-customer-details");
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
