package architecture;

import com.tngtech.archunit.library.dependencies.SliceRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.Test;

public class SlicesTest extends AbstractArchitectureTest {

  @Test
  public void layersShouldBeFreeOfCycles() {
    SliceRule rule = SlicesRuleDefinition.slices()
      .matching("org.tms.(*)..")
      .should().beFreeOfCycles();
    rule.check(classes);
  }

  @Test
  public void adaptersShouldNotDependOnEachOther() {
    SliceRule rule = SlicesRuleDefinition.slices()
      .matching("org.tms.adapters.(*)")
      .should().notDependOnEachOther();
    rule.check(classes);
  }
}
