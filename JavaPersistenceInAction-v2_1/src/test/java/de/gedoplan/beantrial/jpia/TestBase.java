package de.gedoplan.beantrial.jpia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

public abstract class TestBase
{
  private static EntityManagerFactory entityManagerFactory;
  protected EntityManager             entityManager;

  @Before
  public void before()
  {
    this.entityManager = getEntityManagerFactory().createEntityManager();
    this.entityManager.getTransaction().begin();
  }

  @After
  public void after()
  {
    try
    {
      this.entityManager.close();
    }
    catch (Exception e)
    {
      // ignore
    }
  }

  protected static EntityManagerFactory getEntityManagerFactory()
  {
    if (entityManagerFactory == null)
    {
      entityManagerFactory = Persistence.createEntityManagerFactory("test");
    }
    return entityManagerFactory;
  }

  protected static void deleteAll(String... tableName)
  {
    EntityManager entityManager = getEntityManagerFactory().createEntityManager();
    entityManager.getTransaction().begin();

    try
    {
      for (String t : tableName)
      {
        entityManager.createNativeQuery("delete from " + t).executeUpdate();
      }

      entityManager.getTransaction().commit();
    }
    finally
    {
      try
      {
        entityManager.close();
      }
      catch (Exception e)
      {
        // ignore
      }
    }
  }

  protected static void insertAll(Object[]... entities)
  {
    EntityManager entityManager = getEntityManagerFactory().createEntityManager();
    entityManager.getTransaction().begin();

    try
    {
      for (Object[] e : entities)
      {
        insertTestData(entityManager, e);
      }

      entityManager.getTransaction().commit();
    }
    finally
    {
      try
      {
        entityManager.close();
      }
      catch (Exception e)
      {
        // ignore
      }
    }
  }

  private static void insertTestData(EntityManager entityManager, Object[] entities)
  {
    for (Object entity : entities)
    {
      entityManager.persist(entity);
    }

  }

}
