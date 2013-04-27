package de.gedoplan.beantrial.jpia.entity.performance;

import junit.framework.Assert;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Ignore;
import org.junit.Test;

public class KundeTest extends PerformanceTestBase
{
  @Test
  @Ignore
  public void showKunden()
  {
    System.out.println("----- showKunden -----");
    for (Kunde user : this.entityManager.createQuery("select k from Kunde k order by k.id", Kunde.class).getResultList())
    {
      System.out.println(user.toDebugString());
      System.out.println("  " + user.getLand().toDebugString());
    }
  }

  @Test
  // @Ignore
  public void testFindKunden()
  {
    System.out.println("----- testFindKunden -----");

    TypedQuery<Kunde> query = this.entityManager.createQuery("select k from Kunde k order by k.id", Kunde.class);
    assertCorrectResult(query);
  }

  private void assertCorrectResult(TypedQuery<Kunde> query)
  {
    long start = System.nanoTime();

    List<Kunde> kunden = query.getResultList();
    Assert.assertEquals("Anzahl Kunden falsch", testKunden.length, kunden.size());
    for (Kunde testKunde : testKunden)
    {
      int i = kunden.indexOf(testKunde);
      if (i < 0)
      {
        Assert.fail(testKunde + " fehlt in Query-Ergebnis");
      }

      Kunde kunde = kunden.get(i);

      if (!testKunde.deepEquals(kunde))
      {
        Assert.fail("Testkunde " + testKunde.toDebugString() + " nicht korrekt gelesen: " + kunde.toDebugString());
      }
    }

    long stop = System.nanoTime();
    System.out.println(String.format("Used %,d ns\n", (stop - start)));
  }

}
