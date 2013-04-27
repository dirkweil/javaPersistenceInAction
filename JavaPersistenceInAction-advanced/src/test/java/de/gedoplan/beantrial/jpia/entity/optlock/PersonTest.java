package de.gedoplan.beantrial.jpia.entity.optlock;

import junit.framework.Assert;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import org.junit.Ignore;
import org.junit.Test;

public class PersonTest extends OptLockTestBase
{
  @Test
  @Ignore
  public void showPersonen()
  {
    System.out.println("----- showPersonen -----");
    for (Person person : this.entityManager.createQuery("select p from Person p", Person.class).getResultList())
    {
      System.out.println(person);
    }
  }

  @Test
  @Ignore
  public void testTestData()
  {
    List<Person> personen = this.entityManager.createQuery("select p from Person p", Person.class).getResultList();
    Assert.assertEquals("Anzahl Personen falsch", testPersonen.length, personen.size());
  }

  @Test
  public void testConcurentUpdate()
  {
    Person person = this.entityManager.createQuery("select p from Person p where p.name=:name", Person.class).setParameter("name", testPerson1.getName()).getSingleResult();

    EntityManager entityManager2 = getEntityManagerFactory().createEntityManager();
    entityManager2.getTransaction().begin();
    Person person2 = entityManager2.find(Person.class, person.getId());

    person.setVorname("Sepp");
    this.entityManager.getTransaction().commit();

    person2.setName("Müller");
    try
    {
      entityManager2.getTransaction().commit();
    }
    catch (PersistenceException e)
    {
      if (isOptimisticLockException(e))
      {
        return;
      }

      Assert.assertEquals("Falsche Exception", OptimisticLockException.class, e);
    }

    Assert.fail("OptimisticLockException nicht geworfen");
  }

  private static boolean isOptimisticLockException(Throwable e)
  {
    if (e instanceof OptimisticLockException)
    {
      return true;
    }

    e = e.getCause();
    if (e == null)
    {
      return false;
    }

    return isOptimisticLockException(e);
  }

}
