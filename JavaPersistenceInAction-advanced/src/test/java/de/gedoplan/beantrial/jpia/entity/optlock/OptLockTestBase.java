package de.gedoplan.beantrial.jpia.entity.optlock;

import de.gedoplan.beantrial.jpia.TestBase;

import org.junit.BeforeClass;

public abstract class OptLockTestBase extends TestBase
{
  protected static Person   testPerson1  = new Person("Maier", "Gerd");
  protected static Person   testPerson2  = new Person("Mustermann", "Max");
  protected static Person[] testPersonen = { testPerson1, testPerson2 };

  @BeforeClass
  public static void beforeClass()
  {
    clearOldTestData();
    insertTestData();
  }

  protected static void clearOldTestData()
  {
    deleteAll(Person.TABLE_NAME);
  }

  protected static void insertTestData()
  {
    insertAll(testPersonen);
  }
}
