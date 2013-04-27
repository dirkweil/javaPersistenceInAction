package de.gedoplan.beantrial.jpia.entity.compositeid;

import de.gedoplan.baselibs.persistence.entity.SingleIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Entity-Klasse für Räume.
 * 
 * Diese Klasse verdeutlicht den Aufbau von Relationen, deren Foreign Keys mit dem Primary Key überlappen - hier in Form der
 * DB-Spalte GEBAUDE_ID, die sowohl Teil des PKs von Raum ist als ach Teil der FKs für die Relationen zu {@link Fluegel} und
 * {@link Etage}.
 * 
 * @author dw
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = Raum.TABLE_NAME)
public class Raum extends SingleIdEntity<RaumId>
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_RAUM";

  /**
   * ID.
   */
  @EmbeddedId
  private RaumId             id;

  /**
   * Gebäude.
   * 
   * Dies ist Teil der ID.
   */
  @MapsId("gebaeudeId")
  @ManyToOne
  private Gebaeude           gebaeude;

  /**
   * Flügel.
   * 
   * Hierin ist auch das Gebäude enthalten, was bereits ID-Anteil ist. Die JoinColumn ist daher nur lesend eingestellt.
   */
  @ManyToOne
  @JoinColumns({ @JoinColumn(name = "GEBAEUDE_ID", referencedColumnName = "GEBAEUDE_ID", insertable = false, updatable = false),
      @JoinColumn(name = "FLUEGEL_KZ", referencedColumnName = "FLUEGEL_KZ", insertable = false, updatable = false) })
  private Fluegel            fluegel;

  /**
   * Flügel-Kennzeichen.
   * 
   * Dieses Feld ist Teil des Flügels und nur deshalb explizit notwenig, da einige Provider (u. a. Hibernate) keine Mischung von
   * normalen und nur lesenden JoinColumns erlauben. Andernfalls (bspw. bei EclipseLink) kann dieses Feld entfallen und die
   * entsprechende JoinColumn mit insertable=true, updatable=true eingesellt werden.
   */
  @SuppressWarnings("unused")
  @Column(name = "FLUEGEL_KZ")
  private String             fluegelKz;

  /**
   * Etage.
   * 
   * Hierin ist auch das Gebäude enthalten, was bereits ID-Anteil ist. Die JoinColumn ist daher nur lesend eingestellt.
   */
  @ManyToOne
  @JoinColumns({ @JoinColumn(name = "GEBAEUDE_ID", referencedColumnName = "GEBAEUDE_ID", insertable = false, updatable = false),
      @JoinColumn(name = "STOCK", referencedColumnName = "STOCK", insertable = false, updatable = false) })
  private Etage              etage;

  /**
   * Stock der Etage.
   * 
   * Dieses Feld ist Teil der Etage und nur deshalb explizit notwenig, da einige Provider (u. a. Hibernate) keine Mischung von
   * normalen und nur lesenden JoinColumns erlauben. Andernfalls (bspw. bei EclipseLink) kann dieses Feld entfallen und die
   * entsprechende JoinColumn mit insertable=true, updatable=true eingesellt werden.
   */
  @SuppressWarnings("unused")
  private int                stock;

  /**
   * {@inheritDoc}
   * 
   * @see de.gedoplan.baselibs.persistence.entity.SingleIdEntity#getId()
   */
  @Override
  public RaumId getId()
  {
    return this.id;
  }

  /**
   * Konstruktor.
   * 
   * @param gebaeude Gebäude
   * @param raumNummer Raumnummer
   */
  public Raum(Gebaeude gebaeude, String raumNummer)
  {
    this.id = new RaumId(gebaeude.getId(), raumNummer);
    this.gebaeude = gebaeude;
  }

  /**
   * Konstruktor.
   * 
   * @param gebaeude Gebäude
   * @param raumNummer Raumnummer
   * @param fluegel Flügel
   * @param etage Etage
   */
  public Raum(Gebaeude gebaeude, String raumNummer, Fluegel fluegel, Etage etage)
  {
    this(gebaeude, raumNummer);

    setFluegel(fluegel);
    setEtage(etage);
  }

  /**
   * Konstruktor (für interne Zwecke).
   */
  protected Raum()
  {
  }

  /**
   * Wert liefern: {@link #gebaeude}.
   * 
   * @return Wert
   */
  public Gebaeude getGebaeude()
  {
    return this.gebaeude;
  }

  /**
   * Wert liefern: {@link #fluegel}.
   * 
   * @return Wert
   */
  public Fluegel getFluegel()
  {
    return this.fluegel;
  }

  /**
   * Wert setzen: {@link #fluegel}.
   * 
   * @param fluegel Wert
   */
  public void setFluegel(Fluegel fluegel)
  {
    if (fluegel != null)
    {
      if (!fluegel.getGebaeude().equals(this.gebaeude))
      {
        throw new IllegalArgumentException("Fluegel in falschem Gebaeude");
      }

      this.fluegelKz = fluegel.getId().getFluegelKz();
    }

    this.fluegel = fluegel;
  }

  /**
   * Wert liefern: {@link #etage}.
   * 
   * @return Wert
   */
  public Etage getEtage()
  {
    return this.etage;
  }

  /**
   * Wert setzen: {@link #etage}.
   * 
   * @param etage Wert
   */
  public void setEtage(Etage etage)
  {
    if (etage != null)
    {
      if (!etage.getGebaeude().equals(this.gebaeude))
      {
        throw new IllegalArgumentException("Etage in falschem Gebaeude");
      }

      this.stock = etage.getId().getStock();
    }

    this.etage = etage;
  }

}
