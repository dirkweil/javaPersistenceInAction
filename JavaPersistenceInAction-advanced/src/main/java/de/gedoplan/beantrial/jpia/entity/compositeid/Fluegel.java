package de.gedoplan.beantrial.jpia.entity.compositeid;

import de.gedoplan.baselibs.persistence.entity.SingleIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entity-Klasse für Flügel (in Gebäuden).
 * 
 * Diese Klasse verdeutlicht den Aufbau einer mehrteiligen ID mit dazu redundanten Entity-Feldern, d. h. unter Einsatz von
 * IdClass. Ein Teil der Id ist wiederum eine Relation zu einer anderen Entity.
 * 
 * @author dw
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = Fluegel.TABLE_NAME)
@IdClass(FluegelId.class)
public class Fluegel extends SingleIdEntity<FluegelId>
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_FLUEGEL";

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

  @Transient
  private FluegelId          id;

  /**
   * {@inheritDoc}
   * 
   * @see de.gedoplan.baselibs.persistence.entity.SingleIdEntity#getId()
   */
  @Override
  public FluegelId getId()
  {
    if (this.id == null)
    {
      this.id = new FluegelId(this.gebaeude.getId(), this.fluegelKz);
    }
    return this.id;
  }

  /**
   * Konstruktor.
   * 
   * @param gebaeude Gebäude
   * @param fluegelKz Gebäudeflügel-Kennzeichen
   */
  public Fluegel(Gebaeude gebaeude, String fluegelKz)
  {
    this.gebaeude = gebaeude;
    this.fluegelKz = fluegelKz;
  }

  /**
   * Konstruktor (für interne Zwecke).
   */
  protected Fluegel()
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
}
