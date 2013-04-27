package de.gedoplan.beantrial.jpia.entity.performance;

import junit.framework.Assert;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;

public class LandTest extends PerformanceTestBase
{
  @Test
  // @Ignore
  public void testFindLaender1()
  {
    System.out.println("----- testFindLaender1 -----");

    // Vor weiterer Verarbeitung löschen
    entityManagerFactory.getCache().evictAll();

    runQuery();
  }

  @Test
  // @Ignore
  public void testFindLaender2()
  {
    System.out.println("----- testFindLaender2 -----");
    runQuery();
  }

  private void runQuery()
  {
    TypedQuery<Land> query = this.entityManager.createQuery("select l from Land l where l.name > 'D'", Land.class);
    query.setHint("eclipselink.cache-usage", "CheckCacheThenDatabase");
    List<Land> laender = query.getResultList();
    Assert.assertEquals("Anzahl Laender falsch", testLaender.length, laender.size());
    for (Land testLand : testLaender)
    {
      int i = laender.indexOf(testLand);
      if (i < 0)
      {
        Assert.fail(testLand + " fehlt in Query-Ergebnis");
      }
    }
  }

}
