package architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeAll;

public abstract class AbstractArchitectureTest {
  static final String DOMAIN_LAYER_PACKAGES = "org.tms.domain..";
  static final String APPLICATION_LAYER_PACKAGES = "org.tms.application..";
  static final String ADAPTERS_LAYER_PACKAGES = "org.tms.adapters..";
  static final String PERSISTENCE_ADAPTERS_PACKAGES = "org.tms.adapters.persistence..";
  static final String REST_ADAPTERS_PACKAGES = "org.tms.adapters.rest..";
  static final String DOMAIN_MODEL_PACKAGES = "org.tms.domain.model..";
  static final String DOMAIN_SERVICE_PACKAGES = "org.tms.domain.service..";

  static JavaClasses classes;

  @BeforeAll
  public static void setUp() {
    classes = new ClassFileImporter()
      .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
      .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
      .importPackages("org.tms");
  }
}
