package de.gedoplan.beantrial.jpia.entity.convert;

import de.gedoplan.beantrial.jpia.TestBase;

import org.junit.BeforeClass;

public abstract class ConvertTestBase extends TestBase
{
  protected static Projekt   testProjekt1      = new Projekt("0808.07", false, BudgetTyp.AUFWAND, Taetigkeit.PROGRAMMIERUNG);
  protected static Projekt   testProjekt2      = new Projekt("1202.02", true, BudgetTyp.FESTPREIS, Taetigkeit.BERATUNG);
  // Achtung: Projekte müssen nach ihrer Nummer geordnet sein
  protected static Projekt[] testProjekte      = { testProjekt1, testProjekt2 };
  protected static Projekt[] testProjekteAktiv = { testProjekt2 };

  @BeforeClass
  public static void beforeClass()
  {
    clearOldTestData();
    insertTestData();
  }

  protected static void clearOldTestData()
  {
    deleteAll(Projekt.TABLE_NAME);
  }

  protected static void insertTestData()
  {
    insertAll(testProjekte);
  }
}
