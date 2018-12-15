package com.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.controllers.admin.IAdminCreateCoursesController;
import com.controllers.admin.IAdminCreateSpecializationsController;
import com.controllers.admin.IAdminCreateUserController;
import com.controllers.admin.IAdminFrameController;
import com.controllers.admin.IAdminHeaderController;
import com.controllers.admin.IAdminManageCoursesController;
import com.controllers.admin.IAdminManageSpecializationsController;
import com.controllers.admin.IAdminManageUsersController;
import com.controllers.admin.IAdminModifyCoursesController;
import com.controllers.admin.IAdminModifySpecializationController;
import com.controllers.admin.IAdminModifyUserController;
import com.controllers.admin.IAdminProfileController;
import com.controllers.admin.implementation.AdminCreateCoursesController;
import com.controllers.admin.implementation.AdminCreateSpecializationsController;
import com.controllers.admin.implementation.AdminCreateUserController;
import com.controllers.admin.implementation.AdminFrameController;
import com.controllers.admin.implementation.AdminHeaderController;
import com.controllers.admin.implementation.AdminManageCoursesController;
import com.controllers.admin.implementation.AdminManageSpecializationsController;
import com.controllers.admin.implementation.AdminManageUsersController;
import com.controllers.admin.implementation.AdminModifyCoursesController;
import com.controllers.admin.implementation.AdminModifySpecializationController;
import com.controllers.admin.implementation.AdminModifyUsersController;
import com.controllers.admin.implementation.AdminProfileController;
import com.controllers.common.IEmailPanelController;
import com.controllers.common.ILanguageController;
import com.controllers.common.ILoginController;
import com.controllers.common.implementation.EmailPanelController;
import com.controllers.common.implementation.LanguageController;
import com.controllers.common.implementation.LoginController;
import com.controllers.student.IStudentCoursesPanelController;
import com.controllers.student.IStudentEnrolledCoursesPanelController;
import com.controllers.student.IStudentFindCoursePannelController;
import com.controllers.student.IStudentFrameController;
import com.controllers.student.IStudentHeaderController;
import com.controllers.student.IStudentProfileInformationPanelController;
import com.controllers.student.implementation.StudentCoursesPanelController;
import com.controllers.student.implementation.StudentEnrolledCoursesPanelController;
import com.controllers.student.implementation.StudentFindCoursePannelController;
import com.controllers.student.implementation.StudentFrameController;
import com.controllers.student.implementation.StudentHeaderController;
import com.controllers.student.implementation.StudentProfileInformationPanelController;
import com.controllers.teacher.ITeacherCoursePanelController;
import com.controllers.teacher.ITeacherCreateAssignmentFrameController;
import com.controllers.teacher.ITeacherFrameController;
import com.controllers.teacher.ITeacherHeaderController;
import com.controllers.teacher.ITeacherManageAssignmentsPanelController;
import com.controllers.teacher.ITeacherManageStudentsPanelController;
import com.controllers.teacher.ITeacherProfileInformationPanelController;
import com.controllers.teacher.implementation.TeacherCoursePanelController;
import com.controllers.teacher.implementation.TeacherCreateAssignmentFrameController;
import com.controllers.teacher.implementation.TeacherFrameController;
import com.controllers.teacher.implementation.TeacherHeaderController;
import com.controllers.teacher.implementation.TeacherManageAssignmentsPanelController;
import com.controllers.teacher.implementation.TeacherManageStudentsPanelController;
import com.controllers.teacher.implementation.TeacherProfileInformationPanelController;
import com.model.Admin;
import com.model.Student;
import com.model.Teacher;
import com.model.User;
import com.model.dao.AdminDAO;
import com.model.dao.AssignmentDAO;
import com.model.dao.CourseDAO;
import com.model.dao.MailDAO;
import com.model.dao.SpecializationDAO;
import com.model.dao.StudentDAO;
import com.model.dao.TeacherDAO;
import com.model.dao.UserDAO;
import com.model.dao.implementation.AdminDAOImpl;
import com.model.dao.implementation.AssignmentDAOImpl;
import com.model.dao.implementation.CourseDAOImpl;
import com.model.dao.implementation.MailDAOImpl;
import com.model.dao.implementation.SpecializationDAOImpl;
import com.model.dao.implementation.StudentDAOImpl;
import com.model.dao.implementation.TeacherDAOImpl;
import com.model.dao.implementation.UserDAOImpl;
import com.model.mapper.AdminMapper;
import com.model.mapper.AssignmentMapper;
import com.model.mapper.CourseMapper;
import com.model.mapper.MailMapper;
import com.model.mapper.StudentAssignmentMapper;
import com.model.mapper.StudentMapper;
import com.model.mapper.TeacherMapper;
import com.model.mapper.UserMapper;
import com.views.admin.AdminFrame;
import com.views.admin.AdminHeader;
import com.views.admin.AdminPanel;
import com.views.admin.AdminProfile;
import com.views.admin.CreateCourses;
import com.views.admin.CreateSpecialization;
import com.views.admin.CreateUser;
import com.views.admin.ManageCourses;
import com.views.admin.ManageSpecializations;
import com.views.admin.ManageUsers;
import com.views.admin.ModifyCourses;
import com.views.admin.ModifySpecialization;
import com.views.admin.ModifyUser;
import com.views.common.Login;
import com.views.common.MailPanel;
import com.views.student.StudentCoursesPanel;
import com.views.student.StudentEnrolledCoursesPanel;
import com.views.student.StudentFindCoursePannel;
import com.views.student.StudentFrame;
import com.views.student.StudentHeader;
import com.views.student.StudentPanel;
import com.views.student.StudentProfileInformationPanel;
import com.views.teacher.TeacherCoursesPanel;
import com.views.teacher.TeacherCreateAssignmentFrame;
import com.views.teacher.TeacherFrame;
import com.views.teacher.TeacherHeader;
import com.views.teacher.TeacherManageAssignmentsPanel;
import com.views.teacher.TeacherManageStudentsPanel;
import com.views.teacher.TeacherPanel;
import com.views.teacher.TeacherProfileInformationPanel;
import com.views.teacher.TeacherStudentsTablePanel;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/se";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	private StudentFrame studentFrame;
	private IEmailPanelController emailPanelControllerTeacher;
	private IEmailPanelController emailPanelControllerStudent;
	private IStudentCoursesPanelController studentCoursesPanelController;
	private Connection connection;
	private TeacherFrame teacherFrame;
	private MailDAO mailDAO;
	private CourseDAO coursesDAO;
	private StudentDAO studentDAO;
	private TeacherDAO teacherDAO;
	private AssignmentDAO assignmentDAO;
	private AdminDAO adminDAO;
	private UserDAO userDAO;

	private CourseMapper courseMapper;
	private TeacherMapper teacherMapper;
	private StudentMapper studentMapper;
	private StudentAssignmentMapper studentAssignmentsMapper;
	private AssignmentMapper assignmetMapper;
	
	private AdminFrame adminFrame;
	private SpecializationDAO specializationDAO;
	private AdminMapper adminMapper;
	private UserMapper userMapper;
	private ILanguageController languageController;
	private ResourceBundle enBundle;
	private ResourceBundle roBundle;
	private Login loginFrame;
	private ITeacherFrameController teacherFrameController;
	private IStudentFrameController studentFrameController;
	private IAdminFrameController adminFrameController;

	public void run() {
		initializeBundles();
		initConn();
		initMappers();
		initDAO();
//		init();
//		 initialize();
		initStudent();
		initTeacher();
		initAdmin();
		initLogin();
		loginFrame.setVisible(true);
//		adminFrame.setVisible(true);
//		teacherFrame.setVisible(true);
//		studentFrame.setVisible(true);
	}


	private void initLogin() {
		ILoginController loginController = new LoginController(userDAO, adminDAO, teacherDAO, studentDAO, teacherFrameController, studentFrameController, adminFrameController);
		
		loginFrame = new Login(loginController, languageController);
		languageController.add(loginFrame);
		loginController.setLoginFrame(loginFrame);
	}


	private void initAdmin() {
//		Admin admin = adminDAO.getAdmin("3");
//		User user = new User();
		IAdminProfileController adminProfileController = new AdminProfileController(adminDAO);
		
		IAdminCreateCoursesController adminCreateCourseController = new AdminCreateCoursesController(coursesDAO, teacherDAO, specializationDAO);
		IAdminModifyCoursesController adminModifyCourseController = new AdminModifyCoursesController(coursesDAO, teacherDAO, specializationDAO);
		IAdminManageCoursesController adminManageCoursesController = new AdminManageCoursesController(coursesDAO, adminCreateCourseController, adminModifyCourseController);
		
		IAdminModifySpecializationController adminModifySpecializationsController = new AdminModifySpecializationController(specializationDAO);
		IAdminCreateSpecializationsController adminCreateSpecializationsController = new AdminCreateSpecializationsController(specializationDAO);
		IAdminManageSpecializationsController adminManageSpecializationsController = new AdminManageSpecializationsController(specializationDAO, adminModifySpecializationsController, adminCreateSpecializationsController);
				
		IEmailPanelController emailPanelController = new EmailPanelController(mailDAO);
		
		IAdminModifyUserController adminModifyUserController = new AdminModifyUsersController(adminDAO, teacherDAO, studentDAO, specializationDAO);
		IAdminCreateUserController adminCreateUserController = new AdminCreateUserController(teacherDAO, studentDAO, adminDAO, userDAO, specializationDAO);
		IAdminManageUsersController adminMangageUsersController = new AdminManageUsersController(userDAO, adminDAO, teacherDAO, studentDAO, adminCreateUserController, adminModifyUserController);

		IAdminHeaderController adminHeaderController = new AdminHeaderController(adminProfileController, adminManageCoursesController, adminManageSpecializationsController, adminMangageUsersController,emailPanelController);
		
		MailPanel mailPanel = new MailPanel(emailPanelController);
		
		AdminHeader adminHeader = new AdminHeader(adminHeaderController);
		AdminProfile adminProfile = new AdminProfile(adminProfileController);
		AdminPanel adminPanel = new AdminPanel(adminHeader, adminProfile);
		
		ManageCourses adminManageCourses = new ManageCourses(adminManageCoursesController);
		ModifyCourses adminModifyCourses = new ModifyCourses(adminModifyCourseController);
		CreateCourses adminCreateCourses = new CreateCourses(adminCreateCourseController);
		
		ManageSpecializations adminManageSpecializations = new ManageSpecializations(adminManageSpecializationsController);
		CreateSpecialization adminCreateSpecialization = new CreateSpecialization(adminCreateSpecializationsController);
		ModifySpecialization adminModifySpecialization = new ModifySpecialization(adminModifySpecializationsController);
		
		CreateUser adminCreateUsers = new CreateUser(adminCreateUserController);
		ManageUsers adminManageUsers = new ManageUsers(adminMangageUsersController);
		ModifyUser adminModifyUsers = new ModifyUser(adminModifyUserController);

		adminCreateUserController.setAdminCreateUsers(adminCreateUsers);
		adminMangageUsersController.setAdminPanel(adminPanel);
		adminMangageUsersController.setAdminManageUsers(adminManageUsers);
		adminModifyUserController.setAdminModifyUsers(adminModifyUsers);
		
		adminManageSpecializationsController.setAdminManageSpecializations(adminManageSpecializations);
		adminManageSpecializationsController.setAdminPanel(adminPanel);
		adminCreateSpecializationsController.setAdminCreateSpecialization(adminCreateSpecialization);
		adminModifySpecializationsController.setAdminModifySpecialization(adminModifySpecialization);
		
		
		adminManageCoursesController.setAdminManageCourses(adminManageCourses);
		adminManageCoursesController.setAdminPanel(adminPanel);
		adminModifyCourseController.setAdminModifyCourses(adminModifyCourses);
		adminCreateCourseController.setAdminCreateCourses(adminCreateCourses);
//		adminHeaderController.set
		
		adminProfileController.setAdminPanel(adminPanel);
		adminProfileController.setAdminProfilePanel(adminProfile);
		
		emailPanelController.setSupportPanel(adminPanel);
		emailPanelController.setMailPanel(mailPanel);
		
		adminFrame = new AdminFrame(adminPanel);
		
		languageController.add(adminHeader);
		languageController.add(adminModifyUsers);
		languageController.add(adminModifySpecialization);
		languageController.add(adminCreateSpecialization);
		languageController.add(adminManageSpecializations);
		languageController.add(adminCreateCourses);
		languageController.add(adminModifyCourses);
		languageController.add(adminManageCourses);
		languageController.add(adminProfile);
		languageController.add(mailPanel);
		languageController.add(adminManageUsers);
		adminFrameController = new AdminFrameController(adminProfileController, emailPanelController);
		adminFrameController.setAdminFrame(adminFrame);
	}

	private void initializeBundles() {
		Locale englishLocale = new Locale("en", "EN");
		enBundle = ResourceBundle.getBundle("LanguageBundle", englishLocale);
		
		Locale romainianLocale = new Locale("ro", "RO");
		roBundle = ResourceBundle.getBundle("LanguageBundle", romainianLocale);
		

		languageController = new LanguageController(roBundle, enBundle);
		
	}
	
	private void initMappers() {
		studentMapper = new StudentMapper();
		teacherMapper = new TeacherMapper();
		assignmetMapper = new AssignmentMapper();
		courseMapper = new CourseMapper(teacherMapper);
		studentAssignmentsMapper = new StudentAssignmentMapper(assignmetMapper);
		adminMapper = new AdminMapper();
		userMapper = new UserMapper();
	}

	private void initDAO() {
		studentDAO = new StudentDAOImpl(studentMapper, connection);
        coursesDAO = new CourseDAOImpl(courseMapper,connection);
		mailDAO = new MailDAOImpl(new MailMapper(), connection);
		teacherDAO = new TeacherDAOImpl(teacherMapper, connection);
		assignmentDAO = new AssignmentDAOImpl(assignmetMapper, studentAssignmentsMapper, connection);
		adminDAO = new AdminDAOImpl(adminMapper, connection);
		specializationDAO = new SpecializationDAOImpl(connection);
		userDAO = new UserDAOImpl(userMapper, connection);
	}

	private void initTeacher() {
//		User user = new User();
//		Teacher teacher = teacherDAO.getTeacher("123");
		
		emailPanelControllerTeacher = new EmailPanelController(mailDAO);
		ITeacherProfileInformationPanelController teacherProfilePanelController = new TeacherProfileInformationPanelController(teacherDAO);
		
		ITeacherManageStudentsPanelController teacherManageStudentsPanelController = new TeacherManageStudentsPanelController(studentDAO);
		ITeacherCreateAssignmentFrameController teacherCreateAssignmentFrameController = new TeacherCreateAssignmentFrameController(assignmentDAO);
		ITeacherManageAssignmentsPanelController teacherManageAssignmentsPanelConttoller = new TeacherManageAssignmentsPanelController(studentDAO, assignmentDAO, teacherCreateAssignmentFrameController);
		ITeacherCoursePanelController teacherCoursesPanelController = new TeacherCoursePanelController(coursesDAO, teacherManageStudentsPanelController, teacherManageAssignmentsPanelConttoller);
		
		ITeacherHeaderController teacherHeaderController = new TeacherHeaderController(teacherProfilePanelController, teacherCoursesPanelController, emailPanelControllerTeacher);
		
		MailPanel mailPanel = new MailPanel(emailPanelControllerTeacher);
		
		TeacherStudentsTablePanel waitingStudentsTablePanel = new TeacherStudentsTablePanel();
		TeacherStudentsTablePanel enrolledStudentsTablePanel = new TeacherStudentsTablePanel();
		TeacherStudentsTablePanel studentsTablePanel = new TeacherStudentsTablePanel(teacherManageAssignmentsPanelConttoller);
		TeacherManageStudentsPanel teacherManageStudentsPanel = new TeacherManageStudentsPanel(teacherManageStudentsPanelController, waitingStudentsTablePanel, enrolledStudentsTablePanel);
		TeacherManageAssignmentsPanel teacherManageAssignmentsPanel = new TeacherManageAssignmentsPanel(teacherManageAssignmentsPanelConttoller, studentsTablePanel);
		
		TeacherCreateAssignmentFrame teacherCreateAssignmentFrame = new TeacherCreateAssignmentFrame(teacherCreateAssignmentFrameController);

		TeacherCoursesPanel teacherCoursePanel = new TeacherCoursesPanel(teacherCoursesPanelController, teacherManageStudentsPanel, teacherManageAssignmentsPanel);

		TeacherHeader teacherHeader = new TeacherHeader(teacherHeaderController);
		TeacherProfileInformationPanel teacherProfileInformationPanel = new TeacherProfileInformationPanel(teacherProfilePanelController);
		TeacherPanel teacherPanel = new TeacherPanel(teacherHeader, teacherProfileInformationPanel);
		teacherFrame = new TeacherFrame(teacherPanel);
		
		teacherManageAssignmentsPanelConttoller.setTeacherManageAssignmentsPanel(teacherManageAssignmentsPanel);
		
		teacherManageStudentsPanelController.setTeacherManageStudentsPanel(teacherManageStudentsPanel);

		teacherCoursesPanelController.setTeacherCoursePanel(teacherCoursePanel);
		teacherCoursesPanelController.setTeacherPanel(teacherPanel);
		
		teacherProfilePanelController.setTeacherPanel(teacherPanel);
		teacherProfilePanelController.setTeacherProfileInformationPanel(teacherProfileInformationPanel);
		
		teacherCreateAssignmentFrameController.setTeacherCreateAssignmentFrame(teacherCreateAssignmentFrame);
		
		emailPanelControllerTeacher.setMailPanel(mailPanel);
		emailPanelControllerTeacher.setSupportPanel(teacherPanel);
	
		languageController.add(teacherProfileInformationPanel);
		languageController.add(teacherHeader);
		languageController.add(teacherCoursePanel);
		languageController.add(studentsTablePanel);
		languageController.add(enrolledStudentsTablePanel);
		languageController.add(waitingStudentsTablePanel);
		languageController.add(mailPanel);
		
		teacherFrameController = new TeacherFrameController(teacherProfilePanelController, teacherCoursesPanelController, emailPanelControllerTeacher);
		teacherFrameController.setTeacherFrame(teacherFrame);
	}

	private void initConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	

	private void initStudent() {
//		User user = new User();
//		Student student = studentDAO.getStudent("1");
		IStudentProfileInformationPanelController studentProfilePanelController = new StudentProfileInformationPanelController();
		
		IStudentFindCoursePannelController studentFindCoursePannelController = new StudentFindCoursePannelController(coursesDAO, studentDAO);
		IStudentEnrolledCoursesPanelController studentEnrolledCoursesPanelController = new StudentEnrolledCoursesPanelController(coursesDAO, assignmentDAO, null);
		
		studentCoursesPanelController = new StudentCoursesPanelController(studentFindCoursePannelController, studentEnrolledCoursesPanelController);
		
		emailPanelControllerStudent = new EmailPanelController(mailDAO);
		
		IStudentHeaderController studentHeaderController = new StudentHeaderController(studentProfilePanelController, studentCoursesPanelController, emailPanelControllerStudent);

		
		StudentProfileInformationPanel studentProfilePanel = new StudentProfileInformationPanel();
		StudentHeader header = new StudentHeader(studentHeaderController);
		StudentPanel studentPanel = new StudentPanel(header, studentProfilePanel);
		
		StudentFindCoursePannel studentFindCoursePannel = new StudentFindCoursePannel(studentFindCoursePannelController);
		StudentEnrolledCoursesPanel studentEnrolledCoursesPanel = new StudentEnrolledCoursesPanel(studentEnrolledCoursesPanelController);
		
		StudentCoursesPanel studentCoursesPanel = new StudentCoursesPanel(studentCoursesPanelController, studentFindCoursePannel, studentEnrolledCoursesPanel);

		studentFindCoursePannelController.setStudentFindCoursePannel(studentFindCoursePannel);
		studentEnrolledCoursesPanelController.setStudentEnrolledCoursesPanel(studentEnrolledCoursesPanel);
		
		studentProfilePanelController.setStudentPanel(studentPanel);
		studentProfilePanelController.setStudentProfileInformationPanel(studentProfilePanel);
		
		studentCoursesPanelController.setStudentPanel(studentPanel);
		studentCoursesPanelController.setStudentCoursesPanel(studentCoursesPanel);
		
		MailPanel mailPanel = new MailPanel(emailPanelControllerStudent);
		
		emailPanelControllerStudent.setMailPanel(mailPanel);
		emailPanelControllerStudent.setSupportPanel(studentPanel);
		
		studentFrame = new StudentFrame(studentPanel);
		
		languageController.add(mailPanel);
		languageController.add(studentCoursesPanel);
		languageController.add(studentEnrolledCoursesPanel);
		languageController.add(studentFindCoursePannel);
		languageController.add(header);
		languageController.add(studentProfilePanel);
		
		studentFrameController = new StudentFrameController(studentProfilePanelController, studentFindCoursePannelController, studentEnrolledCoursesPanelController, emailPanelControllerStudent);
		studentFrameController.setStudentFrame(studentFrame);
	}

	

}
