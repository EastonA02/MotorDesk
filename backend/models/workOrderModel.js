import mongoose from "mongoose";

const workOrderSchema = mongoose.Schema(
  {
    customer: {
      type: mongoose.Schema.Types.ObjectId,
            ref: "Customer",
            required: true,
    },
    vehicle: {
      type: mongoose.Schema.Types.ObjectId,
            ref: "Vehicle",
            required: true,
    },
    description: {
      type: String,
      required: true,
      trim: true,
    },
    status: {
      type: String,
      enum: [
        "Created",
        "Diagnosing",
        "Waiting_on_parts",
        "In_progress",
        "Completed",
      ],
      default: "Created",
      index: true,
    },
    createdBy: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "User",
      required: true,
    },
  }, {timestamps: true},
);

const WorkOrder = mongoose.model("WorkOrder", workOrderSchema);
export default WorkOrder;