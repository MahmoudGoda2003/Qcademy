import { Navigate } from 'react-router-dom';

export default function ProtectedRoute ({
    user,
    redirectPath,
    children
  })
  {
    console.log(user);
    if (!user) {
      return <Navigate to={redirectPath} replace />;
    }

    return children;
  }