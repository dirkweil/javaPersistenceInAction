package de.gedoplan.beantrial.jpia.entity.performance;

import de.gedoplan.beantrial.jpia.openjpa.UserGroupPermissionConfigurator;

import junit.framework.Assert;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.junit.Ignore;
import org.junit.Test;

public class UserTest extends PerformanceTestBase
{
  @Test
  @Ignore
  public void showUser()
  {
    System.out.println("----- showUser -----");
    for (User user : this.entityManager.createQuery("select u from User u", User.class).getResultList())
    {
      System.out.println(user.toDebugString());
      for (Group group : user.getGroups())
      {
        System.out.println("  " + group.toDebugString());
        for (Permission permission : group.getPermissions())
        {
          System.out.println("    " + permission.toDebugString());
        }
      }
    }
  }

  @Test
  // @Ignore
  public void testFindUser()
  {
    System.out.println("----- testFindUser -----");

    // Nur für OpenJPA aktiv: Query EAGER machen
    UserGroupPermissionConfigurator.initFetchPlan(this.entityManager);

    TypedQuery<User> query = this.entityManager.createQuery("select u from User u", User.class);
    assertCorrectResult(query);
  }

  @Test
  @Ignore
  // Achtung: Diese Testvariante macht nur Sinn *OHNE* Provider-spezifische Fetch-Annotationen in User und Group!
  public void testFindUserJoinFetch()
  {
    System.out.println("----- testFindUserJoinFetch -----");

    CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
    Root<User> u = criteriaQuery.from(User.class);
    u.fetch(User_.groups, JoinType.LEFT).fetch(Group_.permissions, JoinType.LEFT);
    criteriaQuery.select(u).distinct(true);
    TypedQuery<User> query = this.entityManager.createQuery(criteriaQuery);
    assertCorrectResult(query);
  }

  private void assertCorrectResult(TypedQuery<User> query)
  {
    long start = System.nanoTime();

    List<User> users = query.getResultList();
    Assert.assertEquals("Anzahl User falsch", testUsers.length, users.size());
    for (User testuser : testUsers)
    {
      int i = users.indexOf(testuser);
      if (i < 0)
      {
        Assert.fail(testuser + " fehlt in Query-Ergebnis");
      }

      User user = users.get(i);

      if (!testuser.deepEquals(user))
      {
        Assert.fail("Testuser " + testuser.toDebugString() + " nicht korrekt gelesen: " + user.toDebugString());
      }
    }

    long stop = System.nanoTime();
    System.out.println(String.format("Used %,d ns\n", (stop - start)));
  }

}
