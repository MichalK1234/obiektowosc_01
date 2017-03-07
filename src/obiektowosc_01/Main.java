package obiektowosc_01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//PROGRAM WYKONANY W RAMACH SZKOLENIA KM-PROGRAMS
//http://km-programs.pl/

public class Main {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		CreditCard cC = new CreditCard();

		cC.setAccountBalance(1200);
		cC.setPIN("1312");

		System.out.println("BALANCE + " + cC.checkAccountBalance());

		cC.withdraw(240);

		System.out.println("BALANCE + " + cC.checkAccountBalance());

		cC.deposit(3000);

		System.out.println("STAN KONTA + " + cC.checkAccountBalance());
		System.out.println("LOGS : " + cC.getList());

		System.out.println("Cancelled operations: ");
		System.out.println(cC.countCancelledTransactions());

		System.out.println("Account balance before last operation: ");
		System.out.println(cC.checkBalance(1));

		CreditCard kp2 = new CreditCard();
		kp2.setPIN("1234");
		kp2.setAccountBalance(500);

		List<CreditCard> listaKart = new ArrayList<>();
		listaKart.add(cC);
		listaKart.add(kp2);

		System.out.print("The least login errors: ");
		System.out.println(CreditCard.theLeastLoginErrors(listaKart));

		System.out.println("Transactions counter: ");
		CreditCard.operationsCounter(listaKart);

		System.out.println("Compare balance: ");
		System.out.println(CreditCard.compareBalance(cC, kp2));

		System.out.println("Clear");
		cC.clearTransactionHistory();
		System.out.println(cC.getList());
		sc.close();

	}

}
