package bitlap.validation

import scala.annotation.meta.field

import org.hibernate.validator.constraints.EAN

class EANValidatorForOptionSpec extends BaseSpec {

  private[this] case class TestBean(
    @(EAN @field)(`type` = EAN.Type.EAN8)
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
