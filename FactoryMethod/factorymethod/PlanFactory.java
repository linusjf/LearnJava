package factorymethod;

import java.util.Objects;

class PlanFactory {
  // use getPlan method to get object of type Plan
  public Plan getPlan(String planType) {
    Objects.requireNonNull(planType, "Plan type cannot be null.");
    if ("DOMESTICPLAN".equalsIgnoreCase(planType)) {
      return new DomesticPlan();
    } else if ("COMMERCIALPLAN".equalsIgnoreCase(planType)) {
      return new CommercialPlan();
    } else if ("INSTITUTIONALPLAN".equalsIgnoreCase(planType)) {
      return new InstitutionalPlan();
    }
    throw new IllegalArgumentException(planType + ": Invalid parameter.");
  }
}
