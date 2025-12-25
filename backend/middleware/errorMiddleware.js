export const errorHandler = (req,res) => {
  //no status code? then default to 500, otherwise whatever controller set
  const statusCode = res.statusCode === 200? 500 : res.statusCode //status code 500="unexpected error preventing successfull request"

  res.status(statusCode).json({
    message: err.message,
    stack: process.env.NODE_ENV === "production" ? null : err.stack, //hide stack traces
  });
};