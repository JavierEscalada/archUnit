package architecture;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

public class SpringCodingRulesTest extends AbstractArchitectureTest {

    @Test
    public void springSingletonComponentsShouldOnlyHaveFinalFields() {
        ArchRule rule = ArchRuleDefinition.classes()
            .that().areAnnotatedWith(Service.class)
            .or().areAnnotatedWith(Component.class)
            .and().areNotAnnotatedWith(ConfigurationProperties.class)
            .or().areAnnotatedWith(Controller.class)
            .or().areAnnotatedWith(RestController.class)
            .or().areAnnotatedWith(Repository.class)
            .should().haveOnlyFinalFields();
        rule.check(classes);
    }

    @Test
    public void springFieldDependencyInjectionShouldNotBeUsed_ApiLangVersion() {
        ArchRule rule = ArchRuleDefinition.noFields()
            .should().beAnnotatedWith(Autowired.class);
        rule.check(classes);
    }

    @Test
    public void springFieldDependencyInjectionShouldNotBeUsed_ApiLibraryVersion() {
        ArchRule rule = GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
        rule.check(classes);
    }
}
