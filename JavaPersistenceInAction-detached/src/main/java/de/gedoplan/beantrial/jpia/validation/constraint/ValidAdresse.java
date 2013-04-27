package de.gedoplan.beantrial.jpia.validation.constraint;

// CHECKSTYLE:OFF

import de.gedoplan.beantrial.jpia.entity.Adresse;
import de.gedoplan.beantrial.jpia.validation.validator.AdresseValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * BV-Constraint für Strassenadressen.
 * 
 * Anwendbar auf die Klasse {@link Adresse}. Details siehe {@link AdresseValidator}.
 * 
 * @author dw
 * 
 */
@Constraint(validatedBy = AdresseValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAdresse
{
  String message() default "muss komplett leer oder gefüllt sein";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
