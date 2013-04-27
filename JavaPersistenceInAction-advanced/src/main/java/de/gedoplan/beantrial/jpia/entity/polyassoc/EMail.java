package de.gedoplan.beantrial.jpia.entity.polyassoc;

import de.gedoplan.baselibs.persistence.entity.StringIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = EMail.TABLE_NAME)
public class EMail extends StringIdEntity implements Contact
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_EMAIL";

  /**
   * Mail-Adresse.
   */
  private String             address;

  /**
   * Konstruktor.
   */
  protected EMail()
  {
  }

  /**
   * Konstruktor.
   * 
   * @param id
   * @param address
   */
  protected EMail(String id, String address)
  {
    super(id);
    this.address = address;
  }

  /**
   * Wert liefern: {@link #address}.
   * 
   * @return Wert
   */
  public String getAddress()
  {
    return this.address;
  }

  /**
   * Wert setzen: {@link #address}.
   * 
   * @param address Wert
   */
  public void setAddress(String address)
  {
    this.address = address;
  }
}
