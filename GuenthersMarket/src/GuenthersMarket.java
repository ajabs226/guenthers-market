import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Collections;

public class GuenthersMarket {

	public static void main(String[] args) {

		Map<String, Double> items = fillItemsMap();
		List<String> orderNames = new ArrayList<>();
		List<Double> orderPrices = new ArrayList<>();
		Scanner scan = new Scanner(System.in);

		boolean endOrder = true;
		String item = null;
		char quote = '"';
		boolean checkout = true;
		String orderMore = null;
		double itemValue = 0.0;

		System.out.println("What would you like to order?\n");

		while (endOrder) {

			try {

				printMenu(items);
				item = scan.nextLine();

				if (!(items.containsKey(item))) {
					throw new Exception();

				} else {
					itemValue = items.get(item);
					orderNames.add(item);
					orderPrices.add(itemValue);
					System.out.println("\nAdding " + item + " to cart for $" + itemValue);

				}
				System.out.println("\nDo you want to order more? (y/n)");

				do {
					orderMore = scan.nextLine();
					if (orderMore.equalsIgnoreCase("y")) {
						System.out.println("Great! What else would you like?\n");
						checkout = false;

					} else if (orderMore.equalsIgnoreCase("n")) {
						System.out.println("Thanks for your order!\n");
						endOrder = false;
						break;

					} else {
						System.out.println("Enter " + quote + "y" + quote + " or " + quote + "n" + quote);

					}

				} while (!orderMore.equalsIgnoreCase("y"));

			} catch (IndexOutOfBoundsException e) {
				System.out.println("outofbounds");

			} catch (Exception e) {
				System.out.println("Not a valid entry. Select an item from the list.\n");

			}
			System.out.println("Here's what you got:");

			for (int i = 0; i < orderPrices.size(); i++) {
				System.out.println(orderNames.get(i) + "\t$" + orderPrices.get(i));

			}
			System.out.println("The highest cost item is at index: " + GetIndexOfHighestCostItem(orderPrices));
			System.out.println("The lowest cost item is at index: " + GetIndexOfLowestCostItem(orderPrices));
			System.out.println("The average cost of the items ordered is $" + GetAverageCostOfItemsOrdered(orderPrices));

		}

	}

	public static double GetAverageCostOfItemsOrdered(List<Double> itemPricesOrdered) {
		double returnAverage = 0.00;
		double totalPriceOfItems = 0.00;

		for (Double itemPrice : itemPricesOrdered) {
			totalPriceOfItems += itemPrice;

		}

		returnAverage = totalPriceOfItems / itemPricesOrdered.size();
		return returnAverage;

	}

	public static double GetIndexOfHighestCostItem(List<Double> orderPrices) {
		double highestCostItem = 0.00;
		double indexVariable = -1;

		for (int i = 0; i < orderPrices.size(); i++) {
			if (highestCostItem < orderPrices.get(i)) {
				highestCostItem = orderPrices.get(i);
				indexVariable = i;

			}

		}
		return indexVariable;

	}

	public static double GetIndexOfLowestCostItem(List<Double> orderPrices) {
		double lowestCostItem = 1000.00;
		double indexVariable = -1;

		for (int i = 0; i < orderPrices.size(); i++) {
			if (lowestCostItem > orderPrices.get(i)) {
				lowestCostItem = orderPrices.get(i);
				indexVariable = i;

			}

		}
		return indexVariable;

	}

	public static Map<String, Double> fillItemsMap() {
		Map<String, Double> items = new TreeMap<String, Double>();

		items.put("apple", 0.99);
		items.put("orange", 0.89);
		items.put("tissue", 2.56);
		items.put("onions", 8.99);
		items.put("eggs", 1.75);
		items.put("shampoo", 12.84);
		items.put("pudding", 0.13);
		items.put("soup", 5.63);
		items.put("pop", 2.11);
		items.put("qtips", 3.74);

		return items;

	}

	public static void printMenu(Map<String, Double> items) {
		System.out.println("ITEM\t\tPRICE");
		System.out.println("=====================");

		for (Map.Entry<String, Double> entry : items.entrySet())
			System.out.println(entry.getKey() + "\t\t$" + entry.getValue());

	}

}