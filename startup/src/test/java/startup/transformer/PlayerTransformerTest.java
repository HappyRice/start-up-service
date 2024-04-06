//package startup.transformer;
//
//import com.genoapay.application.common.enumeration.EmploymentStatusType;
//import com.genoapay.application.common.enumeration.ExpenseDetailType;
//import com.genoapay.application.common.enumeration.IncomeFrequencyType;
//import com.genoapay.application.model.ExpenseDetails;
//import com.genoapay.application.model.IncomeDetails;
//import com.genoapay.integration.lpay.client.dto.fraud.Finance;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(MockitoJUnitRunner.class)
//public class PlayerTransformerTest {
//
//	@Test
//	public void testBuildFinanceAnnually() {
//		final List<IncomeDetails> incomeDetailsList = new ArrayList<>();
//
//		incomeDetailsList.add(new IncomeDetails.Builder()
//				.withIncome(120000)
//				.withCompanyName("LFS")
//				.withEmploymentStatus(EmploymentStatusType.PERMANENT_FULL_TIME)
//				.withIncomeFrequency(IncomeFrequencyType.ANNUALLY)
//				.build());
//
//		final List<ExpenseDetails> expenseDetailsList = new ArrayList<>();
//
//		expenseDetailsList.add(new ExpenseDetails.Builder()
//				.withAmount(100)
//				.withType(ExpenseDetailType.HOUSING)
//				.build());
//		expenseDetailsList.add(new ExpenseDetails.Builder()
//				.withAmount(100)
//				.withType(ExpenseDetailType.DEBT_REPAYMENTS)
//				.build());
//		expenseDetailsList.add(new ExpenseDetails.Builder()
//				.withAmount(100)
//				.withType(ExpenseDetailType.LIVING)
//				.build());
//
//		final Finance finance = FinanceTransformer.buildFinance(incomeDetailsList, expenseDetailsList);
//
//		assertEquals(Integer.valueOf(300), finance.getExpense());
//		assertEquals(Integer.valueOf(10000), finance.getIncome());
//	}
//
//	@Test
//	public void testBuildFinanceMonthly() {
//		final List<IncomeDetails> incomeDetailsList = new ArrayList<>();
//
//		incomeDetailsList.add(new IncomeDetails.Builder()
//				.withIncome(10000)
//				.withCompanyName("LFS")
//				.withEmploymentStatus(EmploymentStatusType.PERMANENT_FULL_TIME)
//				.withIncomeFrequency(IncomeFrequencyType.MONTHLY)
//				.build());
//
//		final List<ExpenseDetails> expenseDetailsList = new ArrayList<>();
//
//		expenseDetailsList.add(new ExpenseDetails.Builder()
//				.withAmount(100)
//				.withType(ExpenseDetailType.HOUSING)
//				.build());
//		expenseDetailsList.add(new ExpenseDetails.Builder()
//				.withAmount(100)
//				.withType(ExpenseDetailType.DEBT_REPAYMENTS)
//				.build());
//		expenseDetailsList.add(new ExpenseDetails.Builder()
//				.withAmount(100)
//				.withType(ExpenseDetailType.LIVING)
//				.build());
//
//		final Finance finance = FinanceTransformer.buildFinance(incomeDetailsList, expenseDetailsList);
//
//		assertEquals(Integer.valueOf(300), finance.getExpense());
//		assertEquals(Integer.valueOf(10000), finance.getIncome());
//	}
//
//	@Test
//	public void testBuildFinanceWeekly() {
//		final List<IncomeDetails> incomeDetailsList = new ArrayList<>();
//
//		incomeDetailsList.add(new IncomeDetails.Builder()
//				.withIncome(1000)
//				.withCompanyName("LFS")
//				.withEmploymentStatus(EmploymentStatusType.PERMANENT_FULL_TIME)
//				.withIncomeFrequency(IncomeFrequencyType.WEEKLY)
//				.build());
//
//		final List<ExpenseDetails> expenseDetailsList = new ArrayList<>();
//
//		expenseDetailsList.add(new ExpenseDetails.Builder()
//				.withAmount(100)
//				.withType(ExpenseDetailType.HOUSING)
//				.build());
//		expenseDetailsList.add(new ExpenseDetails.Builder()
//				.withAmount(100)
//				.withType(ExpenseDetailType.DEBT_REPAYMENTS)
//				.build());
//		expenseDetailsList.add(new ExpenseDetails.Builder()
//				.withAmount(100)
//				.withType(ExpenseDetailType.LIVING)
//				.build());
//
//		final Finance finance = FinanceTransformer.buildFinance(incomeDetailsList, expenseDetailsList);
//
//		assertEquals(Integer.valueOf(300), finance.getExpense());
//		assertEquals(Integer.valueOf(4333), finance.getIncome());
//	}
//}
