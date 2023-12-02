import { Navigate } from 'react-router-dom';
import globals from '../utils/globals';

export default function ProtectedRoute ({
    redirectPath,
    children
  })
  {
    if (!globals.user) {
      return <Navigate to={redirectPath} replace />;
    }

    return children;
  }