package architecture;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.plantuml.PlantUmlArchCondition;
import org.junit.jupiter.api.Test;

import java.net.URL;

public class ComponentsPlantUMLDiagramTest extends AbstractArchitectureTest {

    @Test
    public void dependenciesDescribedWithPumlMustBeFulfilled() {
        final URL diagram = this.getClass().getResource("template_archunit.puml");
        ArchRule rule = ArchRuleDefinition.classes().should(
            PlantUmlArchCondition.adhereToPlantUmlDiagram(diagram,
                PlantUmlArchCondition.Configurations.consideringOnlyDependenciesInDiagram()));
        rule.check(classes);
    }
}
