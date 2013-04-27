package de.gedoplan.beantrial.jpia.entity.convert;

import de.gedoplan.baselibs.persistence.entity.GeneratedIntegerIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Access(AccessType.FIELD)
@Table(name = Projekt.TABLE_NAME)
public class Projekt extends GeneratedIntegerIdEntity
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_PROJEKT";

  /**
   * Name.
   */
  private String             nummer;

  /**
   * Projekt aktiv?
   */
  // EclipseLink: Konverter Boolean <-> 'N','J' deklarieren
  @org.eclipse.persistence.annotations.ObjectTypeConverter(name = "JNConverter", dataType = String.class, conversionValues = {
      @org.eclipse.persistence.annotations.ConversionValue(dataValue = "J", objectValue = "true"), @org.eclipse.persistence.annotations.ConversionValue(dataValue = "N", objectValue = "false") })
  @org.eclipse.persistence.annotations.Convert("JNConverter")
  //
  // Hibernate: UserType für die Speicherung Boole'scher Werte als 'N' bzw. 'J' deklarieren
  @org.hibernate.annotations.Type(type = "de.gedoplan.beantrial.jpia.hibernate.JNType")
  //
  // OpenJPA: External Values 'N' und 'J' für Boole'sche Werte deklarieren
  @org.apache.openjpa.persistence.ExternalValues({ "true=J", "false=N" })
  @org.apache.openjpa.persistence.Type(String.class)
  //
  @Column(columnDefinition = "CHAR(1)")
  private Boolean            aktiv;

  private String             projektTyp;

  @Transient
  private BudgetTyp          budgetTyp;

  @Transient
  private Taetigkeit         taetigkeit;

  /**
   * Konstruktor.
   * 
   * @param nummer
   * @param aktiv
   * @param budgetTyp
   * @param taetigkeit
   */
  protected Projekt(String nummer, boolean aktiv, BudgetTyp budgetTyp, Taetigkeit taetigkeit)
  {
    this.nummer = nummer;
    this.aktiv = aktiv;
    this.budgetTyp = budgetTyp;
    this.taetigkeit = taetigkeit;
  }

  /**
   * Konstruktor.
   */
  protected Projekt()
  {
  }

  /**
   * Wert liefern: {@link #nummer}.
   * 
   * @return Wert
   */
  public String getNummer()
  {
    return this.nummer;
  }

  /**
   * Wert setzen: {@link #nummer}.
   * 
   * @param nummer Wert
   */
  public void setNummer(String nummer)
  {
    this.nummer = nummer;
  }

  /**
   * Wert liefern: {@link #aktiv}.
   * 
   * @return Wert
   */
  public boolean isAktiv()
  {
    return this.aktiv;
  }

  /**
   * Wert setzen: {@link #aktiv}.
   * 
   * @param aktiv Wert
   */
  public void setAktiv(boolean aktiv)
  {
    this.aktiv = aktiv;
  }

  /**
   * Wert liefern: {@link #budgetTyp}.
   * 
   * @return Wert
   */
  public BudgetTyp getBudgetTyp()
  {
    return this.budgetTyp;
  }

  /**
   * Wert setzen: {@link #budgetTyp}.
   * 
   * @param budgetTyp Wert
   */
  public void setBudgetTyp(BudgetTyp budgetTyp)
  {
    this.budgetTyp = budgetTyp;
  }

  /**
   * Wert liefern: {@link #taetigkeit}.
   * 
   * @return Wert
   */
  public Taetigkeit getTaetigkeit()
  {
    return this.taetigkeit;
  }

  /**
   * Wert setzen: {@link #taetigkeit}.
   * 
   * @param taetigkeit Wert
   */
  public void setTaetigkeit(Taetigkeit taetigkeit)
  {
    this.taetigkeit = taetigkeit;
  }

  public boolean deepEquals(Projekt otherProjekt)
  {
    if (!this.equals(otherProjekt))
    {
      return false;
    }
    if (this.aktiv == null)
    {
      if (otherProjekt.aktiv != null)
      {
        return false;
      }
    }
    else if (!this.aktiv.equals(otherProjekt.aktiv))
    {
      return false;
    }
    if (this.budgetTyp != otherProjekt.budgetTyp)
    {
      return false;
    }
    if (this.nummer == null)
    {
      if (otherProjekt.nummer != null)
      {
        return false;
      }
    }
    else if (!this.nummer.equals(otherProjekt.nummer))
    {
      return false;
    }
    if (this.taetigkeit != otherProjekt.taetigkeit)
    {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unused")
  @PostLoad
  private void postLoad()
  {
    this.budgetTyp = BudgetTyp.valueOfKuerzel(this.projektTyp.substring(0, 1));
    this.taetigkeit = Taetigkeit.valueOfKuerzel(this.projektTyp.substring(1, 3));
  }

  @SuppressWarnings("unused")
  @PrePersist
  @PreUpdate
  private void preStore()
  {
    this.projektTyp = this.budgetTyp.getKuerzel() + this.taetigkeit.getKuerzel();
  }

}
