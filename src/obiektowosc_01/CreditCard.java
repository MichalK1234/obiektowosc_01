package obiektowosc_01;

import java.util.List;

public class CreditCard extends AbstractBankingOps {

	private String PIN;
	

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String pIN) {
		
		PIN = pIN;
	}
	
	public static boolean ifCorrect(String insertedPIN, String PIN) {
		return insertedPIN.matches(PIN);
	}

	public static String getPINFromUser() {

		String insertedPIN = "";
		try {
			System.out.print("Insert pin: ");
			insertedPIN = Main.sc.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return insertedPIN;
	}

	@Override
	public void withdraw(double amount) {
		if (ifCorrect(getPINFromUser(), PIN)) {
			if( amount < checkAccountBalance()){
			setAccountBalance(checkAccountBalance() - amount);
			String s = "WYPLATA_" + amount + "_" + getCurrentDate();
			addToList(s);
			}
			else{
				addToList("ANULOWANA_" + getCurrentDate());
			}
			
		} else {
			addToList("BLAD_LOGOWANIA_" + getCurrentDate());
		}

	}

	public CreditCard(double balance, List<String> list, String PIN) {
		super(balance, list);
		setPIN(PIN);
	}

	public CreditCard() {
		
	}

	@Override
	public String toString() {
		return "CreditCard [PIN=" + PIN + ", getStanKonta()=" + checkAccountBalance() + "]";
	}

	@Override
	public void clearTransactionHistory() {

		if (ifCorrect(getPINFromUser(), PIN)) {
			super.clearTransactionHistory();
		} else {

			addToList("BLAD_LOGOWANIA" + getCurrentDate());
		}

	}

	private long countLoginErrors()
	{
		return getList().stream().filter(x -> x.startsWith("BLAD_LOGOWANIA")).count();
	}
	public static CreditCard theLeastLoginErrors(List<CreditCard> creditCardsList) {

		return creditCardsList
				.stream().sorted((k1,k2) -> Long.compare(k1.countLoginErrors(), k2.countLoginErrors())).findFirst().get();
	}

	public static CreditCard compareBalance(CreditCard k1, CreditCard k2) {
		return k1.checkAccountBalance() > k2.checkAccountBalance() ? k1 : k2;
	}

	public static void operationsCounter(List<CreditCard> lista) {

		lista.stream().forEach(k -> {
			System.out.println("STAN KONTA - " + k.checkAccountBalance()); 
			System.out.println("WPLATA - " + k.getList().stream().filter(x -> x.startsWith("WPLATA")).count());
			System.out.println("WYPLATA - " + k.getList().stream().filter(x -> x.startsWith("WYPLATA")).count());
			System.out.println("ANULOWANA - " + k.getList().stream().filter(x -> x.startsWith("ANULOWANA")).count());
			System.out.println("BLAD_LOGOWANIA - " + k.getList().stream().filter(x -> x.startsWith("BLAD_LOGOWANIA")).count());
		});

			

		

	}

}
