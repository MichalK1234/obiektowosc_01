package obiektowosc_01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractBankingOps implements BankingOperations {

	private double accountBalance;
	private List<String> list = new LinkedList<>();

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = 0;
		if (accountBalance >= 0) {
			this.accountBalance = accountBalance;
		}

	}

	public List<String> getList() {
		return list;
	}

	public void setLista(List<String> list) {
		this.list = list;
	}

	public AbstractBankingOps(double accountBalance, List<String> list) {
		super();
		this.accountBalance = accountBalance;
		this.list = list;
	}

	public AbstractBankingOps() {

	}

	@Override
	public String toString() {
		return " AbstractBankingOps [accountBalance=" + accountBalance + ", list=" + list + "]";
	}

	@Override
	public void deposit(double amount) {
		String s = "";
		if (amount > 0) {

			accountBalance += amount;
			s = "WPLATA_" + amount + "_" + getCurrentDate();

		}

		else {

			s = "ANULOWANA_" + getCurrentDate();

		}
		addToList(s);
	}

	public double checkAccountBalance() {
		return accountBalance;
	}

	public double checkBalance(int i) {

		List<String> el = new ArrayList<>();

		el = list.subList(list.size() - i, list.size());

		for (String string : el) {

			if (string.startsWith("WYPLATA_")) {

				String[] k = string.split("[\\_]");

				double am = Double.parseDouble(k[1]);

				accountBalance += am;
			} else if (string.startsWith("WPLATA_")) {

				String[] k = string.split("[\\_]");

				double am = Double.parseDouble(k[1]);

				accountBalance -= am;

			}

		}
		return accountBalance;

	}

	public int countCancelledTransactions() {

		return (int) list.stream().filter(f -> f.startsWith("ANULOWANA")).count();

	}

	public void clearTransactionHistory() {
		list.clear();
	}

	public void addToList(String s) {
		list.add(s);
	}

	public static String getCurrentDate() {

		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

		return String.valueOf(ldt.format(dtf));
	}
}
