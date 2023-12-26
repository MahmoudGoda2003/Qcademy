import { Navigate } from 'react-router-dom';
import globals from '../../utils/globals';

export default function UpProtectedRoute ({
    redirectPath,
    children
  })
  {
    globals.user = JSON.parse(localStorage.getItem("user"))
    if (globals.user) {
      return <Navigate to={redirectPath} replace />;
    }

    return children;
  }