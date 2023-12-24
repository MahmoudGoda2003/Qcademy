import globals from '../utils/globals';
import Student from './Student';
import Teacher from './TeacherHome';
import Admin from './AdminHome';

export default function Home(props) {

    console.log(globals.user);

    return (
        <>
            {globals.user.role === "STUDENT" && <Student />}
            {globals.user.role === "TEACHER" && <Teacher />}
            {globals.user.role === "ADMIN" && <Admin />}
        </>
    );

}