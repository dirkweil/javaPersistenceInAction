package de.gedoplan.beantrial.jpia.entity.inherit;

import de.gedoplan.beantrial.jpia.TestBase;

import org.junit.BeforeClass;

public abstract class InheritTestBase extends TestBase
{
  protected static Car       testCar1     = new Car("C.01", "Smart", 3);
  protected static Car       testCar2     = new Car("C.02", "Golf", 5);
  protected static Lorry     testLorry1   = new Lorry("L.01", "Actros", 2, 35);
  protected static Ship      testShip1    = new Ship("S.01", "Norwegian Breakaway", 146600);
  protected static Ship      testShip2    = new Ship("S.02", "Disney Fantasy", 130000);
  // Achtung: testVehicles müssen aufsteigend bzgl. der ID angeordnet sein
  protected static Car[]     testCars     = { testCar1, testCar2, testLorry1 };
  protected static Lorry[]   testLorries  = { testLorry1 };
  protected static Ship[]    testShips    = { testShip1, testShip2 };
  protected static Vehicle[] testVehicles = { testCar1, testCar2, testLorry1, testShip1, testShip2 };

  @BeforeClass
  public static void beforeClass()
  {
    clearOldTestData();
    insertTestData();
  }

  protected static void clearOldTestData()
  {
    deleteAll(Vehicle.TABLE_NAME);
  }

  protected static void insertTestData()
  {
    insertAll(testVehicles);
  }
}
