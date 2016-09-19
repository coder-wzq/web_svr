package cc.blueview.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.blueview.dao.TestDao;
import cc.blueview.service.TestService;

@Service
public class TestServiceImpl implements TestService{
	
	@Autowired
    TestDao testDao;

	@Override
	public String getTestResult() {
		return testDao.getTestResult();
	}

}
