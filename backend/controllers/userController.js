import User from "../models/userModel.js";
import generateToken from "../utils/generateToken.js"

//Register new user
export const registerUser = async(req, res) => {
  const { name, email, password, role } = req.body;

  if(!name || !email || !password || !role) {
    //return res.status(400).json({message: "Missing fileds"});
    res.status(400); //400 = bad request
    throw new Error("Missing fields");
  }

  //check if user exists
  const userExists = await User.findOne({ email });
  if(userExists) {
    //return res.status(400).json({message: "User already exists"});
    res.status(400);
    throw new Error("User already exists");
  }

  //create new user given all fields and user does not already exist
  const user = await User.create({
    name,
    email,
    password,
    role,
  });

  res.status(200).json({
    _id: user._id,
    name: user.name,
    email: user.email,
    role: user.role,
    token: generateToken(user._id),
  });
};

//Log in
export const loginUser = async (req,res) => {
  const { email, password } = req.body;

  const user = await User.findOne({ email });

  if(user && (await user.matchPassword(password))) {
    res.json({
      _id: user._id,
      name: user.name,
      email: user.email,
      role: user.role,
      token: generateToken(user._id),
    });
  } else {
    //res.status(400).json({message: "Invalid email or password"});
    res.status(401); //bad credentials
    throw new Error("Invalid email or password");
  }
};