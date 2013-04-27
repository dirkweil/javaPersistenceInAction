package de.gedoplan.beantrial.jpia.entity.compositeid;

import de.gedoplan.baselibs.persistence.entity.SingleIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Entity-Klasse für Etagen (in Gebäuden).
 * 
 * Diese Klasse verdeutlicht den Aufbau einer mehrteiligen ID als EmbeddedId, in der ein Teil eine Relation zu einer anderen
 * Entity ist.
 * 
 * @author dw
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = Etage.TABLE_NAME)
public class Etage extends SingleIdEntity<EtageId>
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_ETAGE";

  /**
   * ID.
   */
  @EmbeddedId
  private EtageId            id;

  @MapsId("gebaeudeId")
  @ManyToOne
  private Gebaeude           gebaeude;

  /**
   * {@inheritDoc}
   * 
   * @see de.gedoplan.baselibs.persistence.entity.SingleIdEntity#getId()
   */
  @Override
  public EtageId getId()
  {
    return this.id;
  }

  /**
   * Konstruktor.
   * 
   * @param gebaeude Gebäude
   * @param stock Stockwerk-Nr.
   */
  public Etage(Gebaeude gebaeude, int stock)
  {
    this.id = new EtageId(gebaeude.getId(), stock);
    this.gebaeude = gebaeude;
  }

  /**
   * Konstruktor (für interne Zwecke).
   */
  protected Etage()
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
