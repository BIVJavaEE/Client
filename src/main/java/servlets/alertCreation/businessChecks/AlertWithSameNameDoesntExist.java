package servlets.alertCreation.businessChecks;

import business.BusinessCheck;
import business.BusinessException;
import servlets.alertCreation.AlertCreationContext;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class AlertWithSameNameDoesntExist extends BusinessCheck<AlertCreationContext> {

    private EntityManager _em;

    public AlertWithSameNameDoesntExist(EntityManager em) {
        _em = em;
    }

    @Override
    public void run(AlertCreationContext context) throws BusinessException {
        boolean exists = true;
        try {
            _em
                .createQuery("FROM Alert a WHERE a.name = :name")
                .setParameter("name", context.getAlert().getName())
                .getSingleResult();
        } catch (NoResultException ignored) {
            exists = false;
        }

        if (exists) {
            throw new BusinessException("An alert with the same name already exists");
        }
    }

}
