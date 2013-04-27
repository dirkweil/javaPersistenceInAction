package de.gedoplan.beantrial.jpia.entity.inherit;

import junit.framework.Assert;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class VehicleTest extends InheritTestBase
{
  @Test
  @Ignore
  public void showVehicles()
  {
    System.out.println("----- showVehicles -----");
    for (Vehicle vehicle : this.entityManager.createQuery("select v from Vehicle v", Vehicle.class).getResultList())
    {
      System.out.println(vehicle);
    }
  }

  @Test
  // @Ignore
  public void testfindVehicles()
  {
    testFindEntries(Vehicle.class, testVehicles);
  }

  @Test
  // @Ignore
  public void testFindCars()
  {
    testFindEntries(Car.class, testCars);
  }

  @Test
  // @Ignore
  public void testFindLorries()
  {
    testFindEntries(Lorry.class, testLorries);
  }

  @Test
  // @Ignore
  public void testFindShips()
  {
    testFindEntries(Ship.class, testShips);
  }

  public <E extends Vehicle> void testFindEntries(Class<E> entityClass, E[] testEntities)
  {
    List<E> entities = this.entityManager.createQuery("select v from " + entityClass.getSimpleName() + " v order by v.id", entityClass).getResultList();
    Assert.assertEquals("Anzahl Einträge des Typs " + entityClass.getSimpleName() + " falsch", testEntities.length, entities.size());

    for (int i = 0; i < testEntities.length; ++i)
    {
      Assert.assertEquals(entityClass.getSimpleName() + "[" + i + "] falsch", testEntities[i], entities.get(i));
    }
  }

}
