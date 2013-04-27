package de.gedoplan.beantrial.jpia.openjpa;

import de.gedoplan.beantrial.jpia.entity.performance.Group;
import de.gedoplan.beantrial.jpia.entity.performance.User;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.FetchPlan;
import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.openjpa.persistence.OpenJPAPersistence;

public final class UserGroupPermissionConfigurator
{
  public static void initFetchPlan(EntityManager entityManager)
  {
    try
    {
      OpenJPAEntityManager openJPAEntityManager = OpenJPAPersistence.cast(entityManager);
      FetchPlan fetchPlan = openJPAEntityManager.getFetchPlan();
      fetchPlan.addField(User.class, "groups");
      fetchPlan.addField(Group.class, "permissions");
    }
    catch (ClassCastException e)
    {
      // not using OpenJPA; ignore
    }
  }

  private UserGroupPermissionConfigurator()
  {
  }
}
