import mongoose from "mongoose";

const customerSchema = mongoose.Schema(
  {
    name: {
      type: String,
      required: true,
      trim: true,
    },
    phone: {
      type: String,
      required: true,
      trim: true,
      index: true, //searchable by phone number
    },
    email: {
      type: String,
      required: true,
    },
  }, 
  {timestamps: true},
);

const Customer = mongoose.model("Customer", customerSchema);
export default Customer;