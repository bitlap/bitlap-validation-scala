package bitlap.validation

import scala.annotation.meta.field

import org.hibernate.validator.constraints.Mod10Check

class Mod10CheckValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
    @(Mod10Check @field)
    value: Option[String]
  )

  Seq(
    (TestBean(Some("00000001")), 1),
    (TestBean(Some("00000000")), 0)
  ) foreach { case (bean, expected) =>
    s"Check violations count. bean = $bean, count = $expected" >> {
      test(bean, expected)
    }
  }
}
