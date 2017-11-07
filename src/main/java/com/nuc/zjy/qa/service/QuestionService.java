package com.nuc.zjy.qa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.zjy.qa.bean.Question;
import com.nuc.zjy.qa.dao.QuestionDAO;

/**
 * @项目名称：QA
 * @类名称：QuestionService @类描述：
 *
 * @author 赵建银
 * @date 2017年11月7日
 * @time 上午10:40:04
 * @version 1.0
 */
@Service
public class QuestionService {

	@Autowired
	QuestionDAO questionDAO;

	public List<Question> getLastQuestions(int userId) {
		return questionDAO.selectLastQuestins(userId);
	}
}
