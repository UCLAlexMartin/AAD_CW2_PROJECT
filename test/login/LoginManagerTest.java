package login;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;

public class LoginManagerTest {
	private LoginManager manager;

	@Before
	public void setUp() {
		manager = new LoginManager();
	}

	@Test
	public void getCorrectResultSetbyValidUserName() {
		ResultSet resultSet;
		String username = "";
		try {
			resultSet = manager.getResultSetbyUsername("ucladmin");
			while (resultSet.next()) {
				username = resultSet.getString("Username");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("ucladmin", username);
	}

	@Test
	public void ResultSetisNullbyInvalidUserName_1(){
		try {
			ResultSet resultSet = manager.getResultSetbyUsername("abc");
			assertTrue(!resultSet.next());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void ResultSetisNullbyInvalidUserName_2(){
		try {
			//';update users set User_Type_Id=1 where Username='hope
			ResultSet resultSet = manager.getResultSetbyUsername("'or 'a'='a");
			assertTrue(!resultSet.next());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Test
    public void testgetDestination_UclAdmin() throws Exception {
		String redirectpage = manager.getDestination(1);
		assertEquals("/CharityWare/uclAdmin.jsp",redirectpage);
	}

    @Test
    public void testgetDestination_CharityAdmin() throws Exception {
		String redirectpage = manager.getDestination(2);
		assertEquals("/CharityWare/CharityAdminServlet",redirectpage);
	}
}
