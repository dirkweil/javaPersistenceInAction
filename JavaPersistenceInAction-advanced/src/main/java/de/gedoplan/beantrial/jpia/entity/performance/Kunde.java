package de.gedoplan.beantrial.jpia.entity.performance;

import de.gedoplan.baselibs.persistence.entity.GeneratedLongIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Kunde.
 * 
 * @author dw
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = Kunde.TABLE_NAME)
public class Kunde extends GeneratedLongIdEntity
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_KUNDE";
  /**
   * Name.
   */
  private String             name;

  /**
   * Vorname.
   */
  private String             vorname;

  /**
   * Land.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  private Land               land;

  /**
   * Konstruktor.
   */
  public Kunde()
  {
  }

  /**
   * Konstruktor.
   * 
   * @param name Name
   * @param land Land
   */
  public Kunde(String name, Land land)
  {
    this.name = name;

    this.land = land;
  }

  /**
   * Attribut liefern: {@link #name}.
   * 
   * @return aktueller Wert des Attributs
   */
  public String getName()
  {
    return this.name;
  }

  /**
   * Attribut setzen: {@link #name}.
   * 
   * @param name neuer Wert des Attributs
   */

  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Attribut liefern: {@link #vorname}.
   * 
   * @return aktueller Wert des Attributs
   */
  public String getVorname()
  {
    return this.vorname;
  }

  /**
   * Attribut setzen: {@link #vorname}.
   * 
   * @param vorname neuer Wert des Attributs
   */

  public void setVorname(String vorname)
  {
    this.vorname = vorname;
  }

  /**
   * Attribut liefern: {@link #land}.
   * 
   * @return aktueller Wert des Attributs
   */
  public Land getLand()
  {
    return this.land;
  }

  /**
   * Attribut setzen: {@link #land}.
   * 
   * @param land neuer Wert des Attributs
   */

  public void setLand(Land land)
  {
    this.land = land;
  }

  public boolean deepEquals(Kunde other)
  {
    if (!this.equals(other))
    {
      return false;
    }

    if (this.land == null)
    {
      if (other.land != null)
      {
        return false;
      }
    }
    else if (!this.land.equals(other.land))
    {
      return false;
    }
    if (this.name == null)
    {
      if (other.name != null)
      {
        return false;
      }
    }
    else if (!this.name.equals(other.name))
    {
      return false;
    }
    if (this.vorname == null)
    {
      if (other.vorname != null)
      {
        return false;
      }
    }
    else if (!this.vorname.equals(other.vorname))
    {
      return false;
    }
    return true;
  }

}
