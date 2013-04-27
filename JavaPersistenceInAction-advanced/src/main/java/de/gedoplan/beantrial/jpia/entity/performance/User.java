package de.gedoplan.beantrial.jpia.entity.performance;

import de.gedoplan.baselibs.persistence.entity.StringIdEntity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = User.TABLE_NAME)
public class User extends StringIdEntity
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME        = "JPIA_USER";

  /**
   * Tabellenname für die Verkettung zu Groups.
   */
  public static final String TABLE_NAME_GROUPS = "JPIA_USER_GROUP";

  /**
   * Name.
   */
  private String             fullName;

  /**
   * Benutzergruppe.
   */
  @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
  @JoinTable(name = TABLE_NAME_GROUPS, joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "GROUP_ID"))
  //
  // Variante 1: Groups bei Bedarf mit einem SELECT holen, wobei die User-IDs in einer IN-Klausel mit max. 10 Einträgen
  // spezifiziert werden
  // (EclipseLink ignoriert scheinbar size und nimmt stets 500 an)
  // (OpenJPA bietet diese Verfahren nicht)
  // @org.eclipse.persistence.annotations.BatchFetch(value = org.eclipse.persistence.annotations.BatchFetchType.IN, size = 10)
  @org.hibernate.annotations.BatchSize(size = 10)
  //
  // Variante 2: Groups bei Bedarf mit einem SELECT holen, wobei die User-IDs per Subselect spezifiziert werden
  // (OpenJPA bietet diese Verfahren nicht)
  @org.eclipse.persistence.annotations.BatchFetch(value = org.eclipse.persistence.annotations.BatchFetchType.EXISTS)
  // @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
  //
  // Variante 3: Groups bei Bedarf mit einem SELECT holen, wobei die User-IDs per Join spezifiziert werden
  // (Hibernate bietet diese Verfahren nicht: FetchMode.JOIN führt zu N+1 SELECTs)
  // (OpenJPA berücksichtigt nur EAGER-Fetches, was im Test durch einen Fetch PLan erzwungen wird)
  // (OpenJPA erzeugt bei Nutzung von FetchMode.PARALLEL ein SELECT pro Ebene; FetchMode.JOIN erzeugt teilweise N+1 SELECTs)
  // @org.eclipse.persistence.annotations.BatchFetch(value = org.eclipse.persistence.annotations.BatchFetchType.JOIN)
  @org.apache.openjpa.persistence.jdbc.EagerFetchMode(org.apache.openjpa.persistence.jdbc.FetchMode.PARALLEL)
  private Set<Group>         groups;

  /**
   * Konstruktor.
   * 
   * @param id
   * @param fullName
   */
  public User(String id, String fullName)
  {
    super(id);
    this.fullName = fullName;

    this.groups = new HashSet<>();
  }

  /**
   * Konstruktor.
   */
  protected User()
  {
  }

  /**
   * Wert liefern: {@link #fullName}.
   * 
   * @return Wert
   */
  public String getFullName()
  {
    return this.fullName;
  }

  /**
   * Wert setzen: {@link #fullName}.
   * 
   * @param fullName Wert
   */
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }

  /**
   * Wert liefern: {@link #groups}.
   * 
   * @return Wert
   */
  public Set<Group> getGroups()
  {
    return this.groups;
  }

  public boolean deepEquals(User otherUser)
  {
    if (!this.equals(otherUser))
    {
      return false;
    }

    if (this.fullName == null)
    {
      if (otherUser.fullName != null)
      {
        return false;
      }
    }
    else if (!this.fullName.equals(otherUser.fullName))
    {
      return false;
    }

    if (this.groups == null)
    {
      if (otherUser.groups != null)
      {
        return false;
      }
    }
    else
    {
      if (this.groups.size() != otherUser.groups.size())
      {
        return false;
      }

      // TODO: Dieser Vergleich ist ineffizient programmiert
      groupLoop: for (Group group : this.groups)
      {
        for (Group otherGroup : otherUser.groups)
        {
          if (group.equals(otherGroup))
          {
            if (!group.deepEquals(otherGroup))
            {
              return false;
            }

            continue groupLoop;
          }
        }
      }
    }

    return true;
  }

}
