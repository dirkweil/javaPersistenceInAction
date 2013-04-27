package de.gedoplan.beantrial.jpia.entity.compositeid;

import java.io.Serializable;

import org.junit.Ignore;
import org.junit.Test;

public class GebaeudeTest extends CompositeIdTestBase implements Serializable
{
  @Test
  // @Ignore
  public void showGebaeude()
  {
    System.out.println("----- showGebaeude -----");
    for (Gebaeude gebaeude : this.entityManager.createQuery("select g from Gebaeude g", Gebaeude.class).getResultList())
    {
      System.out.println(gebaeude);
    }
  }

  // @Test
  @Ignore
  public void showFluegel()
  {
    System.out.println("----- showFluegel -----");
    for (Fluegel fluegel : this.entityManager.createQuery("select f from Fluegel f", Fluegel.class).getResultList())
    {
      System.out.println(fluegel);
      System.out.println("  " + fluegel.getGebaeude());
    }
  }

  @Test
  // @Ignore
  public void showEtagen()
  {
    System.out.println("----- showEtagen -----");
    for (Etage etage : this.entityManager.createQuery("select e from Etage e", Etage.class).getResultList())
    {
      System.out.println(etage);
      System.out.println("  " + etage.getGebaeude());
    }
  }

  @Test
  // @Ignore
  public void showRaeume()
  {
    System.out.println("----- showRaeume -----");
    for (Raum raum : this.entityManager.createQuery("select r from Raum r", Raum.class).getResultList())
    {
      System.out.println(raum);
      System.out.println("  " + raum.getGebaeude());
      System.out.println("  " + raum.getFluegel());
      System.out.println("  " + raum.getEtage());
    }
  }

  @Test
  // @Ignore
  public void showFlure()
  {
    System.out.println("----- showFlure -----");
    for (Flur flur : this.entityManager.createQuery("select f from Flur f", Flur.class).getResultList())
    {
      System.out.println(flur);
      System.out.println("  " + flur.getGebaeude());
      System.out.println("  " + flur.getFluegel());
      System.out.println("  " + flur.getEtage());
    }
  }
}
