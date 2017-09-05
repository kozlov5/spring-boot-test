package service.test.impl;

import dao.DetailsDAO;
import entity.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.test.DetailsService;

import javax.transaction.Transactional;

@Service
public class DetailsServiceImpl implements DetailsService {
    @Autowired
    private DetailsDAO detailsDAO;

    @Override
    @Transactional
    public void update(Details details) {
        detailsDAO.save(details);
    }
}
