package util;

/**
 * Created by 那年.盛夏 on 2017/3/28.
 */
//用来存储各种url，避免hardcode
public class PathUtil {
    public static final String STUDENT_FACE_CODE = "api/attendance-provider-user/studentFacecode";
    public static final String STUDENT_REGIST = "api/attendance-provider-user/student";
    public static final String STUDENT_LOGIN = "api/attendance-provider-user/student/login";
    public static final String CHANGE_STUDENT_INFO = "api/attendance-provider-user/student/nonsensitive/info";
    public static final String CHANGE_STUDENT_PASSWORD = "api/attendance-provider-user/student/sensitive/password";
    public static final String GET_STUDENT_REGIST_TEL_VERIFY_CODE = "api/attendance-provider-user/telVerifyCode/register/student";
    public static final String SAVE_PICTURE = "api/attendance-provider-picture/picture";
    public static final String GET_PICTURE = "api/attendance-provider-picture/picture/";
    public static final String GET_STUDENT_CHANGE_PASSWORD_TEL_VERIFY_CODE = "api/attendance-provider-user/telVerifyCode/student/password";
    public static final String TEACHER_REGIST = "api/attendance-provider-user/teacher";
    public static final String TEACHER_LOGIN = "api/attendance-provider-user/teacher/login";
    public static final String TEACHER_CHANGE_INFO = "api/attendance-provider-user/teacher/nonsensitive/info";
    public static final String TEACHER_CHANGE_PASSWORD ="api/attendance-provider-user/teacher/sensitive/password";
    public static final String GET_TEACHER_REGIST_TEL_VERIFY_CODE = "api/attendance-provider-user/telVerifyCode/register/teacher";
    public static final String GET_TEACHER_CHANGE_PASSWORD_TEL_VERIFY_CODE = "api/attendance-provider-user/telVerifyCode/teacher/password";
    public static final String TEACHER_OPEN_CHECKING = "api/attendance-consumer-cattendance/virtualCourseAttendance";
    public static final String TEACHER_GET_CHECKING_COURSE_ATTENTANCE ="api/attendance-consumer-cattendance/virtualCourseAttendanceItem/items";
    public static final String STUDENT_CHECKING_VERIFY_FACE ="api/attendance-consumer-cattendance/virtualCourseAttendanceItem";
    public static final String STUDENT_GET_CHECKING_AUTHORITY ="api/attendance-consumer-cattendance/virtualCourseAttendanceItem/authority";
    public static final String STUDENT_ASK_FOR_LEAVE ="api/attendance-consumer-cattendance/virtualCourseLeave";
    public static final String STUDENT_GET_STUDENT_ID ="api/attendance-provider-user/student/student";
    public static final String TEACHER_GET_STUDENT_ASK_FOR_LEAVE = "api/attendance-provider-user/courseLeave/students/id";
    public static final String GET_STUDENT_INFO_BY_STUDENT_ID = "api/attendance-provider-user/student/students/no/name";
    public static final String TEACHER_GET_COURSE_TIME_INFO = "api/attendance-consumer-eduaffairs/virtualCourse/teacher/courseList";
    public static final String STUDENT_GET_COURSE_TIME_INFO = "api/attendance-consumer-eduaffairs/virtualCourse/student/courseList";
    public static final String TEACHER_ADD_COURSE = "api/attendance-consumer-eduaffairs/virtualCourse";
    public static final String STUDENT_ADD_COURSE = "api/attendance-consumer-eduaffairs/virtualGrade";
    public static final String STUDENT_GET_COURSE_ATTENTANCE_TOTAL = "api/attendance-consumer-cattendance/virtualCourseAttendanceItem/student/countList";
    public static final String STUDENT_GET_EVERY_COURSE_ATTENTANCE_INFO = "api/attendance-consumer-cattendance/virtualCourseAttendanceItem/items";
    public static final String TEACHER_GET_EVERT_COURSE_ATTENTANCE_TOTAL = "api/attendance-consumer-cattendance/virtualCourseAttendanceItem/teacher/countList";
    public static final String TEACHER_GET_COURSE_STUDENT_ATTENTANCE_LIST_TOTAL = "api/attendance-consumer-cattendance/virtualCourseAttendanceItem/students/countList";
    public static final String TEACHER_GET_COURSE_STUDENT_ATTENTANCE_INFO = "api/attendance-consumer-cattendance/virtualCourseAttendanceItem/items";
    public static final String TEACHER_INIT_OPEN_CHECKING = "api/attendance-consumer-cattendance/virtualCourseAttendanceItem/initialItems";
    public static final String TEACHER_CLOSE_CHECK = "api/attendance-consumer-cattendance/virtualCourseAttendance/status/close";
    public static final String TEACHER_CHANGE_STUDENT_LATE = "api/attendance-consumer-cattendance/virtualCourseAttendanceItem/status/late";
    public static final String TEACHER_CHANGE_STUDENT_ATTENTANCE = "api/attendance-consumer-cattendance/virtualCourseAttendanceItem/status/attendance";
    public static final String ASSISTANT_REGIST_GET_VERIFY_CODE = "api/attendance-provider-user/telVerifyCode/register/assistant";
    public static final String ASSISTANT_REGIST = "api/attendance-provider-user/assistant";
    public static final String ASSISTANT_LOGIN = "api/attendance-provider-user/assistant/login";
}

