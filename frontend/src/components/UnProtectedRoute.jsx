import { Navigate } from 'react-router-dom';
import globals from '../utils/globals';

export default function UpProtectedRoute ({
    redirectPath,
    children
  })
  {
    globals.user = JSON.parse(localStorage.getItem("user"))
    globals.course = JSON.parse(localStorage.getItem("course"))
    if (globals.user) {
      return <Navigate to={redirectPath} replace />;
    }

    return children;
  }