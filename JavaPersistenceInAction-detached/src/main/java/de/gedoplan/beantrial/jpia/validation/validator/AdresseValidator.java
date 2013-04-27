package de.gedoplan.beantrial.jpia.validation.validator;

import de.gedoplan.beantrial.jpia.entity.Adresse;
import de.gedoplan.beantrial.jpia.validation.constraint.ValidAdresse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * BV-Validator zum Constraint {@link ValidAdresse}.
 * 
 * Adressen sind gültig, wenn ihre Komponenten entweder komplett <code>null</code> oder nicht <code>null</code> sind.
 * 
 * @author dw
 */
public class AdresseValidator implements ConstraintValidator<ValidAdresse, Adresse>
{
  @Override
  public void initialize(ValidAdresse constraint)
  {
  }

  @Override
  public boolean isValid(Adresse adresse, ConstraintValidatorContext validationContext)
  {
    if (adresse == null // null ist ok
        || (adresse.getOrt() == null && adresse.getPlz() == null && adresse.getStrasse() == null && adresse.getHausNummer() == null)
        || (adresse.getOrt() != null && adresse.getPlz() != null && adresse.getStrasse() != null))
    {
      return true;
    }

    return false;
  }

}
