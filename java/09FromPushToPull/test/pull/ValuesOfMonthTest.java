package pull;

import java.util.ArrayList;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Test;

import common.Transaction_API;

public class ValuesOfMonthTest {
	private static final LocalDate APRIL_ULTIMO = new LocalDate(2010, 4, 30);
	private static final LocalDate MAY_ULTIMO = new LocalDate(2010, 5, 31);

	@Test
	public void monthWithoutTransactionsHasZeroBalance() {
		ValuesOfMonth valuesOfMonth = new ValuesOfMonth(0, APRIL_ULTIMO, new ArrayList<Transaction_API>());
		Assert.assertEquals(0, valuesOfMonth.getBalance());
		Assert.assertEquals(0, valuesOfMonth.getAverageBalance());
	}

	@Test
	public void monthWithOneTransactionHasBalanceOfThat() {
		ArrayList<Transaction_API> transactionsOfMonth = new ArrayList<Transaction_API>();
		transactionsOfMonth.add(new Transaction_API(new LocalDate(2010, 4, 1), 100));

		ValuesOfMonth valuesOfMonth = new ValuesOfMonth(0, APRIL_ULTIMO, transactionsOfMonth);
		Assert.assertEquals(100, valuesOfMonth.getBalance());
		Assert.assertEquals(100, valuesOfMonth.getAverageBalance());
	}

	@Test
	public void monthWithTwoTransactionsHasSumOfThoseAsBalance() {
		ArrayList<Transaction_API> transactionsOfMonth = new ArrayList<Transaction_API>();
		transactionsOfMonth.add(new Transaction_API(new LocalDate(2010, 4, 1), 100));
		transactionsOfMonth.add(new Transaction_API(new LocalDate(2010, 4, 16), 100));

		ValuesOfMonth valuesOfMonth = new ValuesOfMonth(0, APRIL_ULTIMO, transactionsOfMonth);
		Assert.assertEquals(200, valuesOfMonth.getBalance());
		Assert.assertEquals(150, valuesOfMonth.getAverageBalance());
	}

	@Test
	public void twoMonthsWithTwoTransactionsHasSumOfThoseAsBalance() {
		ArrayList<Transaction_API> transactionsOfMonth = new ArrayList<Transaction_API>();
		transactionsOfMonth.add(new Transaction_API(new LocalDate(2010, 4, 16), 100));
		ValuesOfMonth april = new ValuesOfMonth(0, APRIL_ULTIMO, transactionsOfMonth);

		transactionsOfMonth = new ArrayList<Transaction_API>();
		transactionsOfMonth.add(new Transaction_API(new LocalDate(2010, 5, 16), 200));
		ValuesOfMonth may = new ValuesOfMonth(april.getBalance(), MAY_ULTIMO, transactionsOfMonth);

		Assert.assertEquals(300, may.getBalance());
		Assert.assertEquals(203, may.getAverageBalance());
	}
}
