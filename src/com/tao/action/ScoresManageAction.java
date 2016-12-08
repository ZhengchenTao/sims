package com.tao.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tao.model.Classes;
import com.tao.model.Course;
import com.tao.model.Scores;
import com.tao.model.Student;
import com.tao.service.ClassesService;
import com.tao.service.CourseService;
import com.tao.service.ScoresService;
import com.tao.service.StudentService;

@Controller
@ParentPackage("all")
@Namespace(value = "/scores")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class ScoresManageAction extends ActionSupport {
	private Course course;
	private Student student;
	private Scores scores;
	private int id;
	private int page = 1;
	private int rows = 9;
	@Resource
	private CourseService courseService;
	@Resource
	private StudentService studentService;
	@Resource
	private ScoresService scoresService;

	@Action(value = "index", results = {
			@Result(name = "success", location = "/scores/scoresIndex.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String index() {
		int count = studentService.getCount();
		int cpage;
		if (count % rows == 0) {
			cpage = (count / rows);
		} else {
			cpage = (count / rows) + 1;
		}
		if (page < 1) {
			this.page = 1;
		} else if (page > cpage) {
			page = cpage;
		}

		List<Scores> scoresList = scoresService.findByPage(page, rows);
		List<Course> courseList = courseService.findAllList();
		List<Student> studentList = studentService.findAllList();
		if (studentList != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("list", studentList);
			ac.put("list", scoresList);
			ac.put("clist", courseList);
			ac.put("slist", studentList);
			ac.put("page", page);
			ac.put("cpage", cpage);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "delete", results = {
			@Result(type = "redirect", name = "success", location = "/scores/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String delete() {
		Scores s = scoresService.findScoresById(id);
		scoresService.deleteScores(s);
		return SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/scores/scoresEdit.jsp"),
			@Result(name = "input", location = "/error.jsp") })
	public String edit() {
		Scores s = scoresService.findScoresById(id);
		Student stu = studentService.findStudentById(s.getUid());
		List<Course> courseList = courseService.findAllList();
		if (courseList != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("s", s);
			ac.put("stu", stu);
			ac.put("clist", courseList);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "doEdit", results = {
			@Result(type = "redirect", name = "success", location = "/scores/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String doEdit() {
		scoresService.updateScores(scores);
		return SUCCESS;
	}

	@Action(value = "add", results = {
			@Result(name = "success", location = "/scores/scoresAdd.jsp"),
			@Result(name = "input", location = "/error.jsp") })
	public String add() {
		List<Course> courseList = courseService.findAllList();
		if (courseList != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("clist", courseList);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "doAdd", results = {
			@Result(type = "redirect", name = "success", location = "/scores/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String doAdd() {
		scoresService.saveScores(scores);
		return SUCCESS;
	}

	public Course getCourse() {
		return course;
	}

	public Scores getScores() {
		return scores;
	}

	public void setScores(Scores scores) {
		this.scores = scores;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
