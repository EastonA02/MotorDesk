import mongoose from "mongoose";
import bcrypt from "bcryptjs";

const userSchema = mongoose.Schema(
  {
    name: {
      type: String, 
      required: true,
      trim: true,
    },
    email: {
      type: String, 
      required: true, 
      unique: true, 
      lowercase: true, 
      trim: true,
    },
    password: {
      type: String, 
      required: true,
    },
    role: {
      type: String,
      enum: ["Owner", "Technician", "Admin"],
      default: "Technician"
    },
  },
  {timestamps: true}
);

//method to compare password when logging in
userSchema.methods.matchPassword = async function (enteredPassword) {
  return await bcrypt.compare(enteredPassword, this.password)
};

//Run when user is saved to database : hash and save password if modified
userSchema.pre("save", async function (next) {
  if (!this.isModified("password")) return next();

  const salt = await bcrypt.genSalt(10);
  this.password = await bcrypt.hash(this.password, salt);
});

const User = mongoose.model("User", userSchema);
export default User;