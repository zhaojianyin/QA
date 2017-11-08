package com.nuc.zjy.qa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

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

	@Autowired
	Sensitive sensitive;

	public List<Question> getLastQuestions(int userId) {
		return questionDAO.selectLastQuestins(userId);
	}

	public void addQuestion(Question question) {
		question.setContent(HtmlUtils.htmlEscape(question.getContent()));
		question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));
		question.setContent(sensitive.filter(question.getContent()));
		question.setTitle(sensitive.filter(question.getTitle()));
		questionDAO.addQuestion(question);
	}

	public Question getById(int id) {
		return questionDAO.selectByid(id);
	}

	public void updatequestionCommentCount(int entityId, int count) {
		questionDAO.updateCommentCount(entityId, count);
	}
}
