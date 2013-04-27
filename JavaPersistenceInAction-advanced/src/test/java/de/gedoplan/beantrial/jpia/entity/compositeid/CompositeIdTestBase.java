package de.gedoplan.beantrial.jpia.entity.compositeid;

import de.gedoplan.beantrial.jpia.TestBase;

import org.junit.BeforeClass;

public abstract class CompositeIdTestBase extends TestBase
{
  private static Gebaeude   testGebaeudeStieghorsterStr60  = new Gebaeude("Stieghorster Str. 60, 33605 Bielefeld");
  private static Gebaeude   testGebaeudeBischofholerDamm89 = new Gebaeude("Bischofsholer Damm 89, 30173 Hannover");
  private static Gebaeude[] testGebaeude                   = { testGebaeudeStieghorsterStr60, testGebaeudeBischofholerDamm89 };

  private static Fluegel    testFluegelStieghorsterStr60_N = new Fluegel(testGebaeudeStieghorsterStr60, "N");
  private static Fluegel    testFluegelStieghorsterStr60_W = new Fluegel(testGebaeudeStieghorsterStr60, "W");
  private static Fluegel[]  testFluegel                    = { testFluegelStieghorsterStr60_N, testFluegelStieghorsterStr60_W };

  private static Etage      testEtageStieghorsterStr60_1   = new Etage(testGebaeudeStieghorsterStr60, 1);
  private static Etage      testEtageStieghorsterStr60_3   = new Etage(testGebaeudeStieghorsterStr60, 3);
  private static Etage[]    testEtagen                     = { testEtageStieghorsterStr60_1, testEtageStieghorsterStr60_3 };

  private static Raum       testRaumStieghorsterStr60_101  = new Raum(testGebaeudeStieghorsterStr60, "101", testFluegelStieghorsterStr60_N, testEtageStieghorsterStr60_1);
  private static Raum       testRaumStieghorsterStr60_102  = new Raum(testGebaeudeStieghorsterStr60, "102", testFluegelStieghorsterStr60_N, testEtageStieghorsterStr60_1);
  private static Raum       testRaumStieghorsterStr60_301  = new Raum(testGebaeudeStieghorsterStr60, "301", testFluegelStieghorsterStr60_N, testEtageStieghorsterStr60_3);
  private static Raum[]     testRaeume                     = { testRaumStieghorsterStr60_101, testRaumStieghorsterStr60_102, testRaumStieghorsterStr60_301 };

  private static Flur       testFlurStieghotsterStr60_N_1  = new Flur(testGebaeudeStieghorsterStr60, testFluegelStieghorsterStr60_N, testEtageStieghorsterStr60_1);
  private static Flur       testFlurStieghotsterStr60_N_3  = new Flur(testGebaeudeStieghorsterStr60, testFluegelStieghorsterStr60_N, testEtageStieghorsterStr60_3);
  private static Flur[]     testFlure                      = { testFlurStieghotsterStr60_N_1, testFlurStieghotsterStr60_N_3 };

  @BeforeClass
  public static void beforeClass()
  {
    clearOldTestData();
    insertTestData();
  }

  protected static void clearOldTestData()
  {
    deleteAll(Flur.TABLE_NAME, Raum.TABLE_NAME, Etage.TABLE_NAME, Fluegel.TABLE_NAME, Gebaeude.TABLE_NAME);
  }

  protected static void insertTestData()
  {
    insertAll(testGebaeude, testFluegel, testEtagen, testRaeume, testFlure);
  }
}
