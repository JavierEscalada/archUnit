package architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClassList;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.tms.domain.service.CarSalesService;

import java.util.Set;
import java.util.function.Predicate;

public class ApplicationComponentsTest extends AbstractArchitectureTest {

    public static final String USE_CASE = "UseCase";

    @Test
    public void useCaseClassesShouldBeAnnotatedWithServiceAnnotation() {
        ArchRule rule = ArchRuleDefinition.classes()
            .that().haveSimpleNameEndingWith(USE_CASE)
            .should().beAnnotatedWith(Service.class);
        rule.check(classes);
    }

    @Test
    public void noUseCaseClassesShouldResideOutsideTheApplicationLayer() {
        ArchRule rule = ArchRuleDefinition.noClasses()
            .that().haveSimpleNameEndingWith(USE_CASE)
            .should().resideOutsideOfPackage(APPLICATION_LAYER_PACKAGES);
        rule.check(classes);
    }

    @Test
    public void noClassesWithServiceAnnotationShouldResideOutsideTheApplicationLayer() {
        ArchRule rule = ArchRuleDefinition.noClasses()
            .that().areAnnotatedWith(Service.class)
            .should().resideOutsideOfPackage(APPLICATION_LAYER_PACKAGES);
        rule.check(classes);
    }

    @Test
    public void useCaseClassesShouldImplementServiceInterface() {
        ArchRule rule = ArchRuleDefinition.classes()
            .that().haveSimpleNameEndingWith(USE_CASE)
            .should().implement(CarSalesService.class);
        rule.check(classes);
    }

    @Test
    public void useCaseClassesShouldHaveAInvokeMethodWithAtMostOneParameter() {
        ArchRule rule = ArchRuleDefinition.classes()
            .that().haveSimpleNameEndingWith(USE_CASE)
            .should(new HaveAnInvokeMethodWithAtMostOneParameter());
        rule.check(classes);
    }

    class HaveAnInvokeMethodWithAtMostOneParameter extends ArchCondition<JavaClass> {

        HaveAnInvokeMethodWithAtMostOneParameter() {
            super("have an 'invoke' method with a single parameter and a Mono or Flux return type");
        }

        @Override
        public void check(JavaClass clazz, ConditionEvents events) {
            final Set<JavaMethod> methods = clazz.getMethods();
            final Predicate<JavaMethod> hasMethodNamedInvoke = method -> "invoke".equals(method.getName());
            if (methods.stream().filter(hasMethodNamedInvoke).count() < 1) {
                events.add(SimpleConditionEvent.violated(clazz, clazz.getSimpleName() + " does not have a single 'invoke' method"));
                return;
            }
            methods.stream().filter(hasMethodNamedInvoke).findFirst().ifPresent(invokeMethod -> {
                final JavaClassList parameters = invokeMethod.getRawParameterTypes();
                if (parameters.size() > 1) {
                    events.add(SimpleConditionEvent.violated(invokeMethod, invokeMethod.getName() + ": method 'invoke' does not have a single parameter"));
                }
                final JavaClass returnType = invokeMethod.getRawReturnType();
                if (!"Flux".equals(returnType.getSimpleName()) && !"Mono".equals(returnType.getSimpleName())
                    && !"Object".equals(returnType.getSimpleName()) //Interface
                ) {
                    events.add(SimpleConditionEvent.violated(invokeMethod, invokeMethod.getName() + ": method 'invoke' does not have a return type that is Mono or Flux"));
                }
            });
        }

    }
}
