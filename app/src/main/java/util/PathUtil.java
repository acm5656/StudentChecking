package util;

/**
 * Created by 那年.盛夏 on 2017/3/28.
 */

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
    public static final String TEACHER_OPEN_CHECKING = "api/attendance-consumer-cattendance/courseAttendance";
    public static final String TEACHER_GET_CHECKING_COURSE_ATTENTANCE ="api/attendance-consumer-cattendance/courseAttendanceItem/students";
    public static final String STUDENT_CHECKING_VERIFY_FACE ="api/attendance-consumer-cattendance/courseAttendanceItem";
    public static final String STUDENT_GET_CHECKING_AUTHORITY ="api/attendance-consumer-cattendance/courseAttendanceItem/authority";
//    public static final String TEACHER_ ="";返回补录，待讨论
    public static final String STUDENT_ASK_FOR_LEAVE ="api/attendance-provider-user/courseLeave/student";
    public static final String STUDENT_GET_STUDENT_ID ="api/attendance-provider-user/student/studentId";
    public static final String TEACHER_GET_STUDENT_ASK_FOR_LEAVE = "api/attendance-provider-user/courseLeave/students/id";
    public static final String GET_STUDENT_INFO_BY_STUDENT_ID = "api/attendance-provider-user/student/students/no/name";
    public static final String TEACHER_GET_COURSE_TIME_INFO = "api/attendance-consumer-eduaffairs/courseTime/teacher/courseTable";
    public static final String STUDENT_GET_COURSE_TIME_INFO = "api/attendance-consumer-eduaffairs/courseTime/student/courseTable";
}
