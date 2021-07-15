package egovframework.com.train.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("EgovTrainDAO")
public class EgovTrainDAO extends EgovComAbstractDAO {

	public List<HashMap> selectUserVMLists(HashMap param) {
		return selectList("egovTrainDAO.selectUserVMLists", param);
	}

	public HashMap selectUserVmGroupId(HashMap param) {
		return selectOne("egovTrainDAO.selectUserVmGroupId", param);
	}

}
