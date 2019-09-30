

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.dao.BankApplicationDaoImpl;

class BankApplicationDaoImplTest {

	private BankApplicationDaoImpl dao;
	@BeforeEach
	void setUp() throws Exception {
		
		dao = new BankApplicationDaoImpl();
	}
	

	@AfterEach
	void tearDown() throws Exception {
		
		dao=null;
	}

	@Test
	void salesDetailsEntered() {
		double balance= dao.get_Balance("manali2512","25manali@");
		assertEquals(1500, balance);
		balance= dao.get_Balance("shivani25","shi56vani");
		assertEquals(10000, balance);
		 balance= dao.get_Balance("rohit96","rohit0101");
		assertEquals(2000, balance);
		 balance= dao.get_Balance("shreya25","2512@shreya");
		assertEquals(5000, balance);
	}
	
	@Test
	void depositAmount() {
		double balance= dao.amt_deposit("manali2512","25manali@",200);
		assertEquals(1700,balance);
		 balance= dao.amt_deposit("shivani25","shi56vani",500);
		assertEquals(10500,balance);
		 balance= dao.amt_deposit("rohit96","rohit0101",100);
		assertEquals(2100,balance);
		 balance= dao.amt_deposit("shreya25","2512@shreya",200);
		assertEquals(5200,balance);
		
		
		
	}
	@Test
	void WithdrawAmountShouldBeMoreThanAvailableBalance() {
		double balance= dao.amt_withdraw("manali2512","25manali@",200);
		assertEquals(1300,balance);
		 balance= dao.amt_withdraw("shivani25","shi56vani",500);
		assertEquals(9500,balance);
		 balance= dao.amt_withdraw("rohit96","rohit0101",100);
		assertEquals(1900,balance);
		 balance= dao.amt_withdraw("shreya25","2512@shreya",200);
		assertEquals(4800,balance);

}
}
