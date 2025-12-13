import mongoose from "mongoose";

const vehicleSchema = mongoose.Schema(
  {
    customer: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "Customer",
      required: true,
    },
    make: {
      type: String,
      required: true,
      trim: true,
    },
    model: {
      type: String,
      required: true,
      trim: true,
    },
    year: {
      type: Number,
      required: true,
    },
    licensePlate: {
      type: String,
      required: true,
      index: true, // searchable but not unique
    },
    vin: {
      type: String,
      unique: true,
      sparse: true, // unique if value exists (not required)
     },
  }, {timestamps: true},
);

const Vehicle = mongoose.model("Vehicle", vehicleSchema);
export default Vehicle;
