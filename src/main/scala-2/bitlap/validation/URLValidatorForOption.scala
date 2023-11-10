package bitlap.validation

import javax.validation.{ ConstraintValidator, ConstraintValidatorContext }

import org.hibernate.validator.constraints.URL
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator

/**
 * Validate that the wrapped character sequence (e.g. Option[String]) is a valid URL.
 */
class URLValidatorForOption extends ConstraintValidator[URL, Option[_]] {
  private var constraintAnnotation: URL = _

  override def initialize(constraintAnnotation: URL): Unit =
    this.constraintAnnotation = constraintAnnotation

  override def isValid(value: Option[_], context: ConstraintValidatorContext): Boolean =
    value match {
      case Some(x: CharSequence) =>
        val v = new URLValidator
        v.initialize(constraintAnnotation)
        v.isValid(x, context)
      case None                  =>
        true
      case Some(_)               =>
        throw new IllegalStateException("oops.")
    }
}
