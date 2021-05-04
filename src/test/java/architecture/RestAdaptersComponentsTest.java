package architecture;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

public class RestAdaptersComponentsTest extends AbstractArchitectureTest {

  @Test
  public void controllerClassesShouldBeAnnotatedWithControllerOrRestControllerAnnotation() {
    ArchRule rule = ArchRuleDefinition.classes()
      .that().haveSimpleNameEndingWith("Controller")
      .should().beAnnotatedWith(Controller.class)
      .orShould().beAnnotatedWith(RestController.class);
    rule.check(classes);
  }

  @Test
  public void noClassesWithControllerOrRestControllerAnnotationShouldResideOutsideOfRestAdaptersPackages() {
    ArchRule rule = ArchRuleDefinition.noClasses()
      .that().areAnnotatedWith(Controller.class)
      .or().areAnnotatedWith(RestController.class)
      .should().resideOutsideOfPackage(REST_ADAPTERS_PACKAGES);
    rule.check(classes);
  }

  @Test
  public void publicControllerMethodsShouldBeAnnotatedWithARequestMapping() {
    ArchRule rule = ArchRuleDefinition.methods()
      .that().arePublic()
      .and().areDeclaredInClassesThat().resideInAPackage("..adapters.rest..")
      .and().areDeclaredInClassesThat().haveSimpleNameEndingWith("Controller")
      .and().areDeclaredInClassesThat().areAnnotatedWith(Controller.class)
      .or().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
      .should().beAnnotatedWith(RequestMapping.class)
      .orShould().beAnnotatedWith(GetMapping.class)
      .orShould().beAnnotatedWith(PostMapping.class)
      .orShould().beAnnotatedWith(PatchMapping.class)
      .orShould().beAnnotatedWith(DeleteMapping.class);
    rule.check(classes);
  }
}
