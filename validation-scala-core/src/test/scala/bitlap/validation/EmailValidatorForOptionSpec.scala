package bitlap.validation

import scala.annotation.meta.field

import jakarta.validation.constraints.Email

class EmailValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
    @(Email @field)
    value: Option[String]
  )

  Seq(
    (TestBean(Some("a..@example.com")), 1),
    (TestBean(Some("a@example.com")), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
