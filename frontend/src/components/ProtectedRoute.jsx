import { Navigate } from 'react-router-dom';
import globals from '../utils/globals';

export default function ProtectedRoute ({
    redirectPath,
    children
  })
  {
    globals.user = JSON.parse(localStorage.getItem("user"))
    console.log(globals.user);
    if (!globals.user) {
      return <Navigate to={redirectPath} replace />;
    }

    return children;
  }