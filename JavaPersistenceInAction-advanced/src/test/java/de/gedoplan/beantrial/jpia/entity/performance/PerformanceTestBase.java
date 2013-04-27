package de.gedoplan.beantrial.jpia.entity.performance;

import de.gedoplan.beantrial.jpia.TestBase;

import org.junit.BeforeClass;

public abstract class PerformanceTestBase extends TestBase
{
  // private static final int USER_COUNT = 520;
  // private static final int GROUP_COUNT = 4;
  private static final int      USER_COUNT      = 3;
  private static final int      GROUP_COUNT     = 2;
  private static final int      PERM_COUNT      = GROUP_COUNT * 10;

  protected static User[]       testUsers       = new User[USER_COUNT];
  protected static Group[]      testGroups      = new Group[GROUP_COUNT];
  protected static Permission[] testPermissions = new Permission[PERM_COUNT];
  static
  {
    for (int i = 0; i < testGroups.length; ++i)
    {
      testGroups[i] = new Group(String.format("Testgroup %02d", i));
    }

    for (int i = 0; i < testUsers.length; ++i)
    {
      testUsers[i] = new User(String.format("user%02d", i), String.format("Testuser %02d", i));

      testUsers[i].getGroups().add(testGroups[0]);

      if (i < testUsers.length / 2)
      {
        testUsers[i].getGroups().add(testGroups[1]);
      }
    }

    for (int i = 0; i < testPermissions.length; ++i)
    {
      testPermissions[i] = new Permission(String.format("perm%02d", i), String.format("Testpermission %02d", i));

      testGroups[i % testGroups.length].getPermissions().add(testPermissions[i]);
    }
  }

  public static final Land      testLandAt      = new Land("AT", "Österreich");
  public static final Land      testLandDe      = new Land("DE", "Deutschland");
  public static final Land      testLandCh      = new Land("CH", "Schweiz");
  public static final Land[]    testLaender     = { testLandAt, testLandDe, testLandCh };

  private static final int      KUNDE_COUNT     = 100;
  public static final Kunde[]   testKunden      = new Kunde[KUNDE_COUNT];
  static
  {
    for (int i = 0; i < KUNDE_COUNT; ++i)
    {
      testKunden[i] = new Kunde(String.format("testKunde %03d", i), testLaender[i % testLaender.length]);
    }
  }

  @BeforeClass
  public static void beforeClass()
  {
    System.out.println("----- Initialize test data -----");
    clearOldTestData();
    insertTestData();
    System.out.println("----- End of initialization -----");
  }

  protected static void clearOldTestData()
  {
    deleteAll(User.TABLE_NAME_GROUPS, User.TABLE_NAME, Permission.TABLE_NAME, Group.TABLE_NAME, Kunde.TABLE_NAME, Land.TABLE_NAME);
  }

  protected static void insertTestData()
  {
    insertAll(testUsers, testLaender, testKunden);
  }
}
