package de.gedoplan.beantrial.jpia.entity.polyassoc;

import de.gedoplan.baselibs.persistence.entity.GeneratedLongIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = Employee.TABLE_NAME)
public class Employee extends GeneratedLongIdEntity
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_EMPLOYEE";

  /**
   * Name.
   */
  private String             name;

  /**
   * Vorname.
   */
  private String             vorname;

  /**
   * Kontakt.
   */
  // EclipseLink: Mapping der polymorphen Assoziation mit Hilfe einer Diskriminatorspalte
  @org.eclipse.persistence.annotations.VariableOneToOne(discriminatorColumn = @DiscriminatorColumn(name = "CONTACT_TYPE"), discriminatorClasses = {
      @org.eclipse.persistence.annotations.DiscriminatorClass(discriminator = "E", value = EMail.class),
      @org.eclipse.persistence.annotations.DiscriminatorClass(discriminator = "P", value = Phone.class) })
  //
  // Hibernate: Mapping der polymorphen Assoziation mit Hilfe einer Diskriminatorspalte
  @org.hibernate.annotations.Any(metaColumn = @Column(name = "CONTACT_TYPE"), fetch = FetchType.EAGER)
  @org.hibernate.annotations.AnyMetaDef(idType = "string", metaType = "string", metaValues = { @org.hibernate.annotations.MetaValue(value = "E", targetEntity = EMail.class),
      @org.hibernate.annotations.MetaValue(value = "P", targetEntity = Phone.class) })
  @JoinColumn(name = "CONTACT_ID")
  private Contact            contact;

  /**
   * Konstruktor.
   * 
   * @param name
   * @param vorname
   */
  public Employee(String name, String vorname, Contact contact)
  {
    this.name = name;
    this.vorname = vorname;
    this.contact = contact;
  }

  /**
   * Konstruktor.
   */
  protected Employee()
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

  /**
   * Wert liefern: {@link #vorname}.
   * 
   * @return Wert
   */
  public String getVorname()
  {
    return this.vorname;
  }

  /**
   * Wert setzen: {@link #vorname}.
   * 
   * @param vorname Wert
   */
  public void setVorname(String vorname)
  {
    this.vorname = vorname;
  }

  /**
   * Wert liefern: {@link #contact}.
   * 
   * @return Wert
   */
  public Contact getContact()
  {
    return this.contact;
  }

  /**
   * Wert setzen: {@link #contact}.
   * 
   * @param contact Wert
   */
  public void setContact(Contact contact)
  {
    this.contact = contact;
  }

}
