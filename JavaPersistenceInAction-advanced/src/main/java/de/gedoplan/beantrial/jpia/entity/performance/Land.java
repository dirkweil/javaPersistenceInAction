package de.gedoplan.beantrial.jpia.entity.performance;

import de.gedoplan.baselibs.persistence.entity.StringIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Land.
 * 
 * @author dw
 */
@Entity
@Access(AccessType.FIELD)
// @Cacheable(true)
@Table(name = Land.TABLE_NAME)
public class Land extends StringIdEntity
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_LAND";

  /**
   * Name.
   */
  private String             name;

  /**
   * Konstruktor.
   * 
   * @param isoCode ISO-Code
   * @param name Name
   */
  public Land(String isoCode, String name)
  {
    super(isoCode);
    this.name = name;
  }

  /**
   * Konstruktor für JPA-interne Zwecke.
   */
  protected Land()
  {
  }

  /**
   * Wert liefern: {@link #name}.
   * 
   * @return Wert
   */
  public String getName()
  {
    return this.name;
  }

  /**
   * Wert setzen: {@link #name}.
   * 
   * @param name Wert
   */
  public void setName(String name)
  {
    this.name = name;
  }

}
