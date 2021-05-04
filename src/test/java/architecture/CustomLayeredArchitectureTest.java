package architecture;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

public class CustomLayeredArchitectureTest extends AbstractArchitectureTest {

    private static final String DOMAIN_LAYER = "domain layer";
    private static final String APPLICATION_LAYER = "application layer";
    private static final String ADAPTERS_LAYER = "adapters layer";

    private static Architectures.LayeredArchitecture portsAndAdaptersArchitecture = Architectures
        .layeredArchitecture()
        .layer(DOMAIN_LAYER).definedBy(DOMAIN_LAYER_PACKAGES)
        .layer(APPLICATION_LAYER).definedBy(APPLICATION_LAYER_PACKAGES)
        .layer(ADAPTERS_LAYER).definedBy(ADAPTERS_LAYER_PACKAGES);

    @Test
    public void domainLayerShouldOnlyBeAccessedByApplicationLayer() {
        ArchRule rule = portsAndAdaptersArchitecture.whereLayer(DOMAIN_LAYER)
            .mayOnlyBeAccessedByLayers(APPLICATION_LAYER);
        rule.check(classes);
    }

    @Test
    public void applicationLayerMayOnlyBeAccessedByAdaptersLayer() {
        ArchRule rule = portsAndAdaptersArchitecture
            .whereLayer(APPLICATION_LAYER)
            .mayOnlyBeAccessedByLayers(ADAPTERS_LAYER);
        rule.check(classes);
    }

    @Test
    public void adaptersLayerShouldNotBeAccessedByAnyLayer() {
        ArchRule rule = portsAndAdaptersArchitecture.whereLayer(ADAPTERS_LAYER)
            .mayNotBeAccessedByAnyLayer();
        rule.check(classes);
    }

    @Test
    public void architectureShouldBeOnionArchitecture() {
        onionArchitecture()
            .domainModels(DOMAIN_MODEL_PACKAGES)
            .domainServices(DOMAIN_SERVICE_PACKAGES)
            .applicationServices(APPLICATION_LAYER_PACKAGES )
            .adapter("adapter rest", REST_ADAPTERS_PACKAGES )
            .adapter("adapter persistence", PERSISTENCE_ADAPTERS_PACKAGES)
            .check(classes);
    }
}
