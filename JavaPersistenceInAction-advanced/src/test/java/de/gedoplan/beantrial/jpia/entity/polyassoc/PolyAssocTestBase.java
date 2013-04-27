package de.gedoplan.beantrial.jpia.entity.polyassoc;

import de.gedoplan.beantrial.jpia.TestBase;

import org.junit.BeforeClass;

public abstract class PolyAssocTestBase extends TestBase
{
  protected static EMail      testEMail1    = new EMail("1", "eins@mail.com");
  protected static EMail      testEMail2    = new EMail("2", "zwei@mail.com");
  protected static EMail[]    testEMails    = { testEMail1, testEMail2 };

  protected static Phone      testPhone1    = new Phone("1", "0800 1111111");
  protected static Phone      testPhone2    = new Phone("2", "0800 2222222");
  protected static Phone[]    testPhones    = { testPhone1, testPhone2 };

  protected static Employee   testEmployee1 = new Employee("Mustermann", "Max", testEMail1);
  protected static Employee   testEmployee2 = new Employee("Musterfrau", "Maria", testPhone1);
  protected static Employee[] testEmployees = { testEmployee1, testEmployee2 };

  @BeforeClass
  public static void beforeClass()
  {
    clearOldTestData();
    insertTestData();
  }

  protected static void clearOldTestData()
  {
    deleteAll(Employee.TABLE_NAME, Phone.TABLE_NAME, EMail.TABLE_NAME);
  }

  protected static void insertTestData()
  {
    insertAll(testEMails, testPhones, testEmployees);
  }
}
