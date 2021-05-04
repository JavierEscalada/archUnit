package architecture;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;


public class DomainComponentsTest extends AbstractArchitectureTest {

  @Test
  public void domainClassesShouldOnlyBeAccessedByOtherDomainClassesOrTheApplicationLayerOrTheAdapterLayer() {
    ArchRule rule = ArchRuleDefinition.classes()
      .that().resideInAPackage(DOMAIN_LAYER_PACKAGES)
      .should().onlyBeAccessed().byAnyPackage(DOMAIN_LAYER_PACKAGES, APPLICATION_LAYER_PACKAGES, ADAPTERS_LAYER_PACKAGES);
    rule.check(classes);
  }

  @Test
  public void domainClassesShouldOnlyDependOnDomainOrStdLibClasses() {
    ArchRule rule = ArchRuleDefinition.classes()
      .that().resideInAPackage(DOMAIN_LAYER_PACKAGES)
      .should().onlyDependOnClassesThat().resideInAnyPackage(DOMAIN_LAYER_PACKAGES, "java..");
    rule.check(classes);
  }
}
