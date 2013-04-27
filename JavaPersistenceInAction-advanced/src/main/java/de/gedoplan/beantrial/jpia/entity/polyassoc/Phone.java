package de.gedoplan.beantrial.jpia.entity.polyassoc;

import de.gedoplan.baselibs.persistence.entity.StringIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = Phone.TABLE_NAME)
public class Phone extends StringIdEntity implements Contact
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_PHONE";

  /**
   * Mail-Adresse.
   */
  private String             phoneNumber;

  /**
   * Konstruktor.
   */
  protected Phone()
  {
  }

  /**
   * Konstruktor.
   * 
   * @param id
   * @param phoneNumber
   */
  protected Phone(String id, String phoneNumber)
  {
    super(id);
    this.phoneNumber = phoneNumber;
  }

  /**
   * Wert liefern: {@link #phoneNumber}.
   * 
   * @return Wert
   */
  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }

  /**
   * Wert setzen: {@link #phoneNumber}.
   * 
   * @param phoneNumber Wert
   */
  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }
}
