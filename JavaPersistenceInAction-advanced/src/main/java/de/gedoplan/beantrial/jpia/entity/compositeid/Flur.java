package de.gedoplan.beantrial.jpia.entity.compositeid;

import de.gedoplan.baselibs.persistence.entity.SingleIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entity-Klasse für Flure (in Gebäuden).
 * 
 * Diese Klasse verdeutlicht wie {@link Fluegel} den Aufbau einer mehrteiligen ID mit IdClass. Hier sind allerdings zwei Id-Teile
 * Relationen zu anderen Entities, wobei diese Relationen überlappend sind in dem Sinne, dass die dazu gehörenden IDs ein
 * gemeinsames DB-Feld referenzieren.
 * 
 * @author dw
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = Flur.TABLE_NAME)
@IdClass(FlurId.class)
public class Flur extends SingleIdEntity<FlurId>
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_FLUR";

  /**
   * ID des Gebäudes.
   */
  @Id
  @ManyToOne
  private Gebaeude           gebaeude;

  /**
   * Gebäudeflügel-Kennzeichen.
   */
  @Id
  @Column(name = "FLUEGEL_KZ")
  private String             fluegelKz;

  /**
   * Stockwerk-Nummer.
   */
  @Id
  private int                stock;

  @Transient
  private FlurId             id;

  /**
   * Flügel.
   * 
   * Diese Relation ist vollständig Teil der ID und daher nur lesend ausgeführt.
   */
  @ManyToOne
  @JoinColumns({ @JoinColumn(name = "GEBAEUDE_ID", referencedColumnName = "GEBAEUDE_ID", insertable = false, updatable = false),
      @JoinColumn(name = "FLUEGEL_KZ", referencedColumnName = "FLUEGEL_KZ", insertable = false, updatable = false) })
  private Fluegel            fluegel;

  /**
   * Etage.
   * 
   * Diese Relation ist vollständig Teil der ID und daher nur lesend ausgeführt.
   */
  @ManyToOne
  @JoinColumns({ @JoinColumn(name = "GEBAEUDE_ID", referencedColumnName = "GEBAEUDE_ID", insertable = false, updatable = false),
      @JoinColumn(name = "STOCK", referencedColumnName = "STOCK", insertable = false, updatable = false) })
  private Etage              etage;

  /**
   * {@inheritDoc}
   * 
   * @see de.gedoplan.baselibs.persistence.entity.SingleIdEntity#getId()
   */
  @Override
  public FlurId getId()
  {
    if (this.id == null)
    {
      this.id = new FlurId(this.gebaeude.getId(), this.fluegelKz, this.stock);
    }
    return this.id;
  }

  /**
   * Konstruktor.
   * 
   * @param gebaeude Gebäude
   * @param fluegelKz Gebäudeflügel-Kennzeichen
   */
  public Flur(Gebaeude gebaeude, Fluegel fluegel, Etage etage)
  {
    this.gebaeude = gebaeude;
    this.fluegel = fluegel;
    this.fluegelKz = fluegel.getId().getFluegelKz();
    this.etage = etage;
    this.stock = etage.getId().getStock();
  }

  /**
   * Konstruktor (für interne Zwecke).
   */
  protected Flur()
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
   * Wert liefern: {@link #etage}.
   * 
   * @return Wert
   */
  public Etage getEtage()
  {
    return this.etage;
  }
}
