package de.gedoplan.beantrial.jpia.entity.convert;

public enum BudgetTyp
{
  AUFWAND
  {
    @Override
    public String getKuerzel()
    {
      return "A";
    }
  },
  HOECHSTPREIS
  {
    @Override
    public String getKuerzel()
    {
      return "H";
    }
  },
  FESTPREIS
  {
    @Override
    public String getKuerzel()
    {
      return "F";
    }
  };

  public abstract String getKuerzel();

  public static BudgetTyp valueOfKuerzel(String kuerzel)
  {
    for (BudgetTyp budgetTyp : BudgetTyp.values())
    {
      if (budgetTyp.getKuerzel().equals(kuerzel))
      {
        return budgetTyp;
      }
    }

    throw new IllegalArgumentException("Ungueltiges Kuerzel: " + kuerzel);
  }
}
