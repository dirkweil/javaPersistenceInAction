package de.gedoplan.beantrial.jpia.entity.convert;

import junit.framework.Assert;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Ignore;
import org.junit.Test;

public class ProjektTest extends ConvertTestBase
{
  @Test
  @Ignore
  public void showProjekte()
  {
    System.out.println("----- showProjekte -----");
    for (Projekt Projekt : this.entityManager.createQuery("select p from Projekt p", Projekt.class).getResultList())
    {
      System.out.println(Projekt.toDebugString());
    }
  }

  @Test
  // @Ignore
  public void testProjekte()
  {
    System.out.println("----- testProjekte -----");
    TypedQuery<Projekt> query = this.entityManager.createQuery("select p from Projekt p order by p.nummer", Projekt.class);
    assertResultEquals(query, testProjekte);
  }

  @Test
  // @Ignore
  public void testProjekteAktiv()
  {
    System.out.println("----- testProjekteAktiv -----");
    TypedQuery<Projekt> query = this.entityManager.createQuery("select p from Projekt p where p.aktiv=TRUE order by p.nummer", Projekt.class);
    assertResultEquals(query, testProjekteAktiv);
  }

  private void assertResultEquals(TypedQuery<Projekt> query, Projekt[] expected)
  {
    List<Projekt> projekte = query.getResultList();
    Assert.assertEquals("Anzahl Projekte falsch", expected.length, projekte.size());
    for (int i = 0; i < expected.length; ++i)
    {
      Projekt testProjekt = expected[i];
      Projekt projekt = projekte.get(i);
      if (!testProjekt.deepEquals(projekt))
      {
        Assert.fail("Projekt nicht korrekt eingelesen: " + projekt.toDebugString());
      }
    }

  }

}
