import java.util.ArrayList;
import java.util.Random;

class ShopData {
	final String[] sizes = { "XS", "S", "M", "L", "XL", "XXL" };
	final double[] prices = { 600, 800, 900, 1000, 1100, 1200 };

	ArrayList<Integer> idList = new ArrayList<Integer>();
	ArrayList<Integer> statusList = new ArrayList<Integer>();
	ArrayList<String> phoneList = new ArrayList<String>();
	ArrayList<Integer> sizesList = new ArrayList<Integer>();
	ArrayList<Integer> qtyList = new ArrayList<Integer>();
	ArrayList<Double> amountList = new ArrayList<Double>();

	int current = 0;

	public static String getOrderID (int id) {
		return String.format("ODR#%05d", id);
	}

	private static String getRandomPhoneNo (Random rand) {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("07");
		
		for (int i = 0; i < 8; i++) {
			stringBuilder.append(rand.nextInt(10));
		}

		final String output = stringBuilder.toString();
		return output;
	}

	public static void addSampleData (ShopData instance) {
		final Random rand = new Random();

		for (int i = 0; i < 15; i++) {
			instance.idList.add(i);
			instance.statusList.add(0);
			instance.phoneList.add(getRandomPhoneNo(rand));
			
			final int size = rand.nextInt(instance.sizes.length);
			final int qty = rand.nextInt(49) + 1;
			final double amount = instance.prices[size] * qty;
			
			instance.sizesList.add(size);
			instance.qtyList.add(qty);
			instance.amountList.add(amount);
		}
	}

	private static String getTableHR (int ...cols) {
		final StringBuilder stringBuilder = new StringBuilder();
		final String hrSym = "-";

		stringBuilder.append("+");

		for (int size : cols) {
			stringBuilder.append(hrSym.repeat(size));
			stringBuilder.append("+");
		}

		final String output = stringBuilder.toString();
		return output;
	}

	public void printData () {
		String tableHr = ShopData.getTableHR(8, 9);

		System.out.println(tableHr);
		System.out.println("|  size  |  price  |");
		System.out.println(tableHr);

		for (int i = 0; i < this.sizes.length; i++) {
			final String str = String.format("|  %-4s  | %7.2f |", this.sizes[i], this.prices[i]);

			System.out.println(str);
			System.out.println(tableHr);
		}

		tableHr = ShopData.getTableHR(13, 10, 14, 8, 7, 16);

		System.out.println(tableHr);
		System.out.println("|     id      |  status  |    phone     |  size  |  qty  |     amount     |");
		System.out.println(tableHr);

		for (int i = 0; i < this.idList.size(); i++) {
			System.out.printf(
				"|  %9s  |    %2d    |  %10s  |  %4s  | %5d |   %10.2f   |\n",
				ShopData.getOrderID(this.idList.get(i)),
				this.statusList.get(i),
				this.phoneList.get(i),
				this.sizesList.get(i),
				this.qtyList.get(i),
				this.amountList.get(i)
			);
			System.out.println(tableHr);
		}
	}
}
