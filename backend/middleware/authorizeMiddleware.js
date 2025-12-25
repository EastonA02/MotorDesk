export const authorize = (...allowedRoles) => { //all roles pasted inside parameters
  return (req, res, next) => {
    // req.user is set by protect middleware
    if (!req.user || !allowedRoles.includes(req.user.role)) { //req.user set by protect middleware
      res.status(403);
      throw new Error("Not authorized to perform this action");
    }
    //proceed with permission if granted
    next();
  };
};
