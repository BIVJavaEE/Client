package business;

import java.util.Arrays;
import java.util.List;

public class BusinessCheckOrchestrator<T> extends BusinessCheck<T> {

    private List<BusinessCheck<T>> _businessChecks;

    public BusinessCheckOrchestrator(BusinessCheck<T> ...businessChecks) {
        _businessChecks = Arrays.asList(businessChecks);
    }

    public BusinessCheckOrchestrator<T> add(BusinessCheck<T> businessCheck) {
        _businessChecks.add(businessCheck);
        return this;
    }

    @Override
    public void run(T context) throws BusinessException {
        for (BusinessCheck<T> b : _businessChecks) {
            b.run(context);
        }
    }

}
