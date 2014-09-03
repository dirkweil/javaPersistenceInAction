package de.gedoplan.beantrial.jpia.entity;

import de.gedoplan.baselibs.persistence.entity.GeneratedIntegerIdEntity;
import de.gedoplan.baselibs.utils.constraint.NotEmpty;
import de.gedoplan.baselibs.utils.constraint.ValidMailAddress;
import de.gedoplan.baselibs.utils.constraint.ValidTelNumber;
import de.gedoplan.baselibs.utils.validator.TelNumberValidator;
import de.gedoplan.beantrial.jpia.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OrderColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

//TALKABOUT A1) Person ist einfache Entity, hat aber zwei Lazy-Attribute

/**
 * Entity-Klasse für Personen.
 *
 * @author dw
 */
@Entity(name = "Person")
@Access(AccessType.FIELD)
@Table(name = Person.TABLE_NAME)
public class Person extends GeneratedIntegerIdEntity implements Comparable<Person>
{
  /**
   * Name der Haupt-Tabelle.
   */
  public static final String TABLE_NAME                = "JPIA_PERSON";

  /**
   * Name der Tabelle für Telefonnummern.
   */
  public static final String TABLE_NAME_TELEFONNUMMERN = "JPIA_PERSON_TEL";

  /**
   * Name der Tabelle für Hobbies.
   */
  public static final String TABLE_NAME_HOBBIES        = "JPIA_PERSON_HOBBY";

  /**
   * Name.
   */
  @NotNull
  @NotEmpty
  @Column(name = "NAME")
  private String             name;

  /**
   * Vorname.
   */
  @NotEmpty
  @Column(name = "VORNAME")
  private String             vorname;

  /**
   * (Privat-)Adresse.
   */
  @Valid
  private volatile Adresse   adresse;

  /**
   * (Private) Telefonnummern inklusive Landesvorwahl (Format wie +49 521 20889-10). Dies ist eine Liste, in der die primäre
   * Telefonnummer als erste eingetragen ist.
   */
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = TABLE_NAME_TELEFONNUMMERN)
  @Column(name = "TEL")
  @OrderColumn(name = "SORT_IDX")
  private List<String>       telefonNummern;

  /**
   * BV-Validierungsmethode für {@link #telefonNummern}.
   *
   * @return <code>true</code>, wenn alle Telefonnummern valide sind
   */
  @AssertTrue(message = ValidTelNumber.MESSAGE)
  protected boolean isTelefonNummernValid()
  {
    if (this.telefonNummern != null)
    {
      for (String telNo : this.telefonNummern)
      {
        if (!TelNumberValidator.isValid(telNo))
        {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * (Private) E-Mail-Adresse.
   */
  @ValidMailAddress
  @Column(name = "MAIL")
  private String       mailAdresse;

  /**
   * Hobbies.
   */
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = TABLE_NAME_HOBBIES)
  @Column(name = "HOBBY")
  @OrderColumn(name = "SORT_IDX")
  private List<String> hobbies;

  /**
   * Kostruktor.
   *
   * @param name Name
   * @param vorname Vorname
   */
  public Person(String name, String vorname)
  {
    this.name = name;
    this.vorname = vorname;

    this.adresse = new Adresse();

    this.telefonNummern = new ArrayList<>();
    this.hobbies = new ArrayList<>();
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
   * @param subName Wert
   */
  public void setVorname(String subName)
  {
    this.vorname = subName;
  }

  /**
   * Wert liefern: {@link #adresse}.
   *
   * @return Wert
   */
  public Adresse getAdresse()
  {
    /*
     * Die Spec sagt nicht eindeutig, ob ein Embeddable, dessen Attribute sämtlich null sind, instanziert wird. Um NPEs aus dem
     * Weg zu gehen, wird hier sichergestellt, dass die Adresse erzeugt wurde. Dies geschieht nach dem Double Check Lock Idiom von
     * Joshua Bloch. this.adresse ist dazu volatile!
     */
    Adresse tmp = this.adresse;
    if (tmp == null)
    {
      synchronized (this)
      {
        tmp = this.adresse;
        if (tmp == null)
        {
          tmp = new Adresse();
          this.adresse = tmp;
        }
      }
    }
    return tmp;
  }

  /**
   * Wert liefern: {@link #telefonNummern}.
   *
   * @return Wert
   */
  public List<String> getTelefonNummern()
  {
    return this.telefonNummern;
  }

  /**
   * Telefonnummern zeilenweise in einem String liefern.
   *
   * @return Telefonnummern, getrennt durch '\n'
   */
  @Transient
  public String getTelefonNummernAsString()
  {
    return ListUtil.toString(this.telefonNummern);
  }

  /**
   * Telefonnummern setzen anhand eines Strings, in dem die Nummern zeilenweise enthalten sind.
   *
   * @param s Telefonnummern, getrennt durch '\n'
   */
  public void setTelefonNummernAsString(String s)
  {
    this.telefonNummern = ListUtil.toList(s);
  }

  /**
   * Wert liefern: {@link #mailAdresse}.
   *
   * @return Wert
   */
  public String getMailAdresse()
  {
    return this.mailAdresse;
  }

  /**
   * Wert setzen: {@link #mailAdresse}.
   *
   * @param mailAdresse Wert
   */
  public void setMailAdresse(String mailAdresse)
  {
    this.mailAdresse = mailAdresse;
  }

  /**
   * Wert liefern: {@link #hobbies}.
   *
   * @return Wert
   */
  public List<String> getHobbies()
  {
    return this.hobbies;
  }

  /**
   * Hobbies zeilenweise in einem String liefern.
   *
   * @return Hobbies, getrennt durch '\n'
   */
  @Transient
  public String getHobbiesAsString()
  {
    return ListUtil.toString(this.hobbies);
  }

  /**
   * Hobbies setzen anhand eines Strings, in dem die Hobbies zeilenweise enthalten sind.
   *
   * @param s Hobbies, getrennt durch '\n'
   */
  public void setHobbiesAsString(String s)
  {
    this.hobbies = ListUtil.toList(s);
  }

  /**
   * {@inheritDoc}
   *
   * @see de.gedoplan.baselibs.persistence.entity.SingleIdEntity#toString()
   */
  @Override
  public String toString()
  {
    String toString = this.name;
    if (this.vorname != null)
    {
      toString += ", " + this.vorname;
    }
    return toString;
  }

  /**
   * Konstruktor für JPA-interne Zwecke.
   */
  protected Person()
  {
  }

  /**
   * {@inheritDoc}
   *
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(Person o)
  {
    return this.name.compareTo(o.name);
  }

  /**
   * Leeres Adressobjekt vor dem Speichern durch <code>null</code> ersetzen. Hierdurch wird verhindert, dass durch das Late Init
   * in {@link #getAdresse()} bei unverändert leerer Adresse eine Änderung in die DB geschrieben wird.
   */
  @PrePersist
  @PreUpdate
  private void preSave()
  {
    if (this.adresse != null && this.adresse.isNull())
    {
      this.adresse = null;
    }
  }
}
