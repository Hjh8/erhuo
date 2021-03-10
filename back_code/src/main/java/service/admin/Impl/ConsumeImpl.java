package service.admin.Impl;

import dao.adminDao.ConsumeDao_admin;
import org.springframework.stereotype.Service;
import service.admin.ConsumeService_admin;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ConsumeImpl implements ConsumeService_admin {

    @Resource
    private ConsumeDao_admin consumeDao_admin;

    @Override
    public List<Double> get30DaySale() {
        return consumeDao_admin.get30DaySale();
    }
}
