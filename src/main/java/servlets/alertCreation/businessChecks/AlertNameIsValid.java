package servlets.alertCreation.businessChecks;

import business.BusinessCheck;
import business.BusinessException;
import servlets.alertCreation.AlertCreationContext;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class AlertNameIsValid extends BusinessCheck<AlertCreationContext> {

    private EntityManager _em;

    public AlertNameIsValid(EntityManager em) {
        _em = em;
    }

    @Override
    public void run(AlertCreationContext context) throws BusinessException {
        String name = context.getAlert().getName();
        if (name.isEmpty()) {
            throw new BusinessException("Empty name");
        }

        boolean exists = true;
        try {
            _em
                .createQuery("FROM Alert a WHERE a.name = :name")
                .setParameter("name", name)
                .getSingleResult();
        } catch (NoResultException ignored) {
            exists = false;
        }

        if (exists) {
            throw new BusinessException("An alert with the same name already exists");
        }
    }

}
