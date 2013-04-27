package de.gedoplan.beantrial.jpia.entity.performance;

import de.gedoplan.baselibs.persistence.entity.StringIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = Permission.TABLE_NAME)
public class Permission extends StringIdEntity
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_PERMISSION";

  /**
   * Name.
   */
  private String             description;

  /**
   * Konstruktor.
   * 
   * @param id
   * @param description
   */
  public Permission(String id, String description)
  {
    super(id);
    this.description = description;
  }

  /**
   * Konstruktor.
   */
  protected Permission()
  {
  }

  /**
   * Wert liefern: {@link #description}.
   * 
   * @return Wert
   */
  public String getDescription()
  {
    return this.description;
  }

  /**
   * Wert setzen: {@link #description}.
   * 
   * @param description Wert
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

  public boolean deepEquals(Permission otherPermission)
  {
    if (!this.equals(otherPermission))
    {
      return false;
    }

    if (this.description == null)
    {
      if (otherPermission.description != null)
      {
        return false;
      }
    }
    else if (!this.description.equals(otherPermission.description))
    {
      return false;
    }

    return true;
  }

}
