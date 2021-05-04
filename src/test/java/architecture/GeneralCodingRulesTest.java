package architecture;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.junit.jupiter.api.Test;

public class GeneralCodingRulesTest extends AbstractArchitectureTest {

  @Test
  public void noClassesShouldThrowGenericExceptions() {
    ArchRule rule = ArchRuleDefinition.noClasses()
      .should(GeneralCodingRules.THROW_GENERIC_EXCEPTIONS);
    rule.check(classes);
  }

  @Test
  public void noClassesShouldUseStandardLogging() {
    ArchRule rule = ArchRuleDefinition.noClasses()
      .should(GeneralCodingRules.USE_JAVA_UTIL_LOGGING);
    rule.check(classes);
  }
}
