package servlets.alertCreation.businessChecks;

import business.BusinessCheck;
import business.BusinessException;
import servlets.alertCreation.AlertCreationContext;

public class AlertIsValid extends BusinessCheck<AlertCreationContext> {

    @Override
    public void run(AlertCreationContext context) throws BusinessException {
        if (context.getMapException() != null) {
            throw new BusinessException(context.getMapException().getMessage());
        }
    }

}
